package com.tangent.sorting;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import java.util.Random;

import com.tangent.sorting.sorts.*;
import com.tangent.sorting.ui.ButtonMethods;
import com.tangent.utils.Utils;


public class Controller {
    static final int width = 10000;
    static final int height = 10000;
    static final float widthMultiplier = 0.9f;
    static final int minElements = 4;
    static final int maxElements = 1024;
    static int totalElements = 10;
    static final int minSpeed = 0;
    static final int maxSpeed = 100;
    public static int speed = 25;
    static final int sortCount = 3;
    static ButtonMethods.Method sortType = ButtonMethods.Method.Blank; // reuse enum with same sort names
    public static int[] mainArray;
    public static Thread sortThread = new Thread("sortThread");
    public static boolean sorted = true;
    public static boolean sorting = false;
    public static boolean stop = false;
    public static final Object lock = new Object();
    private static final Random rand = new Random();
    private static ArrayList<IntColourPair> specialBars = new ArrayList<>();

    static void setArray() {
        mainArray = new int[totalElements];
        Bar.setWidth();
        Utils.populateArray(mainArray);
        sorted = true;
    }

    public static void reset() {
        sortThread.interrupt();
        sorting = false;
        specialBars.clear();
        setArray();
    }

    public static void start() {
        if (!sorted && !sortThread.isAlive()) {
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
        if (!sorted && !sortThread.isAlive()) {
            newSort();
        }

        synchronized (lock) {
            stop = true;
            lock.notify();
            stop = false;
        }
    }

    public static void shuffle() {
        Controller.reset();
        Utils.shuffle(Controller.mainArray);
        Controller.sorted = false;
    }

    public static void reverse() {
        Controller.reset();
        Utils.reverse(Controller.mainArray);
        Controller.sorted = false;
    }

    private static void newSort() {
        switch (sortType) {
            case Bubble:
                sortThread = new Thread(new BubbleSort(mainArray), "sortThread");
                sortThread.start();
                break;
            case Bogo:
                //BogoSort.bogoSort(mainArray);
                break;
            case Bozo:
                //BozoSort.bozoSort(mainArray);
                break;
        }
    }

    public static void renderArray(ShapeRenderer sr) {
        for (int i = 0; i < totalElements; i++) {
            new Bar(mainArray[i]).render(i, sr);
        }
        synchronized (lock) {
            for (IntColourPair pair : specialBars) {
                new Bar(mainArray[pair.getNum()], pair.getColour()).render(pair.getNum(), sr);
            }
        }
    }

    public static void setTotalElements(int totalElements) {
        Controller.totalElements = totalElements;
        setArray();
    }

    public static void setSpeed(int speed) {
        Controller.speed = speed;
    }

    public static void randomSort() {
        ButtonMethods.Method tempSortType;
        do {
            tempSortType = ButtonMethods.Method.values()[ButtonMethods.Method.values().length - rand.nextInt(sortCount) - 1];
        }
        while (tempSortType == sortType);
        setSortType(tempSortType);
    }

    public static void setSortType(ButtonMethods.Method sortType) {
        if (Controller.sortType == ButtonMethods.Method.Blank) {
            Controller.sortType = sortType;
        }
        else if (sortType != Controller.sortType) {
            reset();
            Controller.sortType = sortType;
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
