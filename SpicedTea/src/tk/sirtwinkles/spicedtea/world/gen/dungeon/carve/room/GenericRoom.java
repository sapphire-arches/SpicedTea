package tk.sirtwinkles.spicedtea.world.gen.dungeon.carve.room;

import static tk.sirtwinkles.spicedtea.MathUtils.random;
import static tk.sirtwinkles.spicedtea.world.gen.TileSetProvider.*;
import tk.sirtwinkles.spicedtea.components.PositionComponent;
import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.entities.EntityFactory;
import tk.sirtwinkles.spicedtea.world.Level;

public class GenericRoom implements RoomFeature {

	@Override
	public void generate(Room in, int[][] data, Level level) {
		int quota = random.nextInt(2) + 1;
		int genned = 0;
		while (genned < quota) {
			int rx = random.nextInt(in.w - 2) + 1 + in.x;
			int ry = random.nextInt(in.h - 2) + 1 + in.y;
			if (data[rx][ry] == FLOOR) {
				String[] ents = {"spider", "imp", "cyclops", "thing"};
				Entity toAdd = EntityFactory.buildEntity(ents[random.nextInt(level.getLevel() + 1)]);
				PositionComponent pc = (PositionComponent) toAdd.getComponent("position");
				pc.x = rx;
				pc.y = ry;
				level.addEntity(toAdd);
				++genned;
			}
		}
	}
}

