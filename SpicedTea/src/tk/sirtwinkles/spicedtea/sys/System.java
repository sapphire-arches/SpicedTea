package tk.sirtwinkles.spicedtea.sys;

import tk.sirtwinkles.spicedtea.GameSpicedTea;

/**
 * A generic system.
 * @author bob_twinkles
 *
 */
public abstract class System {
	private String id;
	
	public System(String id) {
		this.id = id;
	}
	
	public String getID() {
		return id;
	}
	
	public abstract void run(GameSpicedTea game);
}
