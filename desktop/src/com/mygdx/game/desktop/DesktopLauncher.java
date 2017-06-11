package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=640;
		config.height=360;
		config.foregroundFPS=60;
		config.backgroundFPS=60;
		String type="desktop";
		new LwjglApplication(new Main(type), config);
	}
}
