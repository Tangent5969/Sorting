package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.IntColourPair;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;

public class MergeSort extends Sort{

    public MergeSort(ArrayController arrayController) {
        super(arrayController, "Merge");
    }

    @Override
    public void run() {
        sort(0, arrayController.getLength() - 1);
        finished();
    }

    private void sort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(left, mid);
            sort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right) {
        int lengthL = mid - left + 1;
        int lengthR = right - mid;
        int[] arrayL = new int[lengthL];
        int[] arrayR = new int[lengthR];

        for (int i = 0; i < lengthL; i++) {
            arrayL[i] = arrayController.getElement(left + i);
        }
        arrayController.addAuxWrites(lengthL);

        for (int i = 0; i < lengthR; i++) {
            arrayR[i] = arrayController.getElement(mid + i + 1);
        }
        arrayController.addAuxWrites(lengthR);

        int[] renderQueue = new int[lengthL + lengthR];
        int indexMain = 0;
        MainController.specialBarsClear();
        MainController.specialBarsAdd(new IntColourPair(left, Color.GREEN));
        MainController.specialBarsAdd(new IntColourPair(right, Color.GREEN));
        MainController.specialBarsAdd(new IntColourPair(mid + 1, Color.GREEN));
        MainController.specialBarsAdd(null);
        MainController.specialBarsAdd(null);

        int indexL = 0;
        int indexR = 0;

        while (indexL < lengthL && indexR < lengthR) {
            MainController.specialBarsSet(3, new IntColourPair(left + indexL, Color.RED));
            MainController.specialBarsSet(4, new IntColourPair(mid + indexR + 1, Color.RED));
            arrayController.addComparisons(1);

            if (arrayL[indexL] <= arrayR[indexR]) {
                renderQueue[indexMain] = arrayL[indexL];
                MainController.audio.playSound(arrayL[indexL]);
                indexL++;
            } else {
                renderQueue[indexMain] = arrayR[indexR];
                MainController.audio.playSound(arrayR[indexR]);
                indexR++;
            }
            indexMain++;
            Gdx.graphics.requestRendering();
            checkStatus();
        }

        MainController.specialBarsRemove(4);
        boolean first = false;
        while (indexL < lengthL) {
            renderQueue[indexMain] = arrayL[indexL];
            if (first) {
                MainController.specialBarsSet(3, new IntColourPair(left + indexL, Color.RED));
                Gdx.graphics.requestRendering();
                checkStatus();
            }
            indexL++;
            indexMain++;
            first = true;
        }

        first = false;
        while (indexR < lengthR) {
            renderQueue[indexMain] = arrayR[indexR];
            indexR++;
            if (first) {
                MainController.specialBarsSet(3, new IntColourPair(mid + indexR, Color.RED));
                Gdx.graphics.requestRendering();
                checkStatus();
            }
            indexMain++;
            first = true;
        }

        MainController.specialBarsRemove(3);
        for (int i = 0; i < indexMain; i++) {
            arrayController.setElement(left + i, renderQueue[i]);
            MainController.specialBarsSet(2, new IntColourPair(left + i, Color.RED));
            MainController.audio.playSound(renderQueue[i]);
            Gdx.graphics.requestRendering();
            checkStatus();
        }
    }
}
