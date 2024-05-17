package com.tangent.sorting;
import java.util.Random;
public class Utils {
    static void populateArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }

    static void swap(int[] array, int pos1, int pos2) {
        int temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }

    static void reverse(int[] array) {
        int length = array.length - 1;
        for (int i = 0; i < (length + 1)/2; i++) {
            swap(array, i, length - i);
        }
    }

    // based of fisher yates shuffle
    static void shuffle(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, i, rand.nextInt(i, array.length));
        }
    }
}
