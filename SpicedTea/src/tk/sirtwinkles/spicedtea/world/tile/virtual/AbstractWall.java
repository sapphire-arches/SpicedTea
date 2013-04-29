package tk.sirtwinkles.spicedtea.world.tile.virtual;

import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.world.Level;
import tk.sirtwinkles.spicedtea.world.tile.Tile;
import tk.sirtwinkles.spicedtea.world.tile.TileClass;

public abstract class AbstractWall extends Tile {

	public AbstractWall(String id, int imgX, int imgY, int x, int y) {
		super(id, 0, 0, x, y);
		this.imgX = imgX;
		this.imgY = imgY;
	}
	public int getImageX() {
		return imgX;
	}
	
	@Override
	public void onEntityStep(Entity step, Level in) {
		//Nothing to do
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
		return TileClass.WALL;
	}
}
