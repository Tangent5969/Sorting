package com.tangent.sorting;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Controller {
    static final int graphWidth = 2048;
    static final int graphHeight = 1000;
    private static int totalElements;
    private static int speed;
    public static int[] mainArray;

    public void setArray() {
        mainArray = new int[totalElements];
        Utils.populateArray(mainArray);
    }

    public void logic() {
        totalElements = 10;
        setArray();
    }

    public void renderArray(ShapeRenderer sr) {
        for (int i = 0; i < totalElements; i++) {
            new Bar(mainArray[i]).render(i, sr);
        }
    }

    public static int getTotalElements() {
        return totalElements;
    }

    public static int getSpeed() {
        return speed;
    }
}
