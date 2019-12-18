package Edit;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.GlyphVector;

public class TextShape {

	public static Shape getTextShape(Graphics2D g, String text) {
		GlyphVector gv = g.getFont().createGlyphVector(g.getFontRenderContext(), text);
		return gv.getOutline();
	}
	
}
