package tk.sirtwinkles.spicedtea.sys.combat;

import tk.sirtwinkles.spicedtea.components.PositionComponent;
import tk.sirtwinkles.spicedtea.entities.Entity;

public class Attack {
	Entity instigator;
	Entity target;
	public int targetX, targetY;
	
	public Attack(Entity instigator, Entity target) {
		if (instigator.getComponent("xp") == null) {
			throw new IllegalArgumentException("Instigator did not have a XP component.");
		}
		if (instigator.getComponent("health") == null) {
			throw new IllegalArgumentException("Instigator did not have a health component.");
		}

		if (target.getComponent("xp") == null) {
			throw new IllegalArgumentException("Target did not have a XP component.");
		}
		if (target.getComponent("health") == null) {
			throw new IllegalArgumentException("Target did not have a health component.");
		}
		if (target.getComponent("position") != null) {
			PositionComponent pc = (PositionComponent) target.getComponent("position");
			targetX = pc.x;
			targetY = pc.y;
		}
		
		this.instigator = instigator;
		this.target = target;
	}
}
