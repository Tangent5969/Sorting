package com.tangent.sorting.controls;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import java.util.Random;

import com.tangent.sorting.ui.sound.MidiAudio;
import com.tangent.sorting.ui.visual.Bar;
import com.tangent.sorting.ui.visual.Image;
import com.tangent.sorting.ui.visual.IntColourPair;
import com.tangent.sorting.sorts.*;


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
    public static boolean sorting = false;
    public static boolean stop = false;
    private static Error errorCode;
    private static RenderMethod renderMode = MainController.RenderMethod.Bars;


    private static SortType selectedSort;
    private static final Random rand = new Random();
    private static ArrayList<IntColourPair> specialElements = new ArrayList<>();
    public static ArrayController arrayController;
    public static MidiAudio audio;
    public static Thread sortThread;
    public static final Object lock = new Object();


    public enum SortType {
        Bogo, Bozo, Bubble, Cocktail, Comb, Exchange, Gnome, Insertion, Merge, OddEven, Pancake, Quick, Selection, Shell, Slow
    }

    public enum RenderMethod {
        Bars, Image
    }

    public enum Error {
        FileBad("Unable to save. File open / not found"),
        FileGood("File saved successfully"),
        SortEnded("SortThread terminated"),
        NoImage("No Image Selected. Drag file onto window"),
        InvalidImage("File does not match requirements"),
        BigImage("Max image size " + maxElements + " pixels");
                ;

        private String message;
        Error(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

    public static void initialise() {
        // initial array size
        arrayController = new ArrayController(10);
        audio = new MidiAudio();
        sortThread = new Thread("sortThread");
        Bar.setWidth();
    }

    public static void reset() {
        sortThread.interrupt();
        audio.stopSound();
        sorting = false;
        specialElements.clear();
        arrayController.reset();
    }

    public static void start() {
        if (!sortThread.isAlive() && selectedSort != null) {
            sorting = true;
            newSort();
        }
        else if (!sorting) {
            synchronized (lock) {
                sorting = true;
                lock.notify();
                arrayController.startTimer();
            }
        }
    }

    public static void pause() {
        if (sorting) {
            arrayController.pauseTimer();
            sorting = false;
            audio.stopSound();
        }
    }

    public static void step() {
        if (selectedSort != null) {
            if (!sortThread.isAlive()) newSort();
            pause();
            synchronized (lock) {
                stop = true;
                lock.notify();
                stop = false;
            }
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

        if (arrayController.isSorted()) {
            arrayController.shuffle();
        }

        Sort sort = null;
        switch (selectedSort) {
            case Bogo:
                sort = new BogoSort(arrayController);
                break;
            case Bozo:
                sort = new BozoSort(arrayController);
                break;
            case Bubble:
                sort = new BubbleSort(arrayController);
                break;
            case Cocktail:
                sort = new CocktailSort(arrayController);
                break;
            case Comb:
                sort = new CombSort(arrayController);
                break;
            case Exchange:
                sort = new ExchangeSort(arrayController);
                break;
            case Gnome:
                sort = new GnomeSort(arrayController);
                break;
            case Insertion:
                sort = new InsertionSort(arrayController);
                break;
            case Merge:
                sort = new MergeSort(arrayController);
                break;
            case OddEven:
                sort = new OddEvenSort(arrayController);
                break;
            case Pancake:
                sort = new PancakeSort(arrayController);
                break;
            case Quick:
                sort = new QuickSort(arrayController);
                break;
            case Selection:
                sort = new SelectionSort(arrayController);
                break;
            case Shell:
                sort = new ShellSort(arrayController);
                break;
            case Slow:
                sort = new SlowSort(arrayController);
                break;
        }

        specialElementsClear();
        arrayController.resetStatistics();
        arrayController.setSortingStatus(true);
        sortThread = new Thread(sort, "sortThread");
        sortThread.start();
    }

    public static void renderArray(ShapeRenderer sr) {
        switch (renderMode) {
            case Bars:
                Bar.renderArray(arrayController, specialElements, lock, sr);
                break;
            case Image:
                Image.renderArray(arrayController, sr);
                break;
        }
    }

    public static void toggleRenderMode() {
        switch (renderMode) {
            case Bars:
                renderMode = RenderMethod.Image;
                break;
            case Image:
                renderMode = RenderMethod.Bars;
                break;
        }
    }

    public static void setRenderMode(RenderMethod mode) {
        renderMode = mode;
    }

    public static void setTotalElements(int totalElements) {
        MainController.reset();
        MainController.arrayController.resize(totalElements);
        Bar.setWidth();
    }

    public static void setSpeed(int speed) {
        MainController.speed = speed;
    }

    public static void randomSort() {
        SortType tempSort;
        do {
            tempSort = SortType.values()[SortType.values().length - rand.nextInt(SortType.values().length) - 1];
        }
        while (tempSort == selectedSort);
        setSelectedSort(tempSort);
    }

    public static String getSelectedSort() {
        if (selectedSort == null) {
            return "none";
        }
        return selectedSort.name();
    }

    public static void setSelectedSort(SortType selectedSort) {
        if (MainController.selectedSort == null) {
            MainController.selectedSort = selectedSort;
        }
        else if (selectedSort != MainController.selectedSort) {
            if (arrayController.isSorting()) reset();
            MainController.selectedSort = selectedSort;
        }
    }

    public static void specialElementsAdd(IntColourPair pair) {
        synchronized (lock) {
            specialElements.add(pair);
        }
    }

    public static void specialElementsSet(int i, IntColourPair pair) {
        synchronized (lock) {
            specialElements.set(i, pair);
        }
    }

    public static void specialElementsRemove(int i) {
        synchronized (lock) {
            specialElements.remove(i);
        }
    }

    public static void specialElementsClear() {
        synchronized (lock) {
            specialElements.clear();
        }
    }

    public static int specialElementsLength() {
        synchronized (lock) {
            return specialElements.size();
        }
    }

    public static void setErrorCode(Error error) {
        errorCode = error;
    }

    public static String getErrorMessage() {
        if (errorCode == null) return "";
        return errorCode.getMessage() + "\nPress C to clear";
    }
}
