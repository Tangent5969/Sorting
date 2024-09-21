package com.tangent.sorting;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.tangent.sorting.controls.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Sound Of Sorting");
		config.setWindowIcon("icon.png");
		config.setForegroundFPS(60);
		config.setWindowSizeLimits(640, 480, -1, -1);
		new Lwjgl3Application(new Main(), config);
	}
}
