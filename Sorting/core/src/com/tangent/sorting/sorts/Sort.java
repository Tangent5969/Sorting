package com.tangent.sorting.sorts;

import com.tangent.sorting.Controller;

import static com.tangent.sorting.Controller.lock;

public abstract class Sort implements Runnable{
    protected int[] array;
    Sort(int[] array) {
        this.array = array;
    }

    protected void checkStatus() {
        try {
            Thread.sleep(Controller.speed);
            if (!Controller.sorting || Controller.stop) {
                synchronized (lock) {
                    lock.wait();
                }

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
