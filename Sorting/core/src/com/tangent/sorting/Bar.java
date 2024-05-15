package com.tangent.sorting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bar {

    private final int maxHeight = (int) (Controller.graphHeight * 0.8);
    private static int width;
    final int height;
    Color colour;
    Bar(int value) {
        this.height = (maxHeight / Controller.getTotalElements()) * value;
        this.colour = Color.LIGHT_GRAY;
    }

    static void setWidth() {
        width = Controller.graphWidth / Controller.getTotalElements();
    }

    public void render(int pos, ShapeRenderer sr) {
        sr.setColor(colour);
        sr.rect(pos * width, 0, width, height);
    }
}
