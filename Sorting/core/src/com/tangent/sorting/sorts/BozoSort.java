package com.tangent.sorting.sorts;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.Controller;
import com.tangent.sorting.IntColourPair;
import com.tangent.utils.Utils;

public class BozoSort {

    public static void bozoSort(int[] array) {
        Random rand = new Random();
        if (Controller.marker == -1) {
            if (!Utils.isSorted(array)) {
                int num1 = rand.nextInt(array.length);
                int num2 = rand.nextInt(array.length);

                Controller.specialBars.clear();
                Controller.specialBars.add(new IntColourPair(num1, Color.RED));
                Controller.specialBars.add(new IntColourPair(num2, Color.RED));

                Utils.swap(array, num1,num2);
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
