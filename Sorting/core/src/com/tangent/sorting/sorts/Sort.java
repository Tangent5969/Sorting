package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.ui.visual.IntColourPair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.tangent.sorting.controls.MainController.lock;

public abstract class Sort implements Runnable {
    protected final ArrayController arrayController;
    protected final String name;

    Sort(ArrayController arrayController, String name) {
        this.arrayController = arrayController;
        this.name = name;
    }

    private void checkStatus() {
        try {
            Thread.sleep(MainController.speed);
            if (!MainController.sorting || MainController.stop) {
                synchronized (lock) {
                    lock.wait();
                }
            }
        } catch (InterruptedException e) {
            MainController.setErrorCode(MainController.Error.SortEnded);
            throw new RuntimeException(e);
        }
    }

    protected void update() {
        Gdx.graphics.requestRendering();
        checkStatus();
    }

    private void greenBars() {
        MainController.specialElementsClear();
        int speed;
        if (MainController.speed == 0) {
            speed = 1;
        } else {
            speed = MainController.speed;
        }
        for (int i = 0; i < arrayController.getLength(); i++) {
            MainController.specialElementsAdd(new IntColourPair(i, Color.GREEN));
            Gdx.graphics.requestRendering();
            MainController.audio.playSound(i);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                MainController.setErrorCode(MainController.Error.SortEnded);
                throw new RuntimeException(e);
            }
        }
    }

    protected void finished() {
        arrayController.pauseTimer();
        display();
        try {
            fileOutput();
            MainController.setErrorCode(MainController.Error.FileGood);
        } catch (IOException e) {
            MainController.setErrorCode(MainController.Error.FileBad);
            throw new RuntimeException(e);
        } finally {
            greenBars();
            MainController.audio.stopSound();
            MainController.sorting = false;
            arrayController.setSortingStatus(false);
        }
    }

    private void display() {
        System.out.println("\nSort : " + name);
        arrayController.display();
    }

    private void fileOutput() throws IOException {
        File file = new File("data.csv");
        boolean fileFlag = file.createNewFile();
        FileWriter writer = new FileWriter(file, true);
        if (fileFlag) {
            writer.write("Date,Sort,Elements,Time (Short),Time (Raw),Comparisons,Swaps,Writes,Aux Writes");
        }
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        writer.write(("\n" + currentTime + "," + name + "," + arrayController.export()));
        writer.close();
    }

}
