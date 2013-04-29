package tk.sirtwinkles.spicedtea.world.gen;

import tk.sirtwinkles.spicedtea.world.tile.StairDirection;
import tk.sirtwinkles.spicedtea.world.tile.Tile;

public interface TileSetProvider {
	public static final int BACKGROUND = 0;
	public static final int FLOOR = 1;
	public static final int WALL = 2;
	public static final int DOOR = 3;
	public static final int STAIR_UP = 4;
	public static final int STAIR_DOWN = 5;
	
	public Tile getBackgroundTile(int x, int y);
	public Tile getFloorTile(int x, int y);
	public Tile getWallTile(int x, int y);
	public Tile getDoorTile(int x, int y);
	public Tile getStair(int x, int y, StairDirection dir);
}
