package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.IntColourPair;

import static com.tangent.sorting.controls.MainController.lock;

public abstract class Sort implements Runnable{
    protected ArrayController arrayController;
    protected final String name;
    Sort(ArrayController arrayController, String name) {
        this.arrayController = arrayController;
        this.name = name;
    }

    protected void checkStatus() {
        try {
            Thread.sleep(MainController.speed);
            if (!MainController.sorting || MainController.stop) {
                synchronized (lock) {
                    lock.wait();
                }

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void greenBars() {
        MainController.specialBarsClear();
        int speed;
        if (MainController.speed == 0) {
            speed = 1;
        } else {
            speed = MainController.speed;
        }
        for (int i = 0; i < arrayController.getLength(); i++) {
            MainController.specialBarsAdd(new IntColourPair(i, Color.GREEN));
            Gdx.graphics.requestRendering();
            MainController.audio.playSound(i);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    protected void finished() {
        display();
        greenBars();
        MainController.audio.stopSound();
        arrayController.setSortingStatus(false);
        MainController.sorting = false;
    }

    private void display() {
        System.out.println("Sort : " + name);
        arrayController.display();
    }
}
