package tk.sirtwinkles.spicedtea.components;

import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.sys.render.XPRenderer;

import com.badlogic.gdx.utils.OrderedMap;

public class XPRenderComponent extends RenderComponent {

	public XPRenderComponent(OrderedMap<String, Object> config) {
		super(new XPRenderer(), config);
	}

	@Override
	public void setOwner(Entity owner) {
		super.setOwner(owner);
		XPRenderer cpt = (XPRenderer) this.ren;
		cpt.setComponent((XPComponent) owner.getComponent("xp"));
	}
}
