package Function.force;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;

import AStuff.Message;
import AStuff.MessageType;
import AStuff.TheWorld;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.AStuff.Function;
import Shape.SSEllipse;
import WorldComponent.AStuff.WorldComponent;
import calculation.Calculator;

public class Force extends Function{// Expensive. But I Love this!
	private static final long serialVersionUID = 1L;
	
	// I don't like this
	private boolean noEffect = false;
	
	// User Value
	private int forceArea = 100;
    private double mass = 1;//이동 저항
	private double inertia = 1000;//회전 저항
	private double friction = 0.97;//마찰 저항
	private double elasticity = 0.8;//탄성
    
    // System Value
    public ForceVector force = new ForceVector();//이동 힘
    public ForceVector velocity = new ForceVector();//속도
    public ForceVector acceleration = new ForceVector();//가속도
    
    private double torque = 0;//회전 힘
    private double angularVelocity = 0;//회전 속도
    private double angularAcceleration = 0;//회전 가속도
    public double angle = 0;
    
	protected Message resistIntersectMSG;//충돌 MSG
	private void initMsg() {resistIntersectMSG = new Message(MessageType.ResistIntersect, master, this, null);}
	
	// Constructor
	public Force(WorldComponent master) {super(master); initMsg();}
	
	@Override 
	public void additionalUpdate() {
		if(!noEffect) {
			// Move
			velocity.add(acceleration);
			force.scale(1 / mass);
			acceleration.set(force);
			master.setShape(EditShape.getMovedShape(master.getShape(), velocity.x, velocity.y));
			
			// Rotate
			angularVelocity += angularAcceleration;
			angularAcceleration = torque / inertia;
			angle+=angularVelocity;
			if(angularVelocity>360) {angle-=360;}
			master.setShape(EditShape.getRotatedShape(master.getShape(), angularVelocity));
			
			// Resist
			velocity.scale(friction);
			angularVelocity *= friction;
			
			// Die
			force.set(0, 0);
			torque = 0;
			
			resistCollide();
		}else {
			velocity.set(0, 0);//no move
			angularVelocity = 0;
			force.set(0, 0);
			torque = 0;
			sendMsgToIntersectWC();
		}
	}
	
	protected void sendMsgToIntersectWC() {
		for(WorldComponent wc : TheWorld.getWorldComponents()) {
			if(wc!=master && ShapeTool.isIntersect(master.getShape(), wc.getShape())) {
				wc.giveMessage(resistIntersectMSG);
				master.giveMessage(new Message(MessageType.ResistIntersect, wc, this, null));
			}
		}
	}
	
	protected void resistCollide() {
		for(WorldComponent wc : TheWorld.getWorldComponents()) {
			if(wc!=master && ShapeTool.isIntersect(master.getShape(), wc.getShape())) {
				wc.giveMessage(resistIntersectMSG);
				master.giveMessage(new Message(MessageType.ResistIntersect, wc, this, null));
				if(wc.isForceUse()) {
					resolveCollide(wc);
					resistIntersect(wc);
				}
			}
		}
	}
	
	protected void resistIntersect(WorldComponent wc) {//회전으로 힘을 못줌.
		Point2D masterCenter = ShapeTool.getCenterPoint(master.getShape());
		Rectangle masterBound = master.getShape().getBounds();
		double masterW = masterBound.getWidth();
		double masterH = masterBound.getHeight();
		Shape forceEffectArea = SSEllipse.getShape(0, 0, masterW + forceArea*2,masterH + forceArea*2);
		forceEffectArea = EditShape.getMovedShapeCenterTo(forceEffectArea, masterCenter.getX(), masterCenter.getY());
		Shape applyArea = ShapeTool.getIntersect(wc.getShape(), forceEffectArea);
		
    	while(ShapeTool.isIntersect(master.getShape(), applyArea)) {//밖 -> 안 저항
    		int dx = 0, dy = 0;
    		Point2D masterCenter2 = ShapeTool.getCenterPoint(master.getShape());
    		Point2D wcCenter2 = ShapeTool.getCenterPoint(applyArea);
    		if(Math.abs(masterCenter2.getX() - wcCenter2.getX()) > Math.abs(masterCenter2.getY() - wcCenter2.getY())) {
    			if(masterCenter2.getX() < wcCenter2.getX()) {dx=-1;}else{dx=1;}
    		}else {
    			if(masterCenter2.getY() < wcCenter2.getY()) {dy=-1;}else{dy=1;}
    		}
    		master.setShape(EditShape.getMovedShape(master.getShape(), dx, dy));
    	}
	}
	
    protected void resolveCollide(WorldComponent wc) {
    	// Move
    	Force collideWCForce = wc.getForce();
    	
    	double ma = this.mass;
    	ForceVector  va = this.velocity;
    	double ea = this.elasticity;
    	
    	double mb = collideWCForce.getMass();
    	ForceVector  vb = collideWCForce.getVelocity();
    	double eb = collideWCForce.getElasticity(); 
    	
    	double theta = Calculator.getAngle(new Point2D.Double(va.x, va.y), new Point2D.Double(vb.x, vb.y));
    	double sintheta = Math.sin(theta);
    	double costheta = Math.cos(theta);
    	
    	double vaxp1 = (ma - ea * mb) / (ma + mb) * (va.x * costheta + va.y * sintheta) + (mb + ea * mb) / (ma + mb) * (vb.x * costheta + vb.y * sintheta);
    	double vbxp1 = (ma + eb * ma) / (ma + mb) * (va.x * costheta + va.y * sintheta) + (mb - eb * ma) / (ma + mb) * (vb.x * costheta + vb.y * sintheta);

    	double vayp1 = va.y * costheta - va.x * sintheta;
    	double vbyp1 = vb.y * costheta - vb.x * sintheta;

    	double vaxp2 = vaxp1 * costheta - vayp1 * sintheta;
    	double vayp2 = vaxp1 * sintheta + vayp1 * costheta;
    	
    	double vbxp2 = vbxp1 * costheta - vbyp1 * sintheta;
    	double vbyp2 = vbxp1 * sintheta + vbyp1 * costheta;
    			 
    	this.velocity.set(vaxp2, vayp2);
    	collideWCForce.getVelocity().set(vbxp2, vbyp2);
    	
    	// Rotate
    	Point2D masterCenter = ShapeTool.getCenterPoint(master.getShape());
		Point2D wcCenter = ShapeTool.getCenterPoint(wc.getShape());
		Point2D centersCenter = new Point2D.Double((masterCenter.getX() + wcCenter.getX())/2, (masterCenter.getY() + wcCenter.getY())/2);
		
		ForceVector masterAngleVector = new ForceVector();
		masterAngleVector.set(centersCenter.getX(), centersCenter.getY());
		masterAngleVector.sub(masterCenter.getX(), masterCenter.getY());
        angularVelocity += (masterAngleVector.cross(velocity) / inertia);
        
        ForceVector collideAngleVector = new ForceVector();
        collideAngleVector.set(centersCenter.getX(), centersCenter.getY());
		collideAngleVector.sub(wcCenter.getX(), wcCenter.getY());
		collideWCForce.setAngularVelocity(collideWCForce.getAngularVelocity() + (collideAngleVector.cross(collideWCForce.getVelocity()) / collideWCForce.getInertia()));
	}

    // Getter & Setter
    public double getMass() {return this.mass;}
    public double getInertia() {return this.inertia;}
    public double getFriction() {return this.friction;}
    public double getElasticity() {return this.elasticity;}
    public double getAngularVelocity() {return angularVelocity;}
    public ForceVector getForceVector() {return force;}
    public ForceVector getVelocity() {return velocity;}
    public double getAngle() {return angle;}
    
    public void setMass(double mass) {this.mass = mass;}
    public void setInertia(double inertia) {this.inertia = inertia;}
    public void setVelocity(ForceVector velocity) {this.velocity = velocity;}
    public void setResistFactor(double resistFactor) {this.friction = resistFactor;}
    public void setElasticity(double elasticity) {this.elasticity = elasticity;}
    public void setAngularVelocity(double angularVelocity) {this.angularVelocity = angularVelocity;}
    
    public void applyForce(double x, double y) {this.force.add(new ForceVector(x, y));}
	public void applyTorque(double torque) {this.torque += torque;}

	public boolean getNoEffect() {return noEffect;}
	public void setNoEffect(boolean b) {
		this.noEffect = b; 
		if(b) {mass = 999999999; inertia = 999999999;}
		else {mass = 1; inertia = 10000;}
	}
	
	// No Use
	@Override public void processMessage(Message msg) {}
	@Override public void additionalDraw(Graphics2D g) {}
	@Override public void additionalSound() {}
}
