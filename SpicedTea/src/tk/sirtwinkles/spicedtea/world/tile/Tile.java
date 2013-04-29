package tk.sirtwinkles.spicedtea.world.tile;

import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.world.Level;

public abstract class Tile extends Entity {
	private String id;
	/**
	 * Image coordinates in terrain.png
	 */
	protected int imgX;
	protected int imgY;
	/**
	 * Position in the world;
	 */
	protected int x;
	protected int y;

	/**
	 * Constructs a new tile object.
	 * 
	 * @param id
	 *            Name of this tile.
	 * @param imgX
	 *            Image coordinates in terrain.png, in blocks of 16
	 * @param imgY
	 *            Image coordinates in terrain.png, in blocks of 16
	 * @param x
	 *            X position in the level.
	 * @param y
	 *            Y position in the level.
	 */
	public Tile(String id, int imgX, int imgY, int x, int y) {
		super("id");
		this.id = id;
		this.imgX = imgX;
		this.imgY = imgY;
		this.x = x;
		this.y = y;
	}

	public int getImageX() {
		return imgX;
	}

	public int getImageY() {
		return imgY;
	}
	
	public String getID() {
		return id;
	}
	
	public abstract void onEntityStep(Entity step, Level in);
	public abstract boolean isPassable(Entity ent, Level in);
	public abstract boolean isPathable(Entity ent, Level in);
	public abstract TileClass getTileClass();
}