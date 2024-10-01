package com.tangent.sorting.ui.input;

import com.tangent.sorting.controls.MainController;

public class ButtonMethods {
    public enum Method {
        Blank, Start, Pause, Step, Reset, Mute, Random, // Buttons
        Shuffle, Reverse, // Shuffles
        Bubble, Merge, Insertion, Gnome, Selection, Quick, Bogo, Bozo // Sorts
    }


    public enum SlideMethod {
        Blank, Speed, Size, Volume, Pitch
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
                MainController.setSelectedSort(MainController.SortType.Bubble);
                break;
            case Merge:
                MainController.setSelectedSort(MainController.SortType.Merge);
                break;
            case Insertion:
                MainController.setSelectedSort(MainController.SortType.Insertion);
                break;
            case Gnome:
                MainController.setSelectedSort(MainController.SortType.Gnome);
                break;
            case Selection:
                MainController.setSelectedSort(MainController.SortType.Selection);
                break;
            case Quick:
                MainController.setSelectedSort(MainController.SortType.Quick);
                break;
            case Bogo:
                MainController.setSelectedSort(MainController.SortType.Bogo);
                break;
            case Bozo:
                MainController.setSelectedSort(MainController.SortType.Bozo);
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
                MainController.setTotalElements((int) value);
                break;
            case Volume:
                MainController.audio.setVolume((int) value);
                break;
            case Pitch:
                MainController.audio.setPitch(value);
                break;
        }
    }
}
