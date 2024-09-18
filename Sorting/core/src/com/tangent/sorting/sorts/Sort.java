package com.tangent.sorting.sorts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tangent.sorting.Controller;
import com.tangent.sorting.IntColourPair;

import static com.tangent.sorting.Controller.lock;

public abstract class Sort implements Runnable{
    protected int[] array;
    Sort(int[] array) {
        this.array = array;
    }

    protected void checkStatus() {
        try {
            Thread.sleep(Controller.speed);
            while (!Controller.sorting || Controller.stop) {
                synchronized (lock) {
                    lock.wait();
                }

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void greenBars() {
        Controller.specialBarsClear();
        for (int i = 0; i < array.length; i++) {
            Controller.specialBarsAdd(new IntColourPair(i, Color.GREEN));
            Gdx.graphics.requestRendering();
            try {
                Thread.sleep(Controller.speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
