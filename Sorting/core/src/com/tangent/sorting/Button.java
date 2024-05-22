package com.tangent.sorting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.function.Consumer;
public class Button {

    private Color colour;
    private int width;
    private int height;
    private int posX;
    private int posY;
    private Consumer<> method;
    private String text;


    Button(int width, int height, int posX, int posY) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;

    }

    public void render(ShapeRenderer sr) {
        sr.setColor(colour);
        sr.rect((Settings.settingsWidth * Controller.widthMultiplier) + (posX * Settings.settingsWidthMultiplier), posY, width * Settings.settingsWidthMultiplier, height);
    }


    private void tempMethod() {

    }
}
