package tk.sirtwinkles.spicedtea;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Represents a graphics context.
 * @author bob_twinkles
 *
 */
public class GraphicsContext implements Disposable {
	private SpriteBatch batch;
	/**
	 * Width of the screen
	 */
	private int width;
	/**
	 * Height of the screen
	 */
	private int height;
	
	public GraphicsContext() {
		batch = new SpriteBatch();
	}
	
	/**
	 * Called to initialize context.
	 */
	public void begin() {
		batch.begin();
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}
	
	/**
	 * Finishes the rendering.
	 */
	public void end() {
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	
	public void onResize( int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
}
