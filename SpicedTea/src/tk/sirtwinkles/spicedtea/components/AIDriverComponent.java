package tk.sirtwinkles.spicedtea.components;

import static tk.sirtwinkles.spicedtea.MathUtils.random;
import tk.sirtwinkles.spicedtea.GameSpicedTea;
import tk.sirtwinkles.spicedtea.state.PlayingState;
import tk.sirtwinkles.spicedtea.world.Level;
import tk.sirtwinkles.spicedtea.world.Point;

import com.badlogic.gdx.utils.OrderedMap;

public class AIDriverComponent extends Component {

	public AIDriverComponent(OrderedMap<String, Object> config) {
		super(config);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(GameSpicedTea game, PlayingState play) {
		// Random movement
		MoverComponent mc = (MoverComponent) owner.getComponent("mover");
		if (!mc.hasQueuedMoves()) {
			Level l = play.getWorld().getCurrent();
			PositionComponent pc = (PositionComponent) owner
					.getComponent("position");
			int rx = random.nextInt(5) - 2 + pc.x;
			int ry = random.nextInt(5) - 2 + pc.y;
			
			if (rx < 0 || rx >= l.getWidth() || ry < 0 || ry >= l.getHeight()) return;

			//System.out.println(rx + " " + ry);
			if (l.getTile(rx, ry).isPassable(this.owner, l)) {
				Point start = Point.getPoint(pc.x, pc.y);
				Point end = Point.getPoint(rx, ry);
				mc.path(start, end, l);
			}
		}
	}

	@Override
	public void destroy(GameSpicedTea game, PlayingState play) {
		// TODO Auto-generated method stub

	}

}
