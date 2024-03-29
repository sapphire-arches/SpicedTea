package tk.sirtwinkles.spicedtea.sys.render;

import com.badlogic.gdx.math.Rectangle;

/**
 * Represents a view of the gameworld in screenspace coordinates.
 * @author bob_twinkles
 *
 */
public class Viewport {
	private Rectangle sizePos;
	
	public Viewport(Rectangle sizePos) {
		this.sizePos = sizePos;
	}
	
	public Rectangle getSizeAndPosition() {
		return sizePos;
	}
	
	public void setPosition(float x, float y) {
		this.sizePos.x = x;
		this.sizePos.y = y;
	}
	
	public void resize(float width, float height) {
		this.sizePos.width = width;
		this.sizePos.height = height;
	}
}
