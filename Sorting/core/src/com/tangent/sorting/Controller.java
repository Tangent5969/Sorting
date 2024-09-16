package com.tangent.sorting;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tangent.sorting.sorts.*;
import com.tangent.sorting.ui.ButtonMethods;
import com.tangent.utils.Utils;

import java.util.ArrayList;

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
    static ButtonMethods.Method sortType = ButtonMethods.Method.Blank; // reuse enum with same sort names
    public static int[] mainArray;
    public static Thread sortThread = new Thread();
    public static boolean sorting = false;
    public static boolean stop = false;
    public static final Object lock = new Object();
    public static ArrayList<IntColourPair> specialBars = new ArrayList<>();

    static void setArray() {
        mainArray = new int[totalElements];
        Bar.setWidth();
        Utils.populateArray(mainArray);
    }

    public static void reset() {
        sortThread.interrupt();
        sorting = false;
        specialBars.clear();

        setArray();
    }


    public static void start() {
        if (!sortThread.isAlive()) {
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
        if (!sortThread.isAlive()) {
            newSort();
        }

        synchronized (lock) {
            stop = true;
            lock.notify();
            stop = false;
        }
    }

    private static void newSort() {
        switch (sortType) {
            case Bubble:
                sortThread = new Thread(new BubbleSort(mainArray));
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
        for (IntColourPair pair : specialBars) {
            new Bar(mainArray[pair.getNum()], pair.getColour()).render(pair.getNum(), sr);
        }
    }






    public static void setTotalElements(int totalElements) {
        Controller.totalElements = totalElements;
        setArray();
    }

    public static void setSpeed(int speed) {
        Controller.speed = speed;
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
}
