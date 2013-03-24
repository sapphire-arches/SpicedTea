package tk.sirtwinkles.spicedtea.world.gen.dungeon.carve;

import tk.sirtwinkles.spicedtea.world.gen.dungeon.Direction;
import static tk.sirtwinkles.spicedtea.MathUtils.random;
import static tk.sirtwinkles.spicedtea.world.gen.TileSetProvider.BACKGROUND;
import static tk.sirtwinkles.spicedtea.world.gen.TileSetProvider.FLOOR;

public class FeatureRoom extends Feature {

	private static final int MIN_SIZE = 4;
	private static final int MAX_SIZE = 10;

	@Override
	public boolean generate(int x, int y, int[][] data, Direction dir) {
		int rw = random.nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE;
		int rh = random.nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE;
		int rx,ry;
		rx = ry = 0;
		switch (dir) {
		case N:
			rx = x - rw / 2;
			ry = y - rh;
			break;
		case E:
			rx = x;
			ry = y - rh / 2;
			break;
		case S:
			rx = x - rw / 2;
			ry = y;
			break;
		case W:
			rx = x - rw;
			ry = y - rh / 2;
			break;
		}
		int testres = test(rx - 1, ry - 1, rh + 2, rh + 2, data, BACKGROUND);
		if (testres == 1) {
			fill(rx, ry, rh, rw, data, FLOOR);
			return true;
		}
		return false;
	}
}
