package com.tangent.sorting.sorts;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.tangent.sorting.Controller;
import com.tangent.utils.Utils;

public class BozoSort {

    public static void bozoSort(int[] array) {
        Random rand = new Random();
        if (Controller.marker == -1) {
            if (!Utils.isSorted(array)) {
                Utils.swap(array, rand.nextInt(array.length), rand.nextInt(array.length));
                Gdx.graphics.requestRendering();
                try {
                    Thread.sleep(Controller.speed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                Controller.start = false;
                Controller.marker = -2;
            }
        }

    }
}
