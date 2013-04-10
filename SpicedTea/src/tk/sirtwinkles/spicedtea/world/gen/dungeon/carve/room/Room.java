package tk.sirtwinkles.spicedtea.world.gen.dungeon.carve.room;

public class Room {
	public int x, y, w, h;
	public String feature;
	
	public Room(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public String toString() {
		return "[Room:" + x + " " + y + " " + w + " " + h + " " + feature + "]";
	}
}
