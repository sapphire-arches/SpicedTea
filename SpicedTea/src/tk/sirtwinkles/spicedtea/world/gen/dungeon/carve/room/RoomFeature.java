package tk.sirtwinkles.spicedtea.world.gen.dungeon.carve.room;

import tk.sirtwinkles.spicedtea.world.Level;

public interface RoomFeature {
	void generate(Room in, int[][] data, Level level);
}