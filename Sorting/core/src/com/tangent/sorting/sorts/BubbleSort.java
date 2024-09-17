package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.Controller;
import com.tangent.sorting.IntColourPair;
import com.tangent.utils.Utils;


public class BubbleSort extends Sort {

    public BubbleSort(int[] array) {
        super(array);
    }

    public void run() {
            int end = array.length-1;
            boolean swap;
            do {
                swap = false;
                    for (int i = 0; i < end; i++) {
                        Controller.specialBarsClear();
                        Controller.specialBarsAdd(new IntColourPair(i, Color.RED));
                        Gdx.graphics.requestRendering();
                        checkStatus();
                        Controller.specialBarsSet(0, new IntColourPair(i + 1, Color.RED));
                        Gdx.graphics.requestRendering();
                        checkStatus();
                        if (array[i] > array[i + 1]) {
                            Utils.swap(array, i, i + 1);
                            swap = true;
                            Controller.specialBarsAdd(new IntColourPair(i, Color.RED));
                            Gdx.graphics.requestRendering();
                            checkStatus();
                        }
                    }
                end--;
            }
            while (swap);
            greenBars();
            Controller.sorting = false;
            Controller.sorted = true;
    }
}
