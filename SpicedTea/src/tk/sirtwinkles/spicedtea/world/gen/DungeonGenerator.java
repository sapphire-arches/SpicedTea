package tk.sirtwinkles.spicedtea.world.gen;

import tk.sirtwinkles.spicedtea.world.gen.dungeon.carve.CarveDungeonGenerator;

public class DungeonGenerator {
	/**
	 * Generates a dungeon.
	 * 
	 * @param width
	 *            The width of the dungeon
	 * @param height
	 *            The height of the dungeon
	 * @param depth
	 *            The depth of the dungeon
	 * @param data
	 *            The data array to generate into.
	 */

	public static void generate(int width, int height, int depth, int[][] data) {
		CarveDungeonGenerator.generate(width, height, depth, data);
	}
}
