package tk.sirtwinkles.spicedtea.world.tile;

import static tk.sirtwinkles.spicedtea.MathUtils.random;

public class AbstractWall extends Tile {

	private WallSide side;
	private int baseImgX, baseImgY;
	
	public AbstractWall(String id, int baseImgX, int baseImgY, int x, int y, WallSide side) {
		super(id, 0, 0, x, y);
		this.side = side;
		this.baseImgX = baseImgX; this.baseImgY = baseImgY;
		setImage();
	}
	public int getImageX() {
		return imgX;
	}
	
	private void setImage() {
		switch(side) {
		case N : imgX = baseImgX + 1 + random.nextInt(2); imgY = baseImgY; break;
		case E : imgX = baseImgX + 3; imgY = baseImgY + 1 + random.nextInt(2); break;
		case S : imgX = baseImgX + 1 + random.nextInt(2); imgY = baseImgY + 3; break;
		case W : imgX = baseImgX + 0; imgY = baseImgY + 1 + random.nextInt(2); break;
		case NE: imgX = baseImgX + 0; imgY = baseImgY; break;
		case NW: imgX = baseImgX + 3; imgY = baseImgY; break;
		case SE: imgX = baseImgX + 0; imgY = baseImgY + 3; break;
		case SW: imgX = baseImgX + 3; imgY = baseImgY + 3; break;
		case NEO: imgX = baseImgX + 1; imgY = baseImgY + 2; break;
		case NWO: imgX = baseImgX + 2; imgY = baseImgY + 2; break;
		case SEO: imgX = baseImgX + 1; imgY = baseImgY + 1; break;
		case SWO: imgX = baseImgX + 2; imgY = baseImgY + 1; break;
		}
	}
}
