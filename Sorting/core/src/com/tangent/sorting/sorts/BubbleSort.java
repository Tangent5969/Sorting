package com.tangent.sorting.sorts;

import com.tangent.utils.Utils;

public class BubbleSort {

    public BubbleSort(int[] array) {
            int end = array.length-1;
            boolean swap;
            do {
                swap = false;
                for (int i = 0; i < end; i++) {
                    if (array[i] > array[i + 1]) {
                        Utils.swap(array, i, i + 1);
                        swap = true;
                    }
                }
                end--;
            }
            while (swap);
    }
}
