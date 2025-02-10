package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class ExchangeSort extends Sort {

    public ExchangeSort(ArrayController arrayController) {
        super(arrayController, "Exchange");
    }

    @Override
    public void run() {
        sort();
        finished();
    }

    private void sort() {
        MainController.specialElementsAdd(null);
        MainController.specialElementsAdd(null);

        for (int i = 0; i < arrayController.getLength(); i++) {
            MainController.specialElementsSet(0, new IntColourPair(i, Color.RED));

            for (int j = i + 1; j < arrayController.getLength(); j++) {
                arrayController.addComparisons(1);
                MainController.specialElementsSet(1, new IntColourPair(j, Color.RED));
                MainController.audio.playSound(arrayController.getElement(j));
                update();

                if (arrayController.getElement(i) > arrayController.getElement(j)) {
                    arrayController.swap(i, j);
                    update();
                }
            }
        }
    }
}
