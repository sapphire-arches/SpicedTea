package tk.sirtwinkles.spicedtea.world.gen.dungeon.carve;

import tk.sirtwinkles.spicedtea.world.Direction;
import static tk.sirtwinkles.spicedtea.world.gen.TileSetProvider.*;
import static tk.sirtwinkles.spicedtea.MathUtils.random;

public class FeatureExtraDoor extends Feature {

	@Override
	public boolean generate(int x, int y, int[][] data, Direction dir) {
		if (random.nextFloat() > 0.1) {
			return false;
		}
		switch (dir) {
		case N:
		case S:
			if (0 < y - 1 && y + 1 < data.length && 0 < x - 1
					&& x + 1 < data.length) {
				if (data[x][y - 1] == FLOOR && data[x][y + 1] == FLOOR) {
					if (data[x - 1][y] == WALL && data[x + 1][y] == WALL) {
						data[x][y] = DOOR;
					}
				}
			}
			return true;
		case E:
		case W:
			if (0 < y - 1 && y + 1 < data.length && 0 < x - 1
					&& x + 1 < data.length) {
				if (data[x - 1][y] == FLOOR && data[x + 1][y] == FLOOR) {
					if (data[x][y - 1] == WALL && data[x][y + 1] == WALL) {
						data[x][y] = DOOR;
					}
				}
			}
			return true;
		}
		return false;
	}
}
