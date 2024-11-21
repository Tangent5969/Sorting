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
    private ButtonMethods.Method method;
    private String text;

    public Button(int width, int height, int posX, int posY, ButtonMethods.Method method) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
        this.colour = Color.GRAY;
        this.method = method;
        this.text = "";
    }

    public Button(int width, int height, int posX, int posY, String text, ButtonMethods.Method method) {
        this(width, height, posX, posY, method);
        this.text = text;
    }

    protected boolean collisionCheck (int mouseX, int mouseY) {
        return mouseX >= posX && mouseX <= posX + width && mouseY >= posY && mouseY <= posY + height;
    }

    protected boolean isPressed(int mouseX, int mouseY) {
        if (collisionCheck(mouseX, mouseY)) {
            ButtonMethods.activateMethod(method);
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

    public ButtonMethods.Method getMethod() {
        return method;
    }

    public void setMethod(ButtonMethods.Method method) {
        this.method = method;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

