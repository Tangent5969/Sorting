package com.tangent.sorting.ui.input;

import com.tangent.sorting.controls.Main;
import com.tangent.sorting.controls.MainController;

public class ButtonMethods {
    public enum Method {
        Blank, Start, Pause, Step, Reset, Mute, Random, // Buttons
        Shuffle, Reverse, // Shuffles
        Bogo, Bozo, Bubble, Cocktail, Comb, Exchange, Gnome, Insertion, Merge, OddEven, Pancake, Quick, Selection, Slow // Sorts
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

            case Bogo:
                MainController.setSelectedSort(MainController.SortType.Bogo);
                break;
            case Bozo:
                MainController.setSelectedSort(MainController.SortType.Bozo);
                break;
            case Bubble:
                MainController.setSelectedSort(MainController.SortType.Bubble);
                break;
            case Cocktail:
                MainController.setSelectedSort(MainController.SortType.Cocktail);
                break;
            case Comb:
                MainController.setSelectedSort(MainController.SortType.Comb);
                break;
            case Exchange:
                MainController.setSelectedSort(MainController.SortType.Exchange);
                break;
            case Gnome:
                MainController.setSelectedSort(MainController.SortType.Gnome);
                break;
            case Insertion:
                MainController.setSelectedSort(MainController.SortType.Insertion);
                break;
            case Merge:
                MainController.setSelectedSort(MainController.SortType.Merge);
                break;
            case OddEven:
                MainController.setSelectedSort(MainController.SortType.OddEven);
                break;
            case Pancake:
                MainController.setSelectedSort(MainController.SortType.Pancake);
                break;
            case Quick:
                MainController.setSelectedSort(MainController.SortType.Quick);
                break;
            case Selection:
                MainController.setSelectedSort(MainController.SortType.Selection);
                break;
            case Slow:
                MainController.setSelectedSort(MainController.SortType.Slow);
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
