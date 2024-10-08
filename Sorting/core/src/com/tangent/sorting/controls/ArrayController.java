package com.tangent.sorting.controls;

import java.util.Random;

public class ArrayController {
    private static final Random rand = new Random();

    private int[] array;
    private int length;
    private int comparisons;
    private int swaps;
    private int writes;
    private int auxWrites;
    private long realTime;
    private long estimatedTime;
    private boolean sortingStatus;


    ArrayController(int length) {
        resize(length);
    }

    public void resetStatistics() {
        comparisons = 0;
        swaps = 0;
        writes = 0;
        auxWrites = 0;
        realTime = 0;
        estimatedTime = 0;
    }

    public void reset() {
        sortingStatus = false;
        populateArray();
        resetStatistics();
    }

    public void resize(int length) {
        array = new int[length];
        this.length = length;
        reset();
    }

    public void swap(int pos1, int pos2) {
        int temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
        if (sortingStatus) {
            swaps += 1;
            writes += 2;
        }
    }

    public void display() {
        System.out.println("Length : " + length);
        System.out.println("Comparisons : " + comparisons);
        System.out.println("Swaps : " + swaps);
        System.out.println("Writes : " + writes);
        System.out.println("Aux Writes : " + auxWrites);
        System.out.println("Real Time : " + timeFormat(realTime));
        System.out.println("Estimated Time : " + timeFormat(estimatedTime));
    }

    public String settingsDisplay() {
        return "\nComparisons\n" + comparisons + "\nSwaps\n" + swaps + "\nWrites\n" + writes + "\nAux Writes\n" + auxWrites;
    }

    private String timeFormat(long time) {
        long mills = time / 1000;
        int seconds = (int) (mills / 1000);
        int mins = seconds / 60;
        return "" + seconds;
    }

    public void shuffle() {
        for (int i = array.length - 1; i >= 0; i--) {
            swap(i, rand.nextInt(i, array.length));
        }
    }

    public void reverse() {
        for (int i = 0; i < (length + 1) / 2; i++) {
            swap(i, length - i - 1);
        }
    }

    public boolean isSorted() {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i+1]) {
                return false;
            }
        }
        return true;
    }

    public int getLength() {
        return length;
    }

    public int getElement(int pos) {
        return array[pos];
    }

    public void setElement(int pos, int num) {
        array[pos] = num;
        if (sortingStatus) {
            writes += 1;
        }
    }

    public void addComparisons(int num) {
        comparisons += num;
    }

    public int getComparisons() {
        return comparisons;
    }

    public void addSwaps(int num) {
        swaps += num;
    }

    public int getSwaps() {
        return swaps;
    }

    public void addWrites(int num) {
        writes += num;
    }

    public int getWrites() {
        return writes;
    }

    public void addAuxWrites(int num) {
        auxWrites += num;
    }

    public int getAuxWrites() {
        return auxWrites;
    }

    public void addRealTime(long num) {
        realTime += num;
    }

    public long getRealTime() {
        return realTime;
    }

    public void addEstimatedTime(long num) {
        estimatedTime += num;
    }

    public long getEstimatedTime() {
        return estimatedTime;
    }

    public void updateTime(long time, int delay) {
        realTime += time;
        estimatedTime += time - delay * 1000L;
    }

    public boolean isSorting() {
        return sortingStatus;
    }

    public void setSortingStatus(boolean status) {
        sortingStatus = status;
    }

    public Random getRand() {
        return rand;
    }

    private void populateArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }

}
