package tk.sirtwinkles.spicedtea.components;

import com.badlogic.gdx.utils.OrderedMap;

import tk.sirtwinkles.spicedtea.GameSpicedTea;
import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.entities.EntityFactory;
import tk.sirtwinkles.spicedtea.state.PlayingState;

public class InventoryComponent extends Component {
	private Entity[][] ents;
	private int width, height;

	public InventoryComponent(OrderedMap<String, Object> config) {
		super(config);
		int width = ((Float) config.get("width")).intValue();
		int height = ((Float) config.get("height")).intValue();
		this.width = width;
		this.height = height;
		ents = new Entity[width][height];

		// XXX:REMOVE
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; y += 2) {
				ents[x][y] = EntityFactory.buildEntity("sword");
				ents[x][y + 1] = ents[x][y];
			}
		}
	}

	@Override
	public void update(GameSpicedTea game, PlayingState play) {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy(GameSpicedTea game, PlayingState play) {
		for (Entity[] entlist : ents) {
			for (Entity ent : entlist) {
				ent.destroy(game, play);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public Entity[][] getContents() {
		return ents;
	}
}
