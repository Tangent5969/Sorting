package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class CombSort extends Sort {

    public CombSort(ArrayController arrayController) {
        super(arrayController, "Comb");
    }

    @Override
    public void run() {
        sort(arrayController.getLength());
        finished();
    }

    private void sort(int gap) {
        boolean sorted = false;
        do {
            gap = (int) Math.floor(gap / 1.3);
            if (gap <= 1) {
                gap = 1;
                sorted = true;
            }

            for (int i = 0; i + gap < arrayController.getLength(); i++) {
                MainController.specialElementsClear();
                MainController.specialElementsAdd(new IntColourPair(i, Color.RED));
                MainController.audio.playSound(arrayController.getElement(i));
                update();

                MainController.specialElementsSet(0, new IntColourPair(i + gap, Color.RED));
                MainController.audio.playSound(arrayController.getElement(i + gap));
                update();

                arrayController.addComparisons(1);
                if (arrayController.getElement(i) > arrayController.getElement(i + gap)) {
                    arrayController.swap(i, i + gap);
                    sorted = false;
                    MainController.specialElementsAdd(new IntColourPair(i, Color.RED));
                    update();
                }
            }
        }
        while (!sorted);
    }
}
