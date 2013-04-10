package tk.sirtwinkles.spicedtea.world;

import tk.sirtwinkles.spicedtea.GameSpicedTea;
import tk.sirtwinkles.spicedtea.Globals;
import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.state.PlayingState;
import tk.sirtwinkles.spicedtea.world.gen.JSONTileSetProvider;
import tk.sirtwinkles.spicedtea.world.gen.LevelGenerator;
import tk.sirtwinkles.spicedtea.world.gen.TileSetProvider;

public class World {
	private Level current;

	public World(Entity player) {
		TileSetProvider prov = new JSONTileSetProvider(
				(String) Globals.assets.get("data/config/tilesets/RedBrick.json"));
		this.current = LevelGenerator.create(80, 40, 0, prov, player);
	}

	public void update(GameSpicedTea game, PlayingState play) {
		current.update(game, play);
	}

	public Level getCurrent() {
		return current;
	}
	
	public void setCurrent(Level to) {
		this.current = to;
	}
}
