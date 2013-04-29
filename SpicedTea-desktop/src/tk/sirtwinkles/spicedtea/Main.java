package tk.sirtwinkles.spicedtea;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.lwjgl.opengl.Display;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "SpicedTea";
		cfg.useGL20 = false;
		//Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		cfg.width = 800;//d.width;
		cfg.height = 600;//d.height;
		cfg.fullscreen = false;
		
		new LwjglApplication(new GameSpicedTea(cfg.width, cfg.height), cfg);
	}
}
