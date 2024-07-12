package com.tangent.sorting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bar {

    private static final int maxHeight = (int) (Controller.graphHeight * 0.9);
    private static int width;
    private final int height;
    Color colour;
    Bar(int value) {
        this.height = (maxHeight / Controller.totalElements) * value;
        this.colour = Color.LIGHT_GRAY;
    }

    static void setWidth() {
        width = (int) (Controller.graphWidth * Controller.widthMultiplier) / Controller.totalElements;
    }

    public static int getWidth() {
        return width;
    }

    public void render(int pos, ShapeRenderer sr) {
        sr.setColor(colour);
        sr.rect(pos * width, 0, width, height);
    }
}
