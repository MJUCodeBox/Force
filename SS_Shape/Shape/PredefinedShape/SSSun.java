package Shape.PredefinedShape;

import java.awt.Shape;

import Edit.EditShape;
import Edit.ShapeTool;
import Shape.SSEllipse;
import Shape.SSNStar;

public class SSSun {

	public static Shape getShape() {
		return makeShape();
	}
	
	public static Shape makeShape() {
		SSNStar.setNSpike(8);
		SSNStar.setSpikiness(0.6);
		Shape movedStar = EditShape.getMovedShapeCenterTo(SSNStar.getShape(0, 0, 65, 65), 25, 25);
		Shape compound = ShapeTool.getCompound(new Shape[]{movedStar, SSEllipse.getShape(0, 0, 50, 50)});
		return compound;
	}
}
