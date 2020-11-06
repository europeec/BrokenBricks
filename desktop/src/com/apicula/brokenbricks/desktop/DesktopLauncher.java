package com.apicula.brokenbricks.desktop;

import com.apicula.brokenbricks.AdditionalFiles.Const;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.apicula.brokenbricks.BrokenBricks;

public class DesktopLauncher {
	Const CONST;
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "BrokenBricks";
		config.width = 500;
		config.height = 800;
		config.resizable = false;
		new LwjglApplication(new BrokenBricks(), config);
	}
}
