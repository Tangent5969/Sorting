package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class OddEvenSort extends Sort {
    public OddEvenSort(ArrayController arrayController) {
        super(arrayController, "Odd-Even");
    }

    @Override
    public void run() {
        sort();
        finished();
    }

    private void sort() {
        boolean swap;
        do {
            swap = bubble(1, false);
            swap = bubble(0, swap);
        }
        while (swap);
    }

    private boolean bubble(int start, boolean swap) {
        for (int i = start; i < arrayController.getLength() - 1; i += 2) {
            MainController.specialBarsClear();
            MainController.specialBarsAdd(new IntColourPair(i, Color.RED));
            Gdx.graphics.requestRendering();
            checkStatus();

            MainController.specialBarsSet(0, new IntColourPair(i + 1, Color.RED));
            Gdx.graphics.requestRendering();
            checkStatus();

            arrayController.addComparisons(1);
            MainController.audio.playSound(arrayController.getElement(i + 1));
            if (arrayController.getElement(i) > arrayController.getElement(i + 1)) {
                arrayController.swap(i, i + 1);
                swap = true;
                MainController.specialBarsAdd(new IntColourPair(i, Color.RED));
                Gdx.graphics.requestRendering();
                checkStatus();
            }
        }
        return swap;
    }
}
