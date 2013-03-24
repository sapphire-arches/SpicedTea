package tk.sirtwinkles.spicedtea.world;

import tk.sirtwinkles.spicedtea.GameSpicedTea;
import tk.sirtwinkles.spicedtea.state.PlayingState;
import tk.sirtwinkles.spicedtea.world.gen.JSONTileSetProvider;
import tk.sirtwinkles.spicedtea.world.gen.LevelGenerator;
import tk.sirtwinkles.spicedtea.world.gen.TileSetProvider;

import com.badlogic.gdx.assets.AssetManager;

public class World {
	private Level current;

	public World(AssetManager mgr) {
		TileSetProvider prov = new JSONTileSetProvider(
				(String) mgr.get("data/config/tilesets/GreyBrick.json"));
		mgr.unload("data/config/tilesets/GreyBrick.json");
		this.current = LevelGenerator.create(80, 40, 0, prov);
	}

	public void update(GameSpicedTea game, PlayingState play) {
		current.update(game, play);
		if (current.isComlete()) {
			current = current.getNextLevel();
		}
	}

	public Level getCurrent() {
		return current;
	}
}
