package tk.sirtwinkles.spicedtea.input;

public class TouchEvent {
	private int x, y, pointer;
	private boolean pressed;
	
	public TouchEvent(int x, int y, int pointer, boolean pressed) {
		this.x = x;
		this.y = y;
		this.pointer = pointer;
		this.pressed = pressed;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPointer() {
		return pointer;
	}
	
	public boolean isPressEvent() {
		return pressed;
	}
}
