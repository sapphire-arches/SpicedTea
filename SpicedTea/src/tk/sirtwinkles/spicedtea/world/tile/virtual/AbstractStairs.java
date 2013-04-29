package tk.sirtwinkles.spicedtea.world.tile.virtual;

import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.world.Level;
import tk.sirtwinkles.spicedtea.world.tile.StairDirection;
import tk.sirtwinkles.spicedtea.world.tile.Tile;
import tk.sirtwinkles.spicedtea.world.tile.TileClass;

public abstract class AbstractStairs extends Tile {
	private StairDirection direction;
	
	public AbstractStairs(String id, int imgX, int imgY, int x, int y, StairDirection dir) {
		super(id, imgX, imgY, x, y);
		this.direction = dir;
	}
	
	@Override
	public void onEntityStep(Entity step, Level in) {
		if (step.getID().equalsIgnoreCase("player")) {
			if (this.direction == StairDirection.DOWN) {
				in.complete();
			}
		}
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
		return TileClass.STAIRS;
	}
}
