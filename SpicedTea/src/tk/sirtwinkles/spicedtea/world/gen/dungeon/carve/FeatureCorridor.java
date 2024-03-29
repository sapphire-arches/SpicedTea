package tk.sirtwinkles.spicedtea.world.gen.dungeon.carve;

import static tk.sirtwinkles.spicedtea.MathUtils.random;
import static tk.sirtwinkles.spicedtea.world.gen.TileSetProvider.BACKGROUND;
import static tk.sirtwinkles.spicedtea.world.gen.TileSetProvider.DOOR;
import static tk.sirtwinkles.spicedtea.world.gen.TileSetProvider.FLOOR;
import static tk.sirtwinkles.spicedtea.world.gen.TileSetProvider.WALL;
import tk.sirtwinkles.spicedtea.world.Direction;

public class FeatureCorridor extends Feature {
	public static final int WIDTH = 3;
	public static final int MAX_LENGTH = 10;
	public static final int MIN_LENGTH = 4;

	@Override
	public boolean generate(int x, int y, int[][] data, Direction dir) {
		int length = MIN_LENGTH + random.nextInt(MAX_LENGTH - MIN_LENGTH);
		switch (dir) {
		case S:
			if (test(x - 1, y - 1, WIDTH + 2, length + 2, data, BACKGROUND) == 2 * (WIDTH + 2)) {
				fill(x, y, WIDTH, length, data, WALL);
				fill(x + 1, y, 1, length, data, FLOOR);
				data[x + 1][y] = DOOR;
				break;
			}
			return false;
		case E:
			if (test(x - 1, y - 1, length + 2, WIDTH + 2, data, BACKGROUND) == 2 * (WIDTH + 2)) {
				fill(x, y, length, WIDTH, data, WALL);
				fill(x, y + 1, length, 1, data, FLOOR);
				data[x][y + 1] = DOOR;
				break;
			}
			return false;
		case N:
			if (test(x - 1, y - length, WIDTH + 2, length + 2, data, BACKGROUND) == 2 * (WIDTH + 2)) {
				fill(x, y - length + 1, WIDTH, length, data, WALL);
				fill(x + 1, y - length + 1, 1, length, data, FLOOR);
				data[x + 1][y] = DOOR;
				break;
			}
			return false;
		case W:
			if (test(x - length, y - 1, length + 2, WIDTH + 2, data, BACKGROUND) == 2 * (WIDTH + 2)) {
				fill(x - length + 1, y, length, WIDTH, data, WALL);
				fill(x - length + 1, y + 1, length, 1, data, FLOOR);
				data[x][y + 1] = DOOR;
				break;
			}
			return false;
		}
		return true;
	}
}
