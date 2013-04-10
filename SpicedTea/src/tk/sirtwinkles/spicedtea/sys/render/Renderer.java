package tk.sirtwinkles.spicedtea.sys.render;

import tk.sirtwinkles.spicedtea.GraphicsContext;
import tk.sirtwinkles.spicedtea.state.PlayingState;

public abstract interface Renderer {
	public void render(GraphicsContext context, PlayingState state, Viewport view);
}
