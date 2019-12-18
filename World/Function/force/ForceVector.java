package Function.force;

import java.io.Serializable;

public class ForceVector implements Serializable{
	private static final long serialVersionUID = -3405484436865427639L;
	
	// System Value
	public double x = 0, y = 0;

	//Constructor
    public ForceVector() {}
    public ForceVector(ForceVector v) {this.x = v.x; this.y = v.y;}
    public ForceVector(double x, double y) {this.x = x; this.y = y;}
    
    public void set(ForceVector v) {this.x = v.x; this.y = v.y;}
    public void set(double x, double y) {this.x = x; this.y = y;}
    
    public void add(ForceVector v) {this.x += v.x; this.y += v.y;} 
    public void add(double x, double y) {this.x += x; this.y += y;}
    
    public void sub(ForceVector v) {this.x -= v.x; this.y -= v.y;}
    public void sub(double x, double y) {this.x -= x; this.y -= y;}

    public void scale(double s) {this.x *= s; this.y *= s;}
    
    public double dot(ForceVector v) {return x * v.x + y * v.y;}//내적
	public double cross(ForceVector v) {return x * v.y - y * v.x;}//외적
	
	public double size() {return Math.sqrt(x*x + y*y);}
}
