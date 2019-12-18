package Function.Draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import AStuff.Message;
import Edit.EditShape;
import Edit.ShapeTool;
import Function.AStuff.Function;
import Shape.PredefinedShape.SSSun;
import WorldComponent.AStuff.WorldComponent;

public class ConvertToImageAndDraw extends Function {
	private static final long serialVersionUID = -5019317475898681946L;
	
	// Values
	public boolean GrowOn = false;
	int diffLight = 10;
	int diffSize = 10;
	int num = 10;
	
	BufferedImage img;
	int imgWidth;
	int imgHeight; 
	
	// Constructor
	public ConvertToImageAndDraw(WorldComponent master) {super(master); init();}

	private void init() {
		Shape masterShape = master.getShape();
		Point2D masterCenter = ShapeTool.getCenterPoint(masterShape);

		Shape sun = SSSun.getShape();
		sun = EditShape.getScaledShape(sun, 1, 1);
		sun = EditShape.getMovedShapeCenterTo(sun, masterCenter.getX(), masterCenter.getY());
		Rectangle masterBound = masterShape.getBounds();
		masterShape = sun;
		int masterWidth = (int) masterBound.getWidth();
		int masterHeight = (int) masterBound.getHeight();
		img = new BufferedImage(masterWidth, masterHeight, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = (Graphics2D) img.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Shape movedShape = EditShape.getMovedShapeCenterTo(masterShape, masterWidth / 2, masterHeight / 2);

		Color fillColor = Color.ORANGE;
		int red = fillColor.getRed();
		int green = fillColor.getGreen();
		int blue = fillColor.getBlue();

		for (int i = 0; i < num; i++) {
			Color c = new Color(red, green, blue, diffLight * i);
			int diffWH = (num - i) * diffSize;
			double wRadio = (masterBound.getWidth() + diffWH) / masterBound.getWidth();
			double hRadio = (masterBound.getHeight() + diffWH) / masterBound.getHeight();
			Shape s = EditShape.getScaledShape(masterShape, wRadio, hRadio);
			s = EditShape.getMovedShapeCenterTo(s, masterWidth / 2, masterHeight / 2);
			g2d.setColor(c);
			g2d.fill(s);
		}
		g2d.setColor(Color.white);
		g2d.fill(movedShape);
		g2d.dispose();
		imgWidth = img.getWidth();
		imgHeight = img.getHeight();
	}

	@Override
	public void additionalDraw(Graphics2D g) {
		double angle = master.getForce().getAngle();
		Shape masterShape = master.getShape();
		Point2D masterCenter = ShapeTool.getCenterPoint(masterShape);
		
		AffineTransform at = new AffineTransform();
		at.translate(masterCenter.getX() - imgWidth / 2, masterCenter.getY() - imgHeight / 2);
		at.rotate(Math.toRadians(angle), imgWidth / 2, imgHeight / 2);
		
		g.drawImage(img, at, null);
	}

	@Override public void additionalUpdate() {}
	@Override public void additionalSound() {}
	@Override public void processMessage(Message msg) {}
}
