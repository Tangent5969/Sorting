package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class ShellSort extends Sort {

    public ShellSort(ArrayController arrayController) {
        super(arrayController, "Shell");
    }

    @Override
    public void run() {
        sort();
        finished();
    }

    private void sort() {
        int gap = 1;
        // Knuth's formula
        while (gap < arrayController.getLength()) {
            gap = gap * 3 + 1;
        }
        MainController.specialElementsAdd(null);
        MainController.specialElementsAdd(null);
        MainController.specialElementsAdd(null);

        while (gap >= 1) {
            for (int i = gap; i < arrayController.getLength(); i++) {
                MainController.specialElementsSet(0, new IntColourPair(i, Color.GREEN));
                for (int j = i; j >= gap && arrayController.getElement(j) < arrayController.getElement(j - gap); j -= gap) {
                    arrayController.addComparisons(1);
                    MainController.specialElementsSet(1, new IntColourPair(j, Color.RED));
                    MainController.specialElementsSet(2, new IntColourPair(j - gap, Color.RED));
                    update();

                    arrayController.swap(j, j - gap);
                    MainController.audio.playSound(arrayController.getElement(j));
                    update();
                }
            }
            gap /= 3;
        }
    }
}
