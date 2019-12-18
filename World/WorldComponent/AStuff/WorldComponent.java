package WorldComponent.AStuff;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;
import java.util.Vector;

import AStuff.Message;
import Function.AStuff.Function;
import Function.force.Force;

public abstract class WorldComponent implements Serializable{
	private static final long serialVersionUID = 6009345535911252144L;
	
	// Basic Function
	protected Force force;
	public Force getForce() {return force;}
	public boolean forceUse = true;
	public boolean isForceUse() {return forceUse;}
	public void forceOff() {this.forceUse = false; this.remove(this.force);}

	// Constructor
	public WorldComponent(Shape s) {
		functions = new Vector<Function>();
		mailBox = new Vector<Message>();
		shape = s;
		this.force = new Force(this);
		this.add(force);
	}
	
	// Task
	public void update() {for(Function f : functions) {f.additionalUpdate();}}
	public void draw(Graphics2D g){for(Function f : functions) {f.additionalDraw(g);}}
	public void sound() {for(Function f : functions) {f.additionalSound();}}
	
	//Shape
	protected Shape shape;
	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape = shape;}
		
	// Function
	protected Vector<Function> functions;//private
	public void add(Function f) {functions.add(f);}
	public void add(int i, Function f) {functions.add(i, f);}
	public void remove(Function f) {functions.remove(f);}
	public abstract void addFunctions();
	
	// Function Message
	Vector<Message> mailBox;
	public abstract void additionalProcessMessage(Message msg);
	public void giveMessage(Message msg) {mailBox.add(msg);}
	public void processMessage() {
		Vector<Message> nowInMailBox = new Vector<Message>();//메세지 처리하다 메세지 또 나오면 컨쿼런트 모디픠퀘이션 뜸. 안뜨게함. ㅇㅂㅇ
		nowInMailBox.addAll(mailBox);
		mailBox.clear();
		for(Message msg : nowInMailBox) {
			additionalProcessMessage(msg);
			for(Function f : functions) {
				f.processMessage(msg);
			}
		}
	}
	
	public abstract boolean areYou(WorldComponentState state);
}
