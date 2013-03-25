package tk.sirtwinkles.spicedtea.world.tile;

import static tk.sirtwinkles.spicedtea.MathUtils.random;

public class TileBlack extends Tile {
	public TileBlack(int x, int y) {
		super("tk.sirtwinkles.spicedtea.world.tile.TileBlack", random.nextInt(8), random.nextInt(2), x, y);
	}
}
