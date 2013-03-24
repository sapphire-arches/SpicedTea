package tk.sirtwinkles.spicedtea.world.gen.dungeon.carve;

import tk.sirtwinkles.spicedtea.world.gen.dungeon.Direction;
import static tk.sirtwinkles.spicedtea.MathUtils.random;
import static tk.sirtwinkles.spicedtea.world.gen.TileSetProvider.FLOOR;
import static tk.sirtwinkles.spicedtea.world.gen.TileSetProvider.BACKGROUND;

public class FeatureCorridor extends Feature {
	public static final int WIDTH = 1;
	public static final int MAX_LENGTH = 10;

	@Override
	public boolean generate(int x, int y, int[][] data, Direction dir) {
		int length = WIDTH + random.nextInt(MAX_LENGTH - WIDTH);
		switch (dir) {
		case S:
			if (test(x - 1, y - 1, WIDTH + 2, length + 2, data, BACKGROUND) == WIDTH + 2) {
				fill(x, y, WIDTH, length, data, FLOOR);
				break;
			}
			return false;
		case E:
			if (test(x - 1, y - 1, length + 2, WIDTH + 2, data, BACKGROUND) == WIDTH + 2) {
				fill(x, y, length, WIDTH, data, FLOOR);
				break;
			}
			return false;
		case N:
			if (test(x - 1, y - length, WIDTH + 2, length + 2, data, BACKGROUND) == WIDTH + 2) {
				fill(x, y - length + 1, WIDTH, length, data, FLOOR);
				break;
			}
			return false;
		case W:
			if (test(x - length, y - 1, length + 2, WIDTH + 2, data, BACKGROUND) == WIDTH + 2) {
				fill(x - length + 1, y, length, WIDTH, data, FLOOR);
				break;
			}
			return false;
		}
		return true;
	}
}
