package tk.sirtwinkles.spicedtea.world.gen.dungeon.bsp;

import static tk.sirtwinkles.spicedtea.MathUtils.random;
import static tk.sirtwinkles.spicedtea.world.gen.TileSetProvider.FLOOR;
import static tk.sirtwinkles.spicedtea.world.gen.dungeon.LineUtils.drawZigZag;
import static tk.sirtwinkles.spicedtea.world.gen.dungeon.LineUtils.isValidLine;

import java.util.LinkedList;

import tk.sirtwinkles.spicedtea.world.Point;

public class BSPDungeonGenerator {

	public static void generate(int width, int height, int depth, int[][] data) {
		BSPTree bsp = BSPTree.buildTree(width, height, 8);

		drawTree(bsp, depth, data);
	}

	private static void drawTree(BSPTree bsp, int depth, int[][] data) {
		if (bsp.right == null && bsp.left == null) {
			if (bsp.draw) {
				int hwOff = 3 + random.nextInt(bsp.width - 8);
				if (hwOff < 1) hwOff = 1;
				int hhOff = 3 + random.nextInt(bsp.height - 8);
				if (hhOff < 1) hhOff = 1;
				
				for (int x = bsp.x + hwOff; x < bsp.x + bsp.width - hwOff; ++x) {
					for (int y = bsp.y + hhOff; y < bsp.y + bsp.height - hhOff; ++y) {
						data[x][y] = FLOOR;
					}
				}
			}
		} else {
			drawTree(bsp.right, depth, data);
			drawTree(bsp.left, depth, data);
			connectBSPSubNodes(bsp, data);
		}
	}

	private static void connectBSPSubNodes(BSPTree bsp, int[][] data) {
		if (!bsp.right.draw || !bsp.left.draw)
			return; // Nothing to do, the left and right children aren't even
					// both rooms!
		Point rp, lp;
		do {
			rp = getValidLocation(bsp.right, data);
			lp = getValidLocation(bsp.left, data);
			if (rp == null || lp == null) {
				System.out.println("Bailed on connecting up a BSP node =(");
				return; // Something went horibbly wrong, Bail!
			}
		} while (!isValidLine(rp, lp, bsp.dir, data));
		LinkedList<Point> ll = new LinkedList<Point>();

		drawZigZag(ll, rp, lp, 0.1f);
		while (!(ll.size() == 0)) {
			Point temp = ll.removeFirst();
			if (1 <= temp.x && temp.x < data.length-1 && 1 <= temp.y
					&& temp.y < data[temp.x].length-1) {
				for (int x = temp.x - 1; x <= temp.x + 1; ++x) {
					for (int y = temp.y - 1; y <= temp.y + 1; ++y) {
						data[x][y] = FLOOR;
					}
				}
			}
			Point.free(temp);
		}

		Point.free(rp);
		Point.free(lp);
	}

	private static Point getValidLocation(BSPTree in, int[][] data) {

		int x, y;
		int tries = 0;
		do {
			x = in.x + random.nextInt(in.width);
			y = in.y + random.nextInt(in.height);
			++tries;
			if (tries > 1000) {
				boolean foundFloor = false;
				for (int xx = in.x; xx < in.x + in.width; ++xx) {
					for (int yy = in.y; yy < in.y + in.height; ++yy) {
						if (data[x][y] == FLOOR) {
							foundFloor = true;
						}
					}
				}
				if (!foundFloor) {
					return null;
				}
			}
		} while (((x == in.x || y == in.y) || data[x][y] != FLOOR));

		return Point.getPoint(x, y);
	}
}
