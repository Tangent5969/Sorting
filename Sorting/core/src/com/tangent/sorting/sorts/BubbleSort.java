package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class BubbleSort extends Sort {

    public BubbleSort(ArrayController arrayController) {
        super(arrayController, "Bubble");
    }

    @Override
    public void run() {
        startTime = System.nanoTime();
        sort();
        finished();
    }

    private void sort() {
        int end = arrayController.getLength() - 1;
        boolean swap;

        do {
            swap = false;
            for (int i = 0; i < end; i++) {
                MainController.specialBarsClear();
                MainController.specialBarsAdd(new IntColourPair(i, Color.RED));
                update();

                MainController.specialBarsSet(0, new IntColourPair(i + 1, Color.RED));
                update();

                arrayController.addComparisons(1);
                MainController.audio.playSound(arrayController.getElement(i + 1));
                if (arrayController.getElement(i) > arrayController.getElement(i + 1)) {
                    arrayController.swap(i, i + 1);
                    swap = true;
                    MainController.specialBarsAdd(new IntColourPair(i, Color.RED));
                    update();
                }
            }
            end--;
        }
        while (swap);
    }
}
