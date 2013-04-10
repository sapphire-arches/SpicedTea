package tk.sirtwinkles.spicedtea.sys.render;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

import tk.sirtwinkles.spicedtea.GameSpicedTea;
import tk.sirtwinkles.spicedtea.GraphicsContext;
import tk.sirtwinkles.spicedtea.components.PositionComponent;
import tk.sirtwinkles.spicedtea.state.PlayingState;
import tk.sirtwinkles.spicedtea.sys.System;

import static tk.sirtwinkles.spicedtea.sys.render.LevelRenderer.TILE_SIZE;

public class RenderingSystem extends System {
	public static final float PXL_SCALE = 4f;
	private LinkedList<Renderer> componentRenderers;
	private Viewport view;
	private OrthographicCamera cam;
	private LevelRenderer levelRen;
	private PositionComponent center;

	public RenderingSystem(Viewport view, PlayingState state) {
		super("tk.sirtwinkles.spicedtea.sys.render.RenderSystem");
		this.componentRenderers = new LinkedList<Renderer>();
		this.view = view;
		this.cam = new OrthographicCamera(1, 1);
		Gdx.graphics.getGL10().glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		this.levelRen = new LevelRenderer(state.getWorld());
	}

	@Override
	public void run(GameSpicedTea game) {
		// setup of some vars.
		GL10 gl10 = Gdx.graphics.getGL10();
		GraphicsContext context = game.getContext();
		PlayingState state = (PlayingState) game.getCurrentState();

		view.resize(context.getWidth() / PXL_SCALE, context.getHeight()
				/ PXL_SCALE);
		Rectangle viewRect = view.getSizeAndPosition();
		if (center != null) {
			viewRect.x = (center.x * TILE_SIZE)
					- viewRect.width / 2;
			viewRect.x -= viewRect.x % TILE_SIZE;
			viewRect.y = (center.y * TILE_SIZE)
					- viewRect.height / 2;
			viewRect.y -= viewRect.y % TILE_SIZE;
		}
		if (viewRect.y < 0) viewRect.y = 0;
		if (viewRect.x < 0) viewRect.x = 0;
		if (viewRect.y + viewRect.height >= levelRen.getMaxY()) viewRect.y = levelRen.getMaxY() - viewRect.height;
		if (viewRect.x + viewRect.width >= levelRen.getMaxX()) viewRect.x = levelRen.getMaxX() - viewRect.width;
		cam.setToOrtho(true, viewRect.width, viewRect.height);
		//cam.translate(viewRect.x, viewRect.y);
		cam.update();
		context.getBatch().setProjectionMatrix(cam.projection);
		context.getBatch().setTransformMatrix(cam.view);

		gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
		context.begin(); // Begin rendering.
		levelRen.render(context, view);
		for (Renderer ren : componentRenderers) {
			ren.render(context, state, view);
		}
		context.end();
	}

	public void addRenderer(Renderer comp) {
		componentRenderers.add(comp);
	}

	public void removeRenderer(Renderer comp) {
		componentRenderers.remove(comp);
	}

	public void setCenter(PositionComponent pos) {
		this.center = pos;
	}

	public Viewport getView() {
		return view;
	}
}
