package tk.sirtwinkles.spicedtea.components;

import tk.sirtwinkles.spicedtea.GameSpicedTea;
import tk.sirtwinkles.spicedtea.state.PlayingState;
import tk.sirtwinkles.spicedtea.state.TextDisplayState;

import com.badlogic.gdx.utils.OrderedMap;

public class XPComponent extends Component {
	public int xp;
	public int nextLevelXP;
	public int level;
	private boolean firstLevel;
	
	public XPComponent(OrderedMap<String, Object> config) {
		super(config);
		level = 1;
		nextLevelXP = 4;
		firstLevel = true;
	}

	@Override
	public void update(GameSpicedTea game, PlayingState play) {
		if (xp >= nextLevelXP) {
			++level;
			xp -= nextLevelXP;
			nextLevelXP = (level * level * level);
			
			if (owner.getComponent("health") != null) {
				HealthComponent hc = (HealthComponent) owner.getComponent("health");
				hc.maxHealth += level;
				hc.setHealth(hc.maxHealth);
			}
			
			if (firstLevel && owner.getID().equalsIgnoreCase("player")) {
				play.requestStateChange(new TextDisplayState("first_level_up", play));
				firstLevel = false;
			}
		}
	}

	@Override
	public void destroy(GameSpicedTea game, PlayingState play) {
		//Nothing to do here.
	}

}
