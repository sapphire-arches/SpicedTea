package tk.sirtwinkles.spicedtea.components;

import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.sys.render.HealthRenderer;
import tk.sirtwinkles.spicedtea.sys.render.ImageComponentRenderer;

import com.badlogic.gdx.utils.OrderedMap;

public class HealthRenderComponent extends RenderComponent {

	public HealthRenderComponent(OrderedMap<String, Object> config) {
		super(new HealthRenderer(), config);
	}

	@Override
	public void setOwner(Entity owner) {
		super.setOwner(owner);
		HealthRenderer cpt = (HealthRenderer)this.ren;
		cpt.setComponent((HealthComponent) owner.getComponent("health"));
	}
}
