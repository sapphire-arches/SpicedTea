package tk.sirtwinkles.spicedtea;

import tk.sirtwinkles.spicedtea.input.InputQueue;
import tk.sirtwinkles.spicedtea.state.AssetLoadState;
import tk.sirtwinkles.spicedtea.state.GameState;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

public class GameSpicedTea implements ApplicationListener {
	private GameState currentState;
	/**
	 * The timestep. We always step at 60 fps.
	 */
	public static final double TIMESTEP = 0.01666666666;
	/**
	 * Time we didn't use last frame.
	 */
	private double overflowTime;
	
	/**
	 * The graphics context.
	 */
	private GraphicsContext context;
	
	/**
	 * Hacks to make initialisation happen in init.
	 */
	private int dontusethiswidth,dontusethisheight;
	/**
	 * Input object.
	 */
	private InputQueue input;
	
	/**
	 * Makes a new spiced tea game object.
	 * @param width The width of the screen.
	 * @param height The height of the screen
	 */
	public GameSpicedTea(int width, int height) {
		this.dontusethiswidth = width;
		this.dontusethisheight = height;
		Globals.game = this;
	}
	
	@Override
	public void create() {
		overflowTime = 0;
		context = new GraphicsContext();
		context.onResize(dontusethiswidth, dontusethisheight);
		this.input = new InputQueue();
		Gdx.input.setInputProcessor(input); // register input
		currentState = new AssetLoadState();
		currentState.onEnterState(this);
	}

	@Override
	public void dispose() {
		currentState.onLeaveState(this);
		context.dispose();
	}

	@Override
	public void render() {
		double time = Gdx.graphics.getDeltaTime() + overflowTime;
		long start = TimeUtils.nanoTime();
		while (time > TIMESTEP) {
			currentState.tick(this);
			if (currentState.switchState(this)) {
				currentState.onLeaveState(this);
				currentState = currentState.getNextState(this);
				currentState.onEnterState(this);
				break;
			}
			time -= TIMESTEP;
		}
		long end = TimeUtils.nanoTime();
		overflowTime = time - ((end - start) / 1000000000.);
		currentState.render(this);
	}

	@Override
	public void resize(int width, int height) {
		context.onResize(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	public GraphicsContext getContext() {
		return context;
	}
	
	public InputQueue getInput() {
		return input;
	}

	public GameState getCurrentState() {
		return currentState;
	}
}