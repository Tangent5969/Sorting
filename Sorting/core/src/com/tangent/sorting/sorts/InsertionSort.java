package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.ui.visual.IntColourPair;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;

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
        MainController.specialBarsAdd(null);
        MainController.specialBarsAdd(null);
        MainController.specialBarsAdd(null);

        for (int i = 1; i < arrayController.getLength(); i++) {
            MainController.specialBarsSet(0, new IntColourPair(i, Color.GREEN));
            for (int j = i; j > 0; j--) {

                MainController.specialBarsSet(1, new IntColourPair(j, Color.RED));
                MainController.specialBarsSet(2, null);
                update();

                MainController.specialBarsSet(1, new IntColourPair(j - 1, Color.RED));
                update();

                arrayController.addComparisons(1);
                MainController.audio.playSound(arrayController.getElement(j - 1));
                if (arrayController.getElement(j) < arrayController.getElement(j - 1)) {
                    arrayController.swap(j, j - 1);
                    MainController.specialBarsSet(2, new IntColourPair(j, Color.RED));
                    update();
                }
                else {
                    break;
                }
            }
        }
    }
}
