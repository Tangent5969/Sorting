package com.tangent.sorting.controls;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import java.util.Random;

import com.tangent.sorting.Audio;
import com.tangent.sorting.Bar;
import com.tangent.sorting.IntColourPair;
import com.tangent.sorting.sorts.*;
import com.tangent.sorting.ui.ButtonMethods;


public class MainController {
    // window settings
    public static final int width = 10000;
    public static final int height = 10000;
    public static final float widthMultiplier = 0.9f;

    // parameter limits
    public static final int minElements = 4;
    public static final int maxElements = 4096;
    public static final int minSpeed = 0;
    public static final int maxSpeed = 100;


    public static int speed = 25;
    static final int sortCount = 3;
    public static boolean sorting = false;
    public static boolean stop = false;

    private static SortTypes selectedSort;
    public static ArrayController arrayController;
    public static Audio audio;
    public static Thread sortThread;
    public static final Object lock = new Object();
    private static final Random rand = new Random();
    private static ArrayList<IntColourPair> specialBars = new ArrayList<>();

    public enum SortTypes {
        Bubble, Bogo, Bozo
    }



    public static void initialise() {
        // initial array size
        arrayController = new ArrayController(10);
        audio = new Audio();
        sortThread = new Thread("sortThread");
        Bar.setWidth();
    }


    public static void reset() {
        sortThread.interrupt();
        audio.stopSound();
        sorting = false;
        specialBars.clear();
        arrayController.reset();
    }

    public static void start() {
        if (!arrayController.isSorted() && !sortThread.isAlive()) {
            sorting = true;
            newSort();
        }
        else if (!sorting) {
            synchronized (lock) {
                sorting = true;
                lock.notify();
            }
        }
    }

    public static void pause() {
        sorting = false;
    }

    public static void step() {
        if (!arrayController.isSorted() && !sortThread.isAlive()) {
            newSort();
        }

        synchronized (lock) {
            stop = true;
            lock.notify();
            stop = false;
        }
    }

    public static void shuffle() {
        MainController.reset();
        arrayController.shuffle();
    }

    public static void reverse() {
        MainController.reset();
        arrayController.reverse();
    }

    private static void newSort() {
        if (selectedSort == null) {
            return;
        }
        switch (selectedSort) {
            case Bubble:
                sortThread = new Thread(new BubbleSort(arrayController), "sortThread");
                break;
            case Bogo:
                sortThread = new Thread(new BogoSort(arrayController), "sortThread");
                break;
            case Bozo:
                sortThread = new Thread(new BozoSort(arrayController), "sortThread");
                break;
        }
        arrayController.setSortingStatus(true);
        sortThread.start();
    }

    public static void renderArray(ShapeRenderer sr) {
        for (int i = 0; i < arrayController.getLength(); i++) {
            new Bar(arrayController.getElement(i)).render(i, sr);
        }
        synchronized (lock) {
            for (IntColourPair pair : specialBars) {
                new Bar(arrayController.getElement(pair.getNum()), pair.getColour()).render(pair.getNum(), sr);
            }
        }
    }

    public static void setTotalElements(int totalElements) {
        MainController.arrayController.resize(totalElements);
        Bar.setWidth();
    }

    public static void setSpeed(int speed) {
        MainController.speed = speed;
    }

    public static void randomSort() {
        SortTypes tempSort;
        do {
            tempSort = SortTypes.values()[SortTypes.values().length - rand.nextInt(sortCount) - 1];
        }
        while (tempSort == selectedSort);
        setSelectedSort(tempSort);
    }

    public static void setSelectedSort(SortTypes selectedSort) {
        if (MainController.selectedSort == null) {
            MainController.selectedSort = selectedSort;
        }
        else if (selectedSort != MainController.selectedSort) {
            reset();
            MainController.selectedSort = selectedSort;
        }
    }

    public static void specialBarsAdd(IntColourPair pair) {
        synchronized (lock) {
            specialBars.add(pair);
        }
    }

    public static void specialBarsSet(int i, IntColourPair pair) {
        synchronized (lock) {
            specialBars.set(i, pair);
        }
    }

    public static void specialBarsClear() {
        synchronized (lock) {
            specialBars.clear();
        }
    }

}
