package com.tangent.sorting;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;

public class Settings {
    int startX = (int) (Controller.graphWidth * Controller.widthMultiplier);


    private static Button startButton = new Button(200, 100, 0, 0, Button.ButtonMethod.Start);


    static void render(ShapeRenderer sr) {
        startButton.render(sr);
    }

}
