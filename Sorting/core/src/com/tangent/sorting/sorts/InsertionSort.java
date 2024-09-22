package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.IntColourPair;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;

public class InsertionSort extends Sort{

    public InsertionSort(ArrayController arrayController) {
        super(arrayController, "Insertion");
    }

    @Override
    public void run() {
        MainController.specialBarsAdd(null);
        MainController.specialBarsAdd(null);
        MainController.specialBarsAdd(null);

        for (int i = 1; i < arrayController.getLength(); i++) {
            MainController.specialBarsSet(0, new IntColourPair(i, Color.GREEN));
            for (int j = i; j > 0; j--) {

                MainController.specialBarsSet(1, new IntColourPair(j, Color.RED));
                MainController.specialBarsSet(2, null);
                Gdx.graphics.requestRendering();
                checkStatus();

                MainController.specialBarsSet(1, new IntColourPair(j - 1, Color.RED));
                Gdx.graphics.requestRendering();
                checkStatus();

                arrayController.addComparisons(1);
                MainController.audio.playSound(arrayController.getElement(j - 1));
                if (arrayController.getElement(j) < arrayController.getElement(j - 1)) {
                    arrayController.swap(j, j - 1);
                    MainController.specialBarsSet(2, new IntColourPair(j, Color.RED));
                    Gdx.graphics.requestRendering();
                    checkStatus();
                }
                else {
                    break;
                }
            }
        }



        finished();
    }
}
