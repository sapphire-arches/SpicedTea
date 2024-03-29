package tk.sirtwinkles.spicedtea.components;

import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.sys.render.ImageComponentRenderer;

import com.badlogic.gdx.utils.OrderedMap;

public class ImageRenderComponent extends RenderComponent {

	public ImageRenderComponent(OrderedMap<String, Object> config) {
		super(new ImageComponentRenderer(), config);
	}

	@Override
	public void setOwner(Entity owner) {
		super.setOwner(owner);
		ImageComponentRenderer cpt = (ImageComponentRenderer)this.ren;
		cpt.setComponent((ImageComponent) owner.getComponent("image"));
	}
}
