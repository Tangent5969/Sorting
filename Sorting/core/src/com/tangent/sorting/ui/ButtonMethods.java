package com.tangent.sorting.ui;

import com.tangent.sorting.Controller;

public class ButtonMethods {
    public enum Method {
        Blank, Start, Pause, Step, Reset, Mute, Random
    }
    public enum SlideMethod {
        Blank, Speed, Size, Pitch
    }

    static void activateMethod(Method method) {
        switch (method) {
            case Blank:
                break;
            case Start:
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
