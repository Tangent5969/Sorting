package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.IntColourPair;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;

public class GnomeSort extends Sort {

    public GnomeSort(ArrayController arrayController) {
        super(arrayController, "Gnome");
    }

    @Override
    public void run() {
        int pos = 0;
        int maxIndex = 0;
        while (pos < arrayController.getLength()) {
            if (pos > maxIndex) {
                maxIndex++;
            }
            MainController.specialBarsClear();
            MainController.specialBarsAdd(new IntColourPair(maxIndex, Color.GREEN));
            MainController.specialBarsAdd(new IntColourPair(pos, Color.RED));
            Gdx.graphics.requestRendering();
            checkStatus();

            if (pos > 0) {
                arrayController.addComparisons(1);
                MainController.specialBarsAdd(new IntColourPair(pos - 1, Color.RED));
                MainController.audio.playSound(arrayController.getElement(pos - 1));
                Gdx.graphics.requestRendering();
                checkStatus();
            }

            if (pos == 0 || arrayController.getElement(pos) >= arrayController.getElement(pos - 1)) {
                pos++;
            }
            else {
                arrayController.swap(pos, pos - 1);
                Gdx.graphics.requestRendering();
                checkStatus();
                pos--;
            }
        }
        finished();
    }
}
