package com.bepis.tetris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bepis.tetris.TetrisXD;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// Set to vertical orientation
		config.height = 800;
		config.width = 280;

		new LwjglApplication(new TetrisXD(), config);
	}
}
