package tk.sirtwinkles.spicedtea.world.gen.dungeon.carve.room;

import tk.sirtwinkles.spicedtea.world.Level;
import tk.sirtwinkles.spicedtea.world.gen.TileSetProvider;

public class RoomFeatureStairDown implements RoomFeature {

	@Override
	public void generate(Room in, int[][] data, Level level) {
		data[in.x + in.w / 2][in.y + in.h / 2] = TileSetProvider.STAIR_DOWN;
	}

}
