package com.tangent.sorting.ui.visual;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tangent.sorting.controls.MainController;

public class Bar {

    private static final int maxHeight = (int) (MainController.height * 0.9);
    private static int width;
    private final int height;
    Color colour;
    public Bar(int value) {
        this.height = (maxHeight / MainController.arrayController.getLength()) * value;
        this.colour = Color.LIGHT_GRAY;
    }

    public Bar(int value, Color colour) {
        this.height = (maxHeight / MainController.arrayController.getLength()) * value;
        this.colour = colour;
    }

    public static void setWidth() {
        width = (int) (MainController.width * MainController.widthMultiplier) / MainController.arrayController.getLength();
    }

    public static int getWidth() {
        return width;
    }

    public void render(int pos, ShapeRenderer sr) {
        sr.setColor(colour);
        sr.rect(pos * width, 0, width, height);
    }
}
