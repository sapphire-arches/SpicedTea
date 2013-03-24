package tk.sirtwinkles.spicedtea.sys.render;

import tk.sirtwinkles.spicedtea.GraphicsContext;
import tk.sirtwinkles.spicedtea.components.ImageComponent;

public class ImageComponentRenderer {
	private ImageComponent comp;
	
	public ImageComponentRenderer(ImageComponent comp) {
		this.comp = comp;
	}
	
	public void render(GraphicsContext context) {
		//TODO: Figure out where to draw sprites.
		context.getBatch().draw(comp.getImage(), 0, 0);
	}
}
