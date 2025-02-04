package com.tangent.sorting.ui.visual;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;

import java.util.ArrayList;

public class Bar {

    private static final int maxHeight = (int) (MainController.height * 0.9);
    private static float width;
    private final int height;
    private final Color colour;

    public Bar(int value) {
        this.height = (maxHeight / MainController.arrayController.getLength()) * value;
        this.colour = Color.LIGHT_GRAY;
    }

    public Bar(int value, Color colour) {
        this.height = (maxHeight / MainController.arrayController.getLength()) * value;
        this.colour = colour;
    }

    public static void setWidth() {
        width = (MainController.width * MainController.widthMultiplier) / MainController.arrayController.getLength();
    }

    public static float getWidth() {
        return width;
    }

    public void render(int pos, ShapeRenderer sr) {
        sr.setColor(colour);
        sr.rect(pos * width, 0, width, height);
    }

    public static void renderArray(ArrayController arrayController, ArrayList<IntColourPair> specialElements, Object lock, ShapeRenderer sr) {
        for (int i = 0; i < arrayController.getLength(); i++) {
            new Bar(arrayController.getElement(i)).render(i, sr);
        }
        synchronized (lock) {
            for (IntColourPair pair : specialElements) {
                if (pair == null) {
                    continue;
                }
                new Bar(arrayController.getElement(pair.getNum()), pair.getColour()).render(pair.getNum(), sr);
            }
        }
    }
}
