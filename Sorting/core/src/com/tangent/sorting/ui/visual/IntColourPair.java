package com.tangent.sorting.ui.visual;

import com.badlogic.gdx.graphics.Color;

public class IntColourPair {
    private final int num;
    private final Color colour;
    public IntColourPair(int num, Color colour) {
        this.num = num;
        this.colour = colour;
    }

    public int getNum() {
        return num;
    }

    public Color getColour() {
        return colour;
    }
}
