package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class SlowSort extends Sort {

    public SlowSort(ArrayController arrayController) {
        super(arrayController, "Slow");
    }

    @Override
    public void run() {
        arrayController.setSortingStatus(true);
        arrayController.startTimer();
        sort(0, arrayController.getLength() - 1);
        finished();
    }

    private void sort(int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        sort(start, mid);
        sort(mid + 1, end);

        arrayController.addComparisons(1);
        MainController.specialElementsClear();
        MainController.specialElementsAdd(new IntColourPair(mid, Color.RED));
        MainController.specialElementsAdd(new IntColourPair(end, Color.RED));
        MainController.audio.playSound(arrayController.getElement(end));
        update();

        if (arrayController.getElement(end) < arrayController.getElement(mid)) {
            arrayController.swap(end, mid);
            MainController.audio.playSound(arrayController.getElement(end));
            update();
        }
        sort(start, end - 1);
    }
}
