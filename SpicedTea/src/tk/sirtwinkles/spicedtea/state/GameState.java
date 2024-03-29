package tk.sirtwinkles.spicedtea.state;

import tk.sirtwinkles.spicedtea.GameSpicedTea;

public interface GameState {
	/**
	 * Called when the state is entered.
	 * Perform initialization here.
	 * @param game Game object
	 */
	public void onEnterState(GameSpicedTea game);
	/**
	 * Called when the state is left.
	 * Perform cleanup here.
	 * @param game Game object.
	 */
	public void onLeaveState(GameSpicedTea game);
	/**
	 * Called to determine if the state should be left.
	 * @param game Game object.
	 * @return <code>true</code> if the state should be left, <code>false</code> otherwise.
	 */
	public boolean switchState(GameSpicedTea game);
	/**
	 * Called when the state should draw itself.
	 * @param game The game object.
	 */
	public void render(GameSpicedTea game);
	/**
	 * Called when the state should run gametick events.
	 * @param game
	 */
	public void tick(GameSpicedTea game);
	/**
	 * Called to get the state that should be entered next.
	 * Only called if <code>GameState{@link #switchState(GameSpicedTea)}</code> returns true.
	 * @param game Game object.
	 * @return The next state.
	 */
	public GameState getNextState(GameSpicedTea game);
}
