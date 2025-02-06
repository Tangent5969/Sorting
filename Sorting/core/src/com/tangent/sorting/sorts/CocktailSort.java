package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class CocktailSort extends Sort {

    public CocktailSort(ArrayController arrayController) {
        super(arrayController, "Cocktail");
    }

    @Override
    public void run() {
        arrayController.setSortingStatus(true);
        arrayController.startTimer();
        sort();
        finished();
    }

    private void sort() {
        int start = 0;
        int end = arrayController.getLength() - 1;
        boolean swap;

        do {
            MainController.specialElementsClear();
            MainController.specialElementsAdd(new IntColourPair(start, Color.GREEN));
            MainController.specialElementsAdd(new IntColourPair(end, Color.GREEN));
            MainController.specialElementsAdd(null);
            MainController.specialElementsAdd(null);
            swap = false;

            for (int i = start; i < end; i++) {
                MainController.specialElementsSet(2, new IntColourPair(i, Color.RED));
                update();

                MainController.specialElementsSet(2, new IntColourPair(i + 1, Color.RED));
                update();

                arrayController.addComparisons(1);
                MainController.audio.playSound(arrayController.getElement(i + 1));
                if (arrayController.getElement(i) > arrayController.getElement(i + 1)) {
                    arrayController.swap(i, i + 1);
                    swap = true;
                    MainController.specialElementsSet(3, new IntColourPair(i, Color.RED));
                    update();
                    MainController.specialElementsSet(3, null);
                }
            }

            end--;
            MainController.specialElementsSet(1, new IntColourPair(end, Color.GREEN));
            if (!swap) break;

            for (int i = end; i > start; i--) {
                MainController.specialElementsSet(2, new IntColourPair(i, Color.RED));
                update();

                MainController.specialElementsSet(2, new IntColourPair(i - 1, Color.RED));
                update();

                arrayController.addComparisons(1);
                MainController.audio.playSound(arrayController.getElement(i - 1));
                if (arrayController.getElement(i - 1) > arrayController.getElement(i)) {
                    arrayController.swap(i, i - 1);
                    swap = true;
                    MainController.specialElementsSet(3, new IntColourPair(i, Color.RED));
                    update();
                    MainController.specialElementsSet(3, null);
                }
            }
            start++;
        }
        while (swap);
    }
}
