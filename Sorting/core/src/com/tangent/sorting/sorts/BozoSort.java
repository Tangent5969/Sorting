package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.ui.visual.IntColourPair;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;

public class BozoSort extends Sort{

    public BozoSort(ArrayController arrayController) {
        super(arrayController, "Bozo");
    }

    @Override
    public void run() {
        startTime = System.nanoTime();
        sort();
        finished();
    }

    private void sort() {
        int pos1, pos2;
        while (!arrayController.isSorted()) {
            MainController.specialBarsClear();
            MainController.specialBarsAdd(new IntColourPair(0, Color.GREEN));
            MainController.audio.playSound(arrayController.getElement(0));
            update();

            for (int i = 1; i < arrayController.getLength(); i++) {
                arrayController.addComparisons(1);
                MainController.specialBarsAdd(new IntColourPair(i, Color.RED));
                update();

                if (arrayController.getElement(i) > arrayController.getElement(i - 1)) {
                    MainController.specialBarsSet(i, new IntColourPair(i, Color.GREEN));
                    MainController.audio.playSound(arrayController.getElement(i));
                    update();

                } else {
                    break;
                }
            }
            MainController.specialBarsClear();

            pos1 = arrayController.getRand().nextInt(arrayController.getLength());
            pos2 = arrayController.getRand().nextInt(arrayController.getLength());
            MainController.specialBarsAdd(new IntColourPair(pos1, Color.RED));
            MainController.specialBarsAdd(new IntColourPair(pos2, Color.RED));
            update();

            arrayController.swap(pos1, pos2);
            update();
        }
        arrayController.addComparisons(arrayController.getLength() - 1);
    }
}
