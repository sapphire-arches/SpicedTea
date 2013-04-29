package tk.sirtwinkles.spicedtea.sys.render;

import tk.sirtwinkles.spicedtea.Globals;
import tk.sirtwinkles.spicedtea.GraphicsContext;
import tk.sirtwinkles.spicedtea.world.Level;
import tk.sirtwinkles.spicedtea.world.World;
import tk.sirtwinkles.spicedtea.world.tile.Tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class LevelRenderer {
	/**
	 * Conversion factor from world tile space to screen tile space.
	 */
	public static final int TILE_SIZE = 8;

	private Texture terrain;
	private World world;

	public LevelRenderer(World world) {
		this.world = world;
		this.terrain = (Texture) (Globals.assets.get("data/terrain.png"));
	}

	public void render(GraphicsContext context, Viewport view) {
		SpriteBatch sb = context.getBatch();
		Rectangle viewRect = view.getSizeAndPosition();
		final int w = (int) (viewRect.width / TILE_SIZE) + 1;
		final int h = (int) (viewRect.height / TILE_SIZE) + 1;
		final int tx = (int) (viewRect.x / TILE_SIZE);
		final int ty = (int) (viewRect.y / TILE_SIZE);
		int imgX = 0;
		int imgY = 0;
		Tile current = null;
		Level level = world.getCurrent();

		for (int x = -1; x < w; ++x) {
			for (int y = -1; y < h; ++y) {
				if (level.isVisible(tx + x, ty + y)) {
					current = level.getTile(tx + x, ty + y);
					if (current != null) {
						imgX = current.getImageX();
						imgY = current.getImageY();
					} else {
						imgX = 0;
						imgY = 0;
					}

					sb.draw(terrain, (x * TILE_SIZE), (y * TILE_SIZE),
							TILE_SIZE, TILE_SIZE, imgX * TILE_SIZE, imgY
									* TILE_SIZE, TILE_SIZE, TILE_SIZE, false,
							true);
				}
			}
		}
	}

	public float getMaxY() {
		return world.getCurrent().getHeight() * TILE_SIZE;
	}

	public float getMaxX() {
		return world.getCurrent().getWidth() * TILE_SIZE;
	}
}
