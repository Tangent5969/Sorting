package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.IntColourPair;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;

public class BogoSort extends Sort{

    public BogoSort(ArrayController arrayController) {
        super(arrayController, "Bogo");
    }

    @Override
    public void run() {
        sort();
        finished();
    }

    private void sort() {
        while (!arrayController.isSorted()) {
            MainController.specialBarsClear();
            MainController.specialBarsAdd(new IntColourPair(0, Color.GREEN));
            MainController.audio.playSound(arrayController.getElement(0));
            Gdx.graphics.requestRendering();
            checkStatus();

            for (int i = 1; i < arrayController.getLength(); i++) {
                arrayController.addComparisons(1);
                MainController.specialBarsAdd(new IntColourPair(i, Color.RED));
                Gdx.graphics.requestRendering();
                checkStatus();

                if (arrayController.getElement(i) > arrayController.getElement(i - 1)) {
                    MainController.specialBarsSet(i, new IntColourPair(i, Color.GREEN));
                    MainController.audio.playSound(arrayController.getElement(i));
                    Gdx.graphics.requestRendering();
                    checkStatus();

                } else {
                    break;
                }
            }
            arrayController.shuffle();
        }
        arrayController.addComparisons(arrayController.getLength() - 1);
    }
}
