package com.tangent.sorting.controls;

import com.tangent.utils.Utils;

public class ArrayController {
    private int[] array;
    private int length;
    private int comparisons;
    private int swaps;
    private int writes;


    ArrayController(int length) {
        resize(length);
    }

    public void reset() {
        Utils.populateArray(array);
        comparisons = 0;
        swaps = 0;
        writes = 0;
    }

    public void resize(int length) {
        array = new int[length];
        this.length = length;
        reset();
    }

    public void swap(int pos1, int pos2) {
        Utils.swap(array, pos1, pos2);
        swaps += 1;
        writes += 2;
    }

    public void display() {
        System.out.println("Comparisons : " + comparisons);
        System.out.println("Swaps : " + swaps);
        System.out.println("Writes : " + writes);
    }

    public void shuffle() {
        Utils.shuffle(array);
    }

    public void reverse() {
        Utils.reverse(array);
    }

    public boolean isSorted() {
        return Utils.isSorted(array);
    }

    public int getLength() {
        return length;
    }

    public int getElement(int pos) {
        return array[pos];
    }

    public void addComparison(int num) {
        comparisons += num;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getSwaps() {
        return swaps;
    }

    public void addWrite(int num) {
        writes += num;
    }

    public int getWrites() {
        return writes;
    }

}
