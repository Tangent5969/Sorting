package com.tangent.sorting;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Controller {
    static final int graphWidth = 2048;
    static final int graphHeight = 1000;
    static final float widthMultiplier = 0.9f;
    static int totalElements;
    static int speed;
    static int[] mainArray;

    static void setArray() {
        mainArray = new int[totalElements];
        Bar.setWidth();
        Utils.populateArray(mainArray);
    }

    static void logic() {
        totalElements = 100;
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
