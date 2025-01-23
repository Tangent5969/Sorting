package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class InsertionSort extends Sort {

    public InsertionSort(ArrayController arrayController) {
        super(arrayController, "Insertion");
    }

    @Override
    public void run() {
        arrayController.startTimer();
        sort();
        finished();
    }

    private void sort() {
        MainController.specialElementsAdd(null);
        MainController.specialElementsAdd(null);
        MainController.specialElementsAdd(null);

        for (int i = 1; i < arrayController.getLength(); i++) {
            MainController.specialElementsSet(0, new IntColourPair(i, Color.GREEN));
            for (int j = i; j > 0; j--) {

                MainController.specialElementsSet(1, new IntColourPair(j, Color.RED));
                MainController.specialElementsSet(2, null);
                update();

                MainController.specialElementsSet(1, new IntColourPair(j - 1, Color.RED));
                update();

                arrayController.addComparisons(1);
                MainController.audio.playSound(arrayController.getElement(j - 1));
                if (arrayController.getElement(j) < arrayController.getElement(j - 1)) {
                    arrayController.swap(j, j - 1);
                    MainController.specialElementsSet(2, new IntColourPair(j, Color.RED));
                    update();
                } else {
                    break;
                }
            }
        }
    }
}
