package com.tangent.sorting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Button {

    private Color colour;
    private int width;
    private int height;
    private int posX;
    private int posY;
    private ButtonMethod method;

    private String text;

    public enum ButtonMethod {
        Start, Pause, Step, Reset, Mute, Random
    }


    Button(int width, int height, int posX, int posY, ButtonMethod method) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
        this.colour = Color.GRAY;
        this.method = method;

    }

    public void activateMethod() {
        switch (method) {
            case Start -> {}
            case Pause -> {}
            case Step -> {}
            case Reset -> {}
            case Mute -> {}
            case Random -> {}

        }
    }

    public void render(ShapeRenderer sr) {
        sr.setColor(colour);
        sr.rect((Settings.settingsWidth * Controller.widthMultiplier) + (posX * Settings.settingsWidthMultiplier), posY, width, height);
        //sr.rect(posX, posY, width, height);
    }









}

