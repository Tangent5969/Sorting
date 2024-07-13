package com.tangent.sorting;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Controller {
    static final int width = 10000;
    static final int height = 10000;
    static final float widthMultiplier = 0.9f;
    static final int minElements = 4;
    static final int maxElements = 1024;
    static int totalElements;
    static int speed;
    static int[] mainArray;

    static void setArray() {
        mainArray = new int[totalElements];
        Bar.setWidth();
        Utils.populateArray(mainArray);
    }

    static void logic() {
        totalElements = 10;
        setArray();
        //Utils.reverse(mainArray);
        Utils.shuffle(mainArray);
    }

    static void renderArray(ShapeRenderer sr) {
        for (int i = 0; i < totalElements; i++) {
            new Bar(mainArray[i]).render(i, sr);
        }
    }

}
