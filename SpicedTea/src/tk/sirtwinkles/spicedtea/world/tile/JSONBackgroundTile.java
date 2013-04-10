package tk.sirtwinkles.spicedtea.world.tile;

import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.world.Level;

public class JSONBackgroundTile extends Tile {

	public JSONBackgroundTile(String id, int imgX, int imgY, int x, int y) {
		super(id, imgX, imgY, x, y);
	}

	@Override
	public void onEntityStep(Entity step, Level in) {
		//Nothing to do here.
	}

	@Override
	public boolean isPassable(Entity ent, Level in) {
		return false;
	}
	
	@Override
	public boolean isPathable(Entity ent, Level in) {
		return false;
	}
	
	@Override
	public TileClass getTileClass() {
		return TileClass.BACKGROUND;
	}
}
