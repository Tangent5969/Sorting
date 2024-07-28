package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.tangent.sorting.Controller;
import com.tangent.utils.Utils;

public class RecursiveBubbleSort {

    public static void bubbleSort(int[] array) {
        if (Controller.marker < -1) {
            Controller.marker = array.length;
        }
        Controller.marker = sort(array, Controller.marker);
        if (Controller.marker < 0) {
            Controller.start = false;
            Controller.marker = -2;
        }
        else {
            Gdx.graphics.requestRendering();
            try {
                Thread.sleep(Controller.speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static int sort(int[] array, int n) {
        if (n == 1)
            return -1;

        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (array[i] > array[i + 1]) {
                Utils.swap(array, i, i + 1);
                count++;
            }
        }

        if (count == 0) {
            return -1;
        }


       return  n - 1;
    }
}
