package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.ui.visual.IntColourPair;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;

public class BogoSort extends Sort{

    public BogoSort(ArrayController arrayController) {
        super(arrayController, "Bogo");
    }

    @Override
    public void run() {
        arrayController.startTimer();
        sort();
        finished();
    }

    private void sort() {
        while (!arrayController.isSorted()) {
            MainController.specialElementsClear();
            MainController.specialElementsAdd(new IntColourPair(0, Color.GREEN));
            MainController.audio.playSound(arrayController.getElement(0));
            update();

            for (int i = 1; i < arrayController.getLength(); i++) {
                arrayController.addComparisons(1);
                MainController.specialElementsAdd(new IntColourPair(i, Color.RED));
                update();

                if (arrayController.getElement(i) > arrayController.getElement(i - 1)) {
                    MainController.specialElementsSet(i, new IntColourPair(i, Color.GREEN));
                    MainController.audio.playSound(arrayController.getElement(i));
                    update();

                } else {
                    break;
                }
            }
            arrayController.shuffle();
        }
        arrayController.addComparisons(arrayController.getLength() - 1);
    }
}
