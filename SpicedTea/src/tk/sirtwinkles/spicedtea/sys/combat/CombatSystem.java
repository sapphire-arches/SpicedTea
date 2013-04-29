package tk.sirtwinkles.spicedtea.sys.combat;

import static tk.sirtwinkles.spicedtea.MathUtils.abs;
import static tk.sirtwinkles.spicedtea.MathUtils.random;

import java.util.LinkedList;

import tk.sirtwinkles.spicedtea.GameSpicedTea;
import tk.sirtwinkles.spicedtea.components.HealthComponent;
import tk.sirtwinkles.spicedtea.components.PositionComponent;
import tk.sirtwinkles.spicedtea.components.XPComponent;
import tk.sirtwinkles.spicedtea.state.PlayingState;
import tk.sirtwinkles.spicedtea.state.TextDisplayState;
import tk.sirtwinkles.spicedtea.sys.System;

public class CombatSystem extends System {
	private LinkedList<Attack> attacks;
	private LinkedList<Attack> completed;

	public CombatSystem() {
		super("combat");
		this.attacks = new LinkedList<Attack>();
		this.completed = new LinkedList<Attack>();
	}

	@Override
	public void run(GameSpicedTea game, PlayingState play) {
		completed.clear();
		while (attacks.size() > 0) {
			Attack attack = attacks.removeFirst();
			final XPComponent instigatorXP = (XPComponent)(attack.instigator.getComponent("xp"));
			final XPComponent targetXP = (XPComponent) attack.target.getComponent("xp");
			final int weaponPower = instigatorXP.level * 2; // Arbitrary, should be from weapons, but timez =(
			final double fudgeFactor = 16; //Arbitrary.
			final PositionComponent instigatorPos = (PositionComponent) attack.instigator.getComponent("position");
			final PositionComponent targetPos = (PositionComponent) attack.target.getComponent("position");

			HealthComponent hc = (HealthComponent) attack.target.getComponent("health");
			if (abs(instigatorPos.x - targetPos.x) + abs(instigatorPos.y - targetPos.y) <= 1 && hc.getHealth() > 0) {
				final double damageFactor = (instigatorXP.level - targetXP.level) / fudgeFactor + random.nextDouble();
				int dmg = (int)(weaponPower * damageFactor * damageFactor);
				hc.damage(dmg);
				((XPComponent)attack.instigator.getComponent("xp")).xp += dmg;
				((XPComponent)attack.instigator.getComponent("xp")).update(game, play);
				
				if (hc.getHealth() <= 0) {
					play.getWorld().getCurrent().removeEntity(attack.target);
					attack.target.destroy(game, play);
					((HealthComponent)attack.instigator.getComponent("health")).damage(-hc.maxHealth / 2);
					if (attack.target.getID().equalsIgnoreCase("player")) {
						play.requestStateChange(new TextDisplayState("player_death", null));
					}
				}
				completed.add(attack);
				java.lang.System.out.println(attack.instigator.getID() + " hit " + attack.target.getID() + " for " + dmg + " factor: " + damageFactor);
			}
		}
	}

	public void queueAttack(Attack attack) {
		this.attacks.addLast(attack);
	}
	
	public LinkedList<Attack> getCompletedAttacks() {
		return completed;
	}
}