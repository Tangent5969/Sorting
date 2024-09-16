package com.tangent.sorting.ui;

import com.tangent.sorting.Controller;
import com.tangent.utils.Utils;

public class ButtonMethods {
    public enum Method {
        Blank, Start, Pause, Step, Reset, Mute, Random, // Buttons
        Shuffle, Reverse, // Shuffles
        Bubble, Bogo, Bozo // Sorts
    }


    public enum SlideMethod {
        Blank, Speed, Size, Pitch
    }

    static void activateMethod(Method method) {
        switch (method) {
            case Blank:
                break;
            case Start:
                Controller.start();
                break;
            case Pause:
                Controller.pause();
                break;
            case Step:
                Controller.step();
                break;
            case Reset:
                Controller.reset();
                break;
            case Mute:
                break;
            case Random:
                break;
            case Shuffle:
                Controller.reset();
                Utils.shuffle(Controller.mainArray);
                break;
            case Reverse:
                Controller.reset();
                Utils.reverse(Controller.mainArray);
                break;
            case Bubble:
                Controller.setSortType(Method.Bubble);
                break;
            case Bogo:
                Controller.setSortType(Method.Bogo);
                break;
            case Bozo:
                Controller.setSortType(Method.Bozo);
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
                Controller.reset();
                Controller.setTotalElements(value);
                break;
            case Pitch:
                break;
        }
    }
}
