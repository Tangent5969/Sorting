package com.tangent.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        this.text = "";

    }

    public void activateMethod() {
        switch (method) {
            case Start:
                break;
            case Pause:
                break;
            case Step:
                break;
            case Reset:
                break;
            case Mute:
                break;
            case Random:
                break;
        }
    }

    public boolean isPressed(int mouseX, int mouseY) {
        if (mouseX >= posX && mouseX <= posX + width && mouseY >= posY && mouseY <= posY + height) {
            activateMethod();
            return true;
        }
        return false;
    }

    public void render(ShapeRenderer sr) {
        sr.setColor(colour);
        sr.rect(posX, posY, width, height);
    }

    public void renderText(SpriteBatch batch, BitmapFont font) {
        GlyphLayout layout = new GlyphLayout(font, text);
        font.draw(batch, layout, (posX + (float) width / 2) - (layout.width / 2), (posY + (float) height / 2) + (layout.height / 2));
    }


    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public ButtonMethod getMethod() {
        return method;
    }

    public void setMethod(ButtonMethod method) {
        this.method = method;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

