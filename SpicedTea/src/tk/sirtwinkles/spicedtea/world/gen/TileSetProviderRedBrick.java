package tk.sirtwinkles.spicedtea.world.gen;

import tk.sirtwinkles.spicedtea.world.tile.Tile;
import tk.sirtwinkles.spicedtea.world.tile.TileBlack;
import tk.sirtwinkles.spicedtea.world.tile.TileRedBrickFloor;
import tk.sirtwinkles.spicedtea.world.tile.TileRedBrickWall;
import tk.sirtwinkles.spicedtea.world.tile.WallSide;

public class TileSetProviderRedBrick implements TileSetProvider {

	@Override
	public Tile getBackgroundTile(int x, int y) {
		return new TileBlack(x, y);
	}

	@Override
	public Tile getFloorTile(int x, int y) {
		return new TileRedBrickFloor(x, y);
	}

	@Override
	public Tile getWallTile(int x, int y, WallSide side) {
		return new TileRedBrickWall(x, y, side);
	}
}
