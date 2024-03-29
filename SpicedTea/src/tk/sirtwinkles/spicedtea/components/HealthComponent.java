package tk.sirtwinkles.spicedtea.components;

import com.badlogic.gdx.utils.OrderedMap;

import tk.sirtwinkles.spicedtea.GameSpicedTea;
import tk.sirtwinkles.spicedtea.state.PlayingState;

public class HealthComponent extends Component {
	private int health;
	private int ticksSinceLastDamage;
	public int maxHealth;
	
	public HealthComponent(OrderedMap<String, Object> config) {
		super(config);
		setHealth(maxHealth = ((Float)(config.get("max"))).intValue());
	}

	@Override
	public void update(GameSpicedTea game, PlayingState play) {
		if (ticksSinceLastDamage > 4 && health < maxHealth) {
			//++health;
		}
		++ticksSinceLastDamage;
	}

	@Override
	public void destroy(GameSpicedTea game, PlayingState play) {
		//Nothing to do here.
	}

	public int getHealth() {
		return health;
	}
	
	public void damage(int amount) {
		setHealth(this.health - amount);
	}

	public void setHealth(int health) {
		this.health = (health > maxHealth) ? maxHealth : health;
		ticksSinceLastDamage = 0;
	}

}
