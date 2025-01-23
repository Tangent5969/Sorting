package com.tangent.sorting.sorts;

import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

public class MergeSort extends Sort {

    public MergeSort(ArrayController arrayController) {
        super(arrayController, "Merge");
    }

    @Override
    public void run() {
        arrayController.startTimer();
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
        MainController.specialElementsClear();
        MainController.specialElementsAdd(new IntColourPair(left, Color.GREEN));
        MainController.specialElementsAdd(new IntColourPair(right, Color.GREEN));
        MainController.specialElementsAdd(new IntColourPair(mid + 1, Color.GREEN));
        MainController.specialElementsAdd(null);
        MainController.specialElementsAdd(null);

        int indexL = 0;
        int indexR = 0;

        while (indexL < lengthL && indexR < lengthR) {
            MainController.specialElementsSet(3, new IntColourPair(left + indexL, Color.RED));
            MainController.specialElementsSet(4, new IntColourPair(mid + indexR + 1, Color.RED));
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
            update();
        }

        MainController.specialElementsRemove(4);
        boolean first = false;
        while (indexL < lengthL) {
            renderQueue[indexMain] = arrayL[indexL];
            if (first) {
                MainController.specialElementsSet(3, new IntColourPair(left + indexL, Color.RED));
                update();
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
                MainController.specialElementsSet(3, new IntColourPair(mid + indexR, Color.RED));
                update();
            }
            indexMain++;
            first = true;
        }

        MainController.specialElementsRemove(3);
        for (int i = 0; i < indexMain; i++) {
            arrayController.setElement(left + i, renderQueue[i]);
            MainController.specialElementsSet(2, new IntColourPair(left + i, Color.RED));
            MainController.audio.playSound(renderQueue[i]);
            update();
        }
    }
}
