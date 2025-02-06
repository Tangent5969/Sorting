package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class QuickSort extends Sort {

    public QuickSort(ArrayController arrayController) {
        super(arrayController, "Quick");
    }

    @Override
    public void run() {
        arrayController.setSortingStatus(true);
        arrayController.startTimer();
        sort(0, arrayController.getLength() - 1);
        finished();
    }

    private void sort(int start, int end) {
        // pos[0] = left, pos[1] = right
        int[] pos = partition(start, end);
        if (start < pos[1]) sort(start, pos[1]);
        if (end > pos[0]) sort(pos[0], end);
    }


    private int[] partition(int left, int right) {
        int mid = (left + right) / 2;
        int pivot = arrayController.getElement(mid);

        MainController.specialElementsClear();
        MainController.specialElementsAdd(new IntColourPair(mid, Color.GREEN));
        MainController.specialElementsAdd(new IntColourPair(left, Color.RED));
        MainController.specialElementsAdd(new IntColourPair(right, Color.RED));

        while (left <= right) {
            while (arrayController.getElement(left) < pivot) {
                arrayController.addComparisons(1);
                MainController.specialElementsSet(1, new IntColourPair(left, Color.RED));
                MainController.audio.playSound(arrayController.getElement(left));
                update();

                left++;
            }
            arrayController.addComparisons(1);

            while (arrayController.getElement(right) > pivot) {
                arrayController.addComparisons(1);
                MainController.specialElementsSet(2, new IntColourPair(right, Color.RED));
                MainController.audio.playSound(arrayController.getElement(right));
                update();

                right--;
            }
            arrayController.addComparisons(1);

            if (left <= right) {
                MainController.specialElementsSet(1, new IntColourPair(left, Color.RED));
                MainController.specialElementsSet(2, new IntColourPair(right, Color.RED));
                update();

                arrayController.swap(left, right);
                update();

                left++;
                right--;
            }
        }

        return new int[]{left, right};
    }
}
