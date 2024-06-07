package com.tangent.sorting;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;

public class Settings {
    static final int settingsWidth = 2000;
    static final int settingsHeight = 2000;
    static final float settingsWidthMultiplier = 1 - Controller.widthMultiplier;



    private static Button startButton = new Button(10000, 100, 0, 0);




    static void render(ShapeRenderer sr) {
        startButton.render(sr);
    }

}
