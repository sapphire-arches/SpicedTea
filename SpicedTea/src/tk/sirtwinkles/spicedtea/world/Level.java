package tk.sirtwinkles.spicedtea.world;

import java.util.HashMap;
import java.util.LinkedList;

import tk.sirtwinkles.spicedtea.GameSpicedTea;
import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.state.PlayingState;
import tk.sirtwinkles.spicedtea.world.tile.Tile;
import tk.sirtwinkles.spicedtea.world.tile.TileBlack;
import tk.sirtwinkles.spicedtea.world.tile.TileClass;

public class Level {
	private Tile[][] tiles;
	private boolean[][] visible;
	private int width, height;
	private LinkedList<Entity> ents;
	private LinkedList<Entity> toRemove;
	private LinkedList<Entity> toAdd;
	private HashMap<String, Entity> entityMap;
	private boolean completed;

	public Level(int width, int height) {
		this.tiles = new Tile[width][height];
		this.visible = new boolean[width][height];
		this.width = width;
		this.height = height;
		this.ents = new LinkedList<Entity>();
		this.toAdd = new LinkedList<Entity>();
		this.toRemove = new LinkedList<Entity>();
		this.entityMap = new HashMap<String, Entity>();
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

	public boolean isVisible(int x, int y) {
		if (0 <= x && x < width && 0 <= y && y < height) {
			return visible[x][y];
		}
		return false;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void addEntity(Entity ent) {
		addEntity(ent, true);
	}
	
	public void addEntity(Entity ent, boolean queue) {
		String ename = ent.getID().toLowerCase();
		if (entityMap.containsKey(ename)) {
			throw new IllegalArgumentException("Two entities with ID:" + ename);
		}
		if (!queue) {
			ents.add(ent);
			entityMap.put(ename, ent);
		} else {
			toAdd.add(ent);
		}
	}

	public void removeEntity(Entity ent) {
		toRemove.add(ent);
	}

	public LinkedList<Entity> getEntities() {
		return ents;
	}

	public Entity getEntity(String id) {
		return entityMap.get(id.toLowerCase());
	}

	public void update(GameSpicedTea game, PlayingState play) {
		for (Entity ent : toRemove) {
			ents.remove(ent);
			entityMap.remove(ent.getID().toLowerCase());
		}
		
		for (Entity ent : toAdd) {
			String ename = ent.getID().toLowerCase();
			ents.add(ent);
			entityMap.put(ename, ent);
		}
		
		toRemove.clear();
		toAdd.clear();
		
		for (Entity e : ents) {
			e.update(game, play);
		}
	}

	public boolean isComlete() {
		return completed;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void onEntityMove(int x, int y, Entity entity) {
		tiles[x][y].onEntityStep(entity, this);
	}

	public void floodFillVisiblity(int x, int y) {
		Entity player = getEntity("player");
		LinkedList<Point> queue = new LinkedList<Point>();
		queue.add(Point.getPoint(x, y));
		while (!queue.isEmpty()) {
			Point p = queue.remove();
			if (0 <= p.x && p.x < width && 0 <= p.y && p.y < height) {
				if (!visible[p.x][p.y]) {
					TileClass cls = tiles[p.x][p.y].getTileClass();
					if (tiles[p.x][p.y].isPassable(player, this) && cls != TileClass.DOOR) {
						visible[p.x][p.y] = true;
						queue.add(Point.getPoint(p.x + 0, p.y + 1));
						queue.add(Point.getPoint(p.x + 0, p.y - 1));
						queue.add(Point.getPoint(p.x + 1, p.y + 0));
						queue.add(Point.getPoint(p.x + 1, p.y + 1));
						queue.add(Point.getPoint(p.x + 1, p.y - 1));
						queue.add(Point.getPoint(p.x - 1, p.y + 0));
						queue.add(Point.getPoint(p.x - 1, p.y + 1));
						queue.add(Point.getPoint(p.x - 1, p.y - 1));
					} else if (cls == TileClass.DOOR || cls == TileClass.WALL) {
						visible[p.x][p.y] = true;
						//But we don't want to continue searching in this direction
					}
				}
			}
			Point.free(p);
		}
	}

	public void complete() {
		this.completed = true;
	}
}
