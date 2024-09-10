package com.tangent.sorting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.tangent.sorting.sorts.BogoSort;
import com.tangent.sorting.sorts.BozoSort;
import com.tangent.sorting.sorts.RecursiveBubbleSort;
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
    public static boolean start = false;
    public static boolean step = false;
    public static int marker = -1;
    public static ArrayList<IntColourPair> specialBars = new ArrayList<>();

    static void setArray() {
        mainArray = new int[totalElements];
        Bar.setWidth();
        Utils.populateArray(mainArray);
    }

    public static void reset() {
        start = false;
        marker = -1;
        specialBars.clear();
        setArray();
    }

    static void sort() {
        switch (sortType) {
            case Bubble:
                RecursiveBubbleSort.bubbleSort(mainArray);
                break;
            case Bogo:
                BogoSort.bogoSort(mainArray);
                break;
            case Bozo:
                BozoSort.bozoSort(mainArray);
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
