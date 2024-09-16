package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.tangent.sorting.Controller;
import com.tangent.utils.Utils;

import static com.tangent.sorting.Controller.lock;

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
                    if (array[i] > array[i + 1]) {
                        Utils.swap(array, i, i + 1);
                        swap = true;
                        Gdx.graphics.requestRendering();
                    }
                    checkStatus();
                }
                end--;
            }
            while (swap);
            Controller.sorting = false;
    }
}
