package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.ui.visual.IntColourPair;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;

public class QuickSort extends Sort {

    public QuickSort(ArrayController arrayController) {
        super(arrayController, "Quick");
    }

    @Override
    public void run() {
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

        MainController.specialBarsClear();
        MainController.specialBarsAdd(new IntColourPair(mid, Color.GREEN));
        MainController.specialBarsAdd(new IntColourPair(left, Color.RED));
        MainController.specialBarsAdd(new IntColourPair(right, Color.RED));

        while (left <= right) {
            while (arrayController.getElement(left) < pivot) {
                arrayController.addComparisons(1);
                MainController.specialBarsSet(1, new IntColourPair(left, Color.RED));
                MainController.audio.playSound(arrayController.getElement(left));
                Gdx.graphics.requestRendering();
                checkStatus();

                left++;
            }
            arrayController.addComparisons(1);

            while (arrayController.getElement(right) > pivot) {
                arrayController.addComparisons(1);
                MainController.specialBarsSet(2, new IntColourPair(right, Color.RED));
                MainController.audio.playSound(arrayController.getElement(right));
                Gdx.graphics.requestRendering();
                checkStatus();

                right--;
            }
            arrayController.addComparisons(1);

            if (left <= right) {
                MainController.specialBarsSet(1, new IntColourPair(left, Color.RED));
                MainController.specialBarsSet(2, new IntColourPair(right, Color.RED));
                Gdx.graphics.requestRendering();
                checkStatus();

                arrayController.swap(left, right);
                Gdx.graphics.requestRendering();
                checkStatus();

                left++;
                right--;
            }
        }

        return new int[] {left, right};
    }
}
