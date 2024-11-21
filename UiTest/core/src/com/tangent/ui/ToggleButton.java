package com.tangent.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ToggleButton extends Button {
    private static final int border = 3;
    private boolean status;

    public ToggleButton(int width, int height, int posX, int posY, ButtonMethods.Method method) {
        super(width, height, posX, posY, method);
    }

    public ToggleButton(int width, int height, int posX, int posY, String text, ButtonMethods.Method method) {
        this(width, height, posX, posY, method);
        this.setText(text);
    }

    @Override
    protected boolean isPressed(int mouseX, int mouseY) {
        if (super.isPressed(mouseX, mouseY)) {
            status = !status;
            return true;
        }
        return false;
    }

    @Override
    public void render(ShapeRenderer sr) {
        if (status) sr.setColor(Color.GREEN);
        else sr.setColor(Color.RED);
        sr.rect(getPosX(), getPosY(), getWidth(), getHeight());
        sr.setColor(getColour());
        sr.rect(getPosX() + border, getPosY() + border, getWidth() - border * 2, getHeight() - border * 2);
    }
}

