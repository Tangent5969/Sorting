package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class SelectionSort extends Sort {

    public SelectionSort(ArrayController arrayController) {
        super(arrayController, "Selection");
    }

    @Override
    public void run() {
        sort();
        finished();
    }

    private void sort() {
        MainController.specialElementsAdd(null);
        MainController.specialElementsAdd(null);
        MainController.specialElementsAdd(null);

        for (int i = 0; i < arrayController.getLength(); i++) {
            MainController.specialElementsSet(0, new IntColourPair(i, Color.GREEN));
            MainController.specialElementsSet(2, new IntColourPair(i, Color.RED));

            int smallest = i;
            for (int j = i + 1; j < arrayController.getLength(); j++) {
                MainController.specialElementsSet(1, new IntColourPair(j, Color.RED));
                MainController.audio.playSound(arrayController.getElement(j));
                arrayController.addComparisons(1);
                update();

                if (arrayController.getElement(j) < arrayController.getElement(smallest)) {
                    smallest = j;
                    MainController.specialElementsSet(2, new IntColourPair(j, Color.RED));
                }
            }
            if (i != smallest) {
                arrayController.swap(i, smallest);
                MainController.specialElementsSet(0, new IntColourPair(i, Color.RED));
                MainController.specialElementsSet(1, null);
                update();
            }
        }
    }
}
