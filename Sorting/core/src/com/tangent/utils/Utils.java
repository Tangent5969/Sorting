package com.tangent.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Utils {

    // populates an array with numbers 1 to n
    public static void populateArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }

    // swap the position of two elements in an array
    public static void swap(int[] array, int pos1, int pos2) {
        int temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }

    // reverse the elements in an array
    public static void reverse(int[] array) {
        int length = array.length - 1;
        for (int i = 0; i < (length + 1)/2; i++) {
            swap(array, i, length - i);
        }
    }

    // shuffle the elements in an array
    // based of fisher yates shuffle
    public static void shuffle(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, i, rand.nextInt(i, array.length));
        }
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i+1]) {
                return false;
            }
        }
        return true;
    }

    // converts screen coordinates to world coordinates
    public static Vector3 unproject (float x, float y, float width, float height) {
        // normalize screen coordinates
        x = (2 * x) / Gdx.graphics.getWidth() - 1;
        y = (2 * (Gdx.graphics.getHeight() - y)) / Gdx.graphics.getHeight() - 1;

        // generates the inverse of the orthographic projection matrix
        Matrix4 proj = new Matrix4();
        proj.setToOrtho(0, width, 0,  height, 0, 1);
        Matrix4.inv(proj.val);

        return new Vector3(x, y, 0).prj(proj);
    }
}
