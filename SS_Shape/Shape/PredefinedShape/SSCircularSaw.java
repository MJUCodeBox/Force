package Shape.PredefinedShape;

import java.awt.Shape;

import Edit.EditShape;
import Edit.ShapeTool;
import Shape.SSEllipse;
import Shape.SSNCircularSaw;
import Shape.SSNStar;

public class SSCircularSaw {

	public static Shape getShape() {
		return makeShape();
	}
	
	public static Shape makeShape() {
		SSNCircularSaw.setNSpike(12);
		SSNCircularSaw.setSpikiness(0.75);
		SSNCircularSaw.setOddAngleFactor(1);
		
		SSNStar.setNSpike(3);
		SSNStar.setSpikiness(1);
		
		Shape movedStar = EditShape.getMovedShapeCenterTo(SSNCircularSaw.getShape(0, 0, 70, 70), 20, 20);
		Shape subtracted = ShapeTool.getSubtract(movedStar, new Shape[] {SSEllipse.getShape(0, 0, 40, 40)});
		Shape compound = ShapeTool.getCompound(new Shape[] {subtracted, SSNStar.getShape(10, 10, 20, 20)});
		
		return compound;
	}
}
