package com.tangent.sorting.ui;

import com.badlogic.gdx.Gdx;
import com.tangent.sorting.Controller;
import com.tangent.sorting.sorts.BubbleSort;
import com.tangent.utils.Utils;

public class ButtonMethods {
    public enum Method {
        Blank, Start, Pause, Step, Reset, Mute, Random, Shuffle, Reverse
    }


    public enum SlideMethod {
        Blank, Speed, Size, Pitch
    }

    static void activateMethod(Method method) {
        switch (method) {
            case Blank:
                break;
            case Start:
                new BubbleSort(Controller.mainArray);
                break;
            case Pause:
                break;
            case Step:
                break;
            case Reset:
                break;
            case Mute:
                break;
            case Random:
                break;
            case Shuffle:
                Utils.shuffle(Controller.mainArray);
                break;
            case Reverse:
                Utils.reverse(Controller.mainArray);
                break;
        }
    }

    static void updateSlideValue(SlideMethod method, int value) {
        switch (method) {
            case Blank:
                break;
            case Speed:
                Controller.setSpeed(value);
                break;
            case Size:
                Controller.setTotalElements(value);
                break;
            case Pitch:
                break;
        }
    }
}
