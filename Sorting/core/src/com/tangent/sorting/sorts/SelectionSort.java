package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.IntColourPair;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;

public class SelectionSort extends Sort{

    public SelectionSort(ArrayController arrayController) {
        super(arrayController, "Selection");
    }

    @Override
    public void run() {
        sort();
        finished();
    }

    private void sort() {
        MainController.specialBarsClear();
        MainController.specialBarsAdd(null);
        MainController.specialBarsAdd(null);
        MainController.specialBarsAdd(null);

        for (int i = 0; i < arrayController.getLength(); i++) {
            MainController.specialBarsSet(0, new IntColourPair(i, Color.GREEN));
            MainController.specialBarsSet(2, new IntColourPair(i, Color.RED));

            int smallest = i;
            for (int j = i + 1; j < arrayController.getLength(); j++) {
                MainController.specialBarsSet(1, new IntColourPair(j, Color.RED));
                MainController.audio.playSound(arrayController.getElement(j));
                arrayController.addComparisons(1);
                Gdx.graphics.requestRendering();
                checkStatus();

                if (arrayController.getElement(j) < arrayController.getElement(smallest)) {
                    smallest = j;
                    MainController.specialBarsSet(2, new IntColourPair(j, Color.RED));
                }
            }
            if (i != smallest) {
                arrayController.swap(i, smallest);
                MainController.specialBarsSet(0, new IntColourPair(i, Color.RED));
                MainController.specialBarsSet(1, null);
                Gdx.graphics.requestRendering();
                checkStatus();
            }
        }
    }
}
