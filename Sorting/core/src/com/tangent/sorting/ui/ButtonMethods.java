package com.tangent.sorting.ui;

import com.tangent.sorting.controls.MainController;

public class ButtonMethods {
    public enum Method {
        Blank, Start, Pause, Step, Reset, Mute, Random, // Buttons
        Shuffle, Reverse, // Shuffles
        Bubble, Bogo, Bozo // Sorts
    }


    public enum SlideMethod {
        Blank, Speed, Size, Pitch
    }

    protected static void activateMethod(Method method) {
        switch (method) {
            case Blank:
                break;
            case Start:
                MainController.start();
                break;
            case Pause:
                MainController.pause();
                break;
            case Step:
                MainController.step();
                break;
            case Reset:
                MainController.reset();
                break;
            case Mute:
                MainController.audio.mute();
                break;
            case Random:
                MainController.randomSort();
                break;
            case Shuffle:
                MainController.shuffle();
                break;
            case Reverse:
                MainController.reverse();
                break;
            case Bubble:
                MainController.setSelectedSort(MainController.SortTypes.Bubble);
                break;
            case Bogo:
                MainController.setSelectedSort(MainController.SortTypes.Bogo);
                break;
            case Bozo:
                MainController.setSelectedSort(MainController.SortTypes.Bozo);
                break;
        }
    }

    protected static void updateSlideValue(SlideMethod method, double value) {
        switch (method) {
            case Blank:
                break;
            case Speed:
                MainController.setSpeed((int) value);
                break;
            case Size:
                MainController.reset();
                MainController.setTotalElements((int) value);
                break;
            case Pitch:
                MainController.audio.setPitch(value);
                break;
        }
    }
}
