package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class CocktailSort extends Sort {

    public CocktailSort(ArrayController arrayController) {
        super(arrayController, "Cocktail");
    }

    @Override
    public void run() {
        sort();
        finished();
    }

    private void sort() {
        int start = 0;
        int end = arrayController.getLength() - 1;
        boolean swap;

        do {
            MainController.specialBarsClear();
            MainController.specialBarsAdd(new IntColourPair(start, Color.GREEN));
            MainController.specialBarsAdd(new IntColourPair(end, Color.GREEN));
            MainController.specialBarsAdd(null);
            MainController.specialBarsAdd(null);
            swap = false;

            for (int i = start; i < end; i++) {
                MainController.specialBarsSet(2, new IntColourPair(i, Color.RED));
                Gdx.graphics.requestRendering();
                checkStatus();

                MainController.specialBarsSet(2, new IntColourPair(i + 1, Color.RED));
                Gdx.graphics.requestRendering();
                checkStatus();

                arrayController.addComparisons(1);
                MainController.audio.playSound(arrayController.getElement(i + 1));
                if (arrayController.getElement(i) > arrayController.getElement(i + 1)) {
                    arrayController.swap(i, i + 1);
                    swap = true;
                    MainController.specialBarsSet(3, new IntColourPair(i, Color.RED));
                    Gdx.graphics.requestRendering();
                    checkStatus();
                    MainController.specialBarsSet(3, null);
                }
            }

            end--;
            MainController.specialBarsSet(1, new IntColourPair(end, Color.GREEN));
            if (!swap) break;

            for (int i = end; i > start; i--) {
                MainController.specialBarsSet(2, new IntColourPair(i, Color.RED));
                Gdx.graphics.requestRendering();
                checkStatus();

                MainController.specialBarsSet(2, new IntColourPair(i - 1, Color.RED));
                Gdx.graphics.requestRendering();
                checkStatus();

                arrayController.addComparisons(1);
                MainController.audio.playSound(arrayController.getElement(i - 1));
                if (arrayController.getElement(i - 1) > arrayController.getElement(i)) {
                    arrayController.swap(i, i - 1);
                    swap = true;
                    MainController.specialBarsSet(3, new IntColourPair(i, Color.RED));
                    Gdx.graphics.requestRendering();
                    checkStatus();
                    MainController.specialBarsSet(3, null);
                }
            }
            start++;
        }
        while (swap);
    }
}
