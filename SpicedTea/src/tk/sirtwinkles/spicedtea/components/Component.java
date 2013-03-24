package tk.sirtwinkles.spicedtea.components;

import tk.sirtwinkles.spicedtea.GameSpicedTea;
import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.state.PlayingState;

public abstract class Component {
	Entity owner;
	String id;
	
	public Component(String id) {
		this.id = id;
	}
	
	public void setOwner(Entity owner) {
		this.owner = owner;
	}
	
	public String getID() {
		return id;
	}
	
	public Entity getOwner() {
		return owner;
	}
	
	/**
	 * Perform whatever action this component performs.
	 * @param game The game.
	 */
	public abstract void update(GameSpicedTea game, PlayingState play);
}
