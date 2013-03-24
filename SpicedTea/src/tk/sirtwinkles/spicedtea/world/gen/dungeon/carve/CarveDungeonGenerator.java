package tk.sirtwinkles.spicedtea.world.gen.dungeon.carve;

import static tk.sirtwinkles.spicedtea.MathUtils.random;
import static tk.sirtwinkles.spicedtea.world.gen.TileSetProvider.FLOOR;
import static tk.sirtwinkles.spicedtea.world.gen.TileSetProvider.BACKGROUND;

import java.util.ArrayList;

import tk.sirtwinkles.spicedtea.world.gen.dungeon.Direction;

public class CarveDungeonGenerator {
	private static ArrayList<Feature> FEATURES;
	
	static {
		//TODO: load this from JSON
		FEATURES = new ArrayList<Feature>();
		FEATURES.add(new FeatureCorridor());
		FEATURES.add(new FeatureRoom());
	}
	
	public static void generate(int width, int height, int depth, int[][] data) {
		genCenterRoom(width, height, data);
		//Try to generate features.
		int rx, ry;
		for (int i = 0; i < width * height; ++i) {
			rx = 1 + random.nextInt(width - 2);
			ry = 1 + random.nextInt(height - 2);
			if (data[rx][ry] == BACKGROUND){
				if (data[rx - 1][ry] == FLOOR) {
					genRandomFeatures(rx, ry, Direction.E, data);
				} else if (data[rx + 1][ry] == FLOOR) {
					genRandomFeatures(rx, ry, Direction.W, data);
				} else if (data[rx][ry + 1] == FLOOR) {
					genRandomFeatures(rx, ry, Direction.N, data);
				} else if (data[rx][ry - 1] == FLOOR) {
					genRandomFeatures(rx, ry, Direction.S, data);
				}
			}
		}
	}

	private static void genRandomFeatures(int rx, int ry, Direction dir,
			int[][] data) {
		int featureIndex =random.nextInt(FEATURES.size());
		//if (dir == Direction.N)
		FEATURES.get(featureIndex).generate(rx, ry, data, dir);
	}

	private static void genCenterRoom(int width, int height, int[][] data) {
		//Random constants 4 days
		int w = width / 5;
		int h = height / 5;
		int sx = width / 2 - w / 2;
		int sy = height / 2 - h / 2;
		for (int x = sx; x < sx + w; ++x) {
			for (int y = sy; y < sy + h; ++y) {
				data[x][y] = FLOOR;
			}
		}
	}
}
