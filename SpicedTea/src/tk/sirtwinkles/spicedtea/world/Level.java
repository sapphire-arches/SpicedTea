package tk.sirtwinkles.spicedtea.world;

import java.util.LinkedList;

import tk.sirtwinkles.spicedtea.GameSpicedTea;
import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.state.PlayingState;
import tk.sirtwinkles.spicedtea.world.tile.Tile;
import tk.sirtwinkles.spicedtea.world.tile.TileBlack;
import tk.sirtwinkles.spicedtea.world.tile.TileRedBrickFloor;
import tk.sirtwinkles.spicedtea.world.tile.TileRedBrickWall;
import tk.sirtwinkles.spicedtea.world.tile.WallSide;

public class Level {
	private Tile[][] tiles;
	private int width, height;
	private LinkedList<Entity> ents;

	public Level(int width, int height) {
		tiles = new Tile[width][height];
		this.width = width;
		this.height = height;
		this.ents = new LinkedList<Entity>();
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				tiles[x][y] = new TileBlack(x, y);
			}
		}
	}

	public Tile getTile(int x, int y) {
		if (0 <= x && x < width && 0 <= y && y < height) {
			return tiles[x][y];
		}
		return null;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void addEntity(Entity ent) {
		ents.add(ent);
	}

	public void removeEntity(Entity ent) {
		ents.remove(ent);
	}

	public LinkedList<Entity> getEntities() {
		return ents;
	}

	public void update(GameSpicedTea game, PlayingState play) {
		for (Entity e : ents) {
			e.update(game, play);
		}
	}

	public boolean isComlete() {
		// TODO Auto-generated method stub
		return false;
	}

	public Level getNextLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Tile[][] getTiles() {
		return tiles;
	}
}
