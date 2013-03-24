package tk.sirtwinkles.spicedtea.input;

public class KeyEvent {
	private int c;
	private boolean pressed;
	
	public KeyEvent(int c, boolean pressed) {
		this.c = c;
		this.pressed = pressed;
	}
	
	public int getC() {
		return c;
	}
	
	public boolean isPressEvent() {
		return pressed;
	}
}
