package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.Controller;
import com.tangent.sorting.IntColourPair;
import com.tangent.utils.Utils;

public class RecursiveBubbleSort2 {
    private static int increment = 0;
    private static int count = 0;
    private static int size = 0;

    public static void bubbleSort(int[] array) {
        if (Utils.isSorted(array)) {
            Controller.start = false;
            Controller.specialBars.clear();
            Gdx.graphics.requestRendering();
            return;
        }
        if (Controller.marker < 0) {
            Controller.marker = array.length;
        }
        if (array.length != size) {
            increment = 0;
            size = array.length;
        }
        Controller.marker = sort(array, Controller.marker);
        if (Controller.marker < -1) {
            Controller.start = false;
            Controller.marker = -2;
            increment = 0;
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

        if (n == 1) {
            System.out.println(1);
            return -1;
        }


        int currentIncrement = increment;
        Controller.specialBars.clear();
        if (increment != n - 1) {
            Controller.specialBars.add(new IntColourPair(increment, Color.RED));
            if (array[increment] > array[increment + 1]) {
                Utils.swap(array, increment, increment + 1);
                count++;
                Controller.specialBars.add(new IntColourPair(increment + 1, Color.RED));

            }
            increment++;
        } else {
            if (count == 0) {
                System.out.println(2);
                return -1;
            }
            increment = 0;
            count = 0;
        }


        if (currentIncrement > increment) {
            System.out.println(3);
            return n - 1;
        } else {
            System.out.println(4);
            System.out.println(n);
            return n;
        }

    }
}
