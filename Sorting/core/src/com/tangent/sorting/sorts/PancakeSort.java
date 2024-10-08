package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class PancakeSort extends Sort {

    public PancakeSort(ArrayController arrayController) {
        super(arrayController, "Pancake");
    }

    @Override
    public void run() {
        startTime = System.nanoTime();
        sort();
        finished();
    }

    private void sort() {
        for (int length = arrayController.getLength(); length > 1; length--) {
            int max = 0;
            MainController.specialBarsClear();
            MainController.specialBarsAdd(new IntColourPair(max, Color.RED));
            MainController.specialBarsAdd(null);

            for (int i = 0; i < length; i++) {
                MainController.specialBarsSet(1, new IntColourPair(i, Color.RED));
                arrayController.addComparisons(1);
                MainController.audio.playSound(arrayController.getElement(i));
                update();

                if (arrayController.getElement(i) > arrayController.getElement(max)) {
                    max = i;
                    MainController.specialBarsSet(0, new IntColourPair(max, Color.RED));
                }
            }

            if (max != length - 1) {
                MainController.specialBarsSet(0, new IntColourPair(max, Color.GREEN));
                MainController.specialBarsSet(1, new IntColourPair(0, Color.GREEN));
                update();

                flip(max);
                MainController.specialBarsSet(0, new IntColourPair(length - 1, Color.GREEN));
                update();

                flip(length - 1);
            }
        }
    }

    private void flip(int end) {
        MainController.specialBarsAdd(null);
        MainController.specialBarsAdd(null);
        for (int i = 0; i < (end + 1) / 2; i++) {
            arrayController.swap(i, end - i);
            MainController.specialBarsSet(2, new IntColourPair(i, Color.RED));
            MainController.specialBarsSet(3, new IntColourPair(end - i, Color.RED));
            update();
        }
        MainController.specialBarsRemove(3);
        MainController.specialBarsRemove(2);
    }

}
