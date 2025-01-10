package com.tangent.sorting;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.tangent.sorting.controls.Main;
import com.tangent.sorting.ui.visual.Image;

import java.io.IOException;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Sound Of Sorting");
		config.setWindowIcon("icon.png");
		config.setForegroundFPS(60);
		config.setWindowSizeLimits(640, 480, -1, -1);

		config.setWindowListener(new Lwjgl3WindowAdapter() {
			@Override
			public void filesDropped (String[] files) {
                try {
                    Image.selectImage(files[0]);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
		});
		new Lwjgl3Application(new Main(), config);
	}
}
