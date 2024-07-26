package com.tangent.sorting;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.tangent.utils.Utils;

public class Controller {
    static final int width = 10000;
    static final int height = 10000;
    static final float widthMultiplier = 0.9f;
    static final int minElements = 4;
    static final int maxElements = 1024;
    static int totalElements = 10;
    static int speed;
    public static int[] mainArray;

    static void setArray() {
        mainArray = new int[totalElements];
        Bar.setWidth();
        Utils.populateArray(mainArray);
    }



    public static void renderArray(ShapeRenderer sr) {
        for (int i = 0; i < totalElements; i++) {
            new Bar(mainArray[i]).render(i, sr);
        }
    }

    public static void setTotalElements(int totalElements) {
        Controller.totalElements = totalElements;
        setArray();
    }

    public static void setSpeed(int speed) {
        Controller.speed = speed;
    }
}
