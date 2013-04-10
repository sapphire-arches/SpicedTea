package tk.sirtwinkles.spicedtea.world.tile;

import static tk.sirtwinkles.spicedtea.MathUtils.random;
import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.world.Level;

public class TileBlack extends Tile {
	public TileBlack(int x, int y) {
		super("tk.sirtwinkles.spicedtea.world.tile.TileBlack", random.nextInt(8), random.nextInt(2), x, y);
	}

	@Override
	public void onEntityStep(Entity step, Level in) {
		//Do nothgin
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
