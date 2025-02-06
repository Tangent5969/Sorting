package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class GnomeSort extends Sort {

    public GnomeSort(ArrayController arrayController) {
        super(arrayController, "Gnome");
    }

    @Override
    public void run() {
        arrayController.setSortingStatus(true);
        arrayController.startTimer();
        sort();
        finished();
    }

    private void sort() {
        int pos = 0;
        int maxIndex = 0;
        while (pos < arrayController.getLength()) {
            if (pos > maxIndex) {
                maxIndex++;
            }
            MainController.specialElementsClear();
            MainController.specialElementsAdd(new IntColourPair(maxIndex, Color.GREEN));
            MainController.specialElementsAdd(new IntColourPair(pos, Color.RED));
            update();

            if (pos > 0) {
                arrayController.addComparisons(1);
                MainController.specialElementsAdd(new IntColourPair(pos - 1, Color.RED));
                MainController.audio.playSound(arrayController.getElement(pos - 1));
                update();
            }

            if (pos == 0 || arrayController.getElement(pos) >= arrayController.getElement(pos - 1)) {
                pos++;
            } else {
                arrayController.swap(pos, pos - 1);
                update();
                pos--;
            }
        }
    }
}
