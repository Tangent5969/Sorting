package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class BozoSort extends Sort {

    public BozoSort(ArrayController arrayController) {
        super(arrayController, "Bozo");
    }

    @Override
    public void run() {
        arrayController.startTimer();
        sort();
        finished();
    }

    private void sort() {
        int pos1, pos2;
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
            MainController.specialElementsClear();

            pos1 = arrayController.getRand().nextInt(arrayController.getLength());
            pos2 = arrayController.getRand().nextInt(arrayController.getLength());
            MainController.specialElementsAdd(new IntColourPair(pos1, Color.RED));
            MainController.specialElementsAdd(new IntColourPair(pos2, Color.RED));
            MainController.audio.playSound(arrayController.getElement(Math.min(pos1, pos2)));
            update();

            arrayController.swap(pos1, pos2);
            MainController.audio.playSound(arrayController.getElement(Math.min(pos1, pos2)));
            update();
        }
        arrayController.addComparisons(arrayController.getLength() - 1);
    }
}
