package tk.sirtwinkles.spicedtea.entities;

import java.util.HashMap;
import java.util.LinkedList;

import tk.sirtwinkles.spicedtea.GameSpicedTea;
import tk.sirtwinkles.spicedtea.components.Component;
import tk.sirtwinkles.spicedtea.state.PlayingState;

public class Entity {
	private String id;
	LinkedList<Component> components;
	HashMap<String, Component> compMap;
	
	public Entity (String id) {
		this.id = id;
		this.components = new LinkedList<Component>();
		this.compMap = new HashMap<String, Component>();
	}
	
	public void addComponent(Component comp) {
		comp.setOwner(this);
		components.add(comp);
		compMap.put(comp.getID().toLowerCase(), comp);
	}
	
	public Component getComponent(String id) {
		return compMap.get(id.toLowerCase());
	}
	
	public void update(GameSpicedTea game, PlayingState ps) {
		for (Component c : components) {
			c.update(game, ps);
		}
	}
}
