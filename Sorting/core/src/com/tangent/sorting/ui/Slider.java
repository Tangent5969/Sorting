package com.tangent.sorting.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Slider {
    private static final int radius = 50;
    private static final int height = 15;
    private int min;
    private int max;
    private int value;
    private int posX;
    private int posY;
    private int width;
    private int position;
    private String text;

    public Slider(int min, int max, int value, int posX, int posY, int width) {
        this.min = min;
        this.max = max;
        this.value = value;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.position = (int) ((posX - (float) width / 2) + (width * ((float) (value - min) / (max - min))));
        this.text = "";
    }

    public Slider(int min, int max, int value, int posX, int posY, int width, String text) {
        this(min, max, value, posX, posY, width);
        this.text = text;
    }

    public void updatePosition(int mouseX) {
        if (mouseX <= posX - width / 2) {
            position = posX - width / 2;
        }
        else if (mouseX >= posX + width / 2) {
            position = posX + width / 2;
        }
        else {
            position = mouseX;
        }
        value = (int) (min + ((position - (posX - (float) width / 2)) / ((posX + (float) width / 2) - (posX - (float) width / 2))) * (max - min));
    }

    public void updateValue() {

    }

    public boolean isSelected(int mouseX, int mouseY) {
        return mouseX >= position - radius && mouseX <= position + radius && mouseY >= posY - radius && mouseY <= posY + radius;
    }

    public void render(ShapeRenderer sr) {
        sr.rect(posX - width / 2, posY - height / 2, width, height);
        sr.circle(position, posY, radius);

    }

    public void renderText(SpriteBatch batch, BitmapFont font) {
        GlyphLayout layout = new GlyphLayout(font, text +  value);
        font.draw(batch, layout, posX - layout.width / 2, posY + height + layout.height + radius);
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text + " : ";
    }
}
