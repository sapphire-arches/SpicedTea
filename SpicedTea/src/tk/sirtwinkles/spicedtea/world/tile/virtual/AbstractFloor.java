package tk.sirtwinkles.spicedtea.world.tile.virtual;

import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.world.Level;
import tk.sirtwinkles.spicedtea.world.tile.Tile;
import tk.sirtwinkles.spicedtea.world.tile.TileClass;

public abstract class AbstractFloor extends Tile {

	public AbstractFloor(String id, int baseImgX, int baseImgY, int x, int y) {
		super(id, baseImgX, baseImgY, x, y);
	}

	@Override
	public void onEntityStep(Entity step, Level in) {
		//Do nothing.
	}
	
	@Override
	public boolean isPassable(Entity ent, Level in) {
		return true;
	}
	
	@Override
	public boolean isPathable(Entity ent, Level in) {
		return true;
	}
	
	@Override
	public TileClass getTileClass() {
		return TileClass.FLOOR;
	}
}
