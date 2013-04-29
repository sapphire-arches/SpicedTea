package tk.sirtwinkles.spicedtea.sys.render;

import static tk.sirtwinkles.spicedtea.sys.render.LevelRenderer.TILE_SIZE;

import java.util.LinkedList;

import tk.sirtwinkles.spicedtea.Globals;
import tk.sirtwinkles.spicedtea.GraphicsContext;
import tk.sirtwinkles.spicedtea.sys.combat.Attack;
import tk.sirtwinkles.spicedtea.sys.combat.CombatSystem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class AttacksRenderer {
	private CombatSystem combat;
	private Texture gui;

	public AttacksRenderer(CombatSystem c) {
		this.combat = c;
		this.gui = Globals.assets.get("data/gui.png");
	}

	public void render(GraphicsContext context, Viewport view) {
		SpriteBatch sb = context.getBatch();
		Rectangle viewRect = view.getSizeAndPosition();
		final int w = (int) (viewRect.width / TILE_SIZE) + 1;
		final int h = (int) (viewRect.height / TILE_SIZE) + 1;
		final int tx = (int) (viewRect.x / TILE_SIZE);
		final int ty = (int) (viewRect.y / TILE_SIZE);

		LinkedList<Attack> attacks = combat.getCompletedAttacks();

		for (Attack a : attacks) {
			if (tx <= a.targetX && a.targetX <= tx + w) {
				if (ty <= a.targetY && a.targetY <= ty + h) {
					sb.draw(gui, (a.targetX - tx) * TILE_SIZE, (a.targetY - ty)
							* TILE_SIZE, TILE_SIZE, TILE_SIZE, 24, 24, 8, 8,
							false, true);
				}
			}
		}
	}
}
