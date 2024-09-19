package com.tangent.sorting.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Slider {
    private static final int radius = 50;
    private static final int height = 15;
    private float min;
    private float max;
    private float value;
    private int posX;
    private int posY;
    private int width;
    private int position;
    private String text;
    private boolean decimal;
    private ButtonMethods.SlideMethod method;

    public Slider(float min, float max, float value, int posX, int posY, int width, boolean decimal, ButtonMethods.SlideMethod method) {
        this.min = min;
        this.max = max;
        this.value = value;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.position = (int) ((posX - (float) width / 2) + (width * ((float) (value - min) / (max - min))));
        this.decimal = decimal;
        this.method = method;
        this.text = "";
    }

    public Slider(float min, float max, float value, int posX, int posY, int width, boolean decimal, ButtonMethods.SlideMethod method, String text) {
        this(min, max, value, posX, posY, width, decimal, method);
        this.text = text;
    }


    private void updatePosition() {
        this.position = (int) ((posX - (float) width / 2) + (width * ((value - min) / (max - min))));
    }

    protected void updatePosition(int mouseX) {
        if (mouseX <= posX - width / 2) {
            position = posX - width / 2;
        }
        else if (mouseX >= posX + width / 2) {
            position = posX + width / 2;
        }
        else {
            position = mouseX;
        }
        if (decimal) {
            value = (min + ((position - (posX - (float) width / 2)) / ((posX + (float) width / 2) - (posX - (float) width / 2))) * (max - min));
        } else {
            value = (int) (min + ((position - (posX - (float) width / 2)) / ((posX + (float) width / 2) - (posX - (float) width / 2))) * (max - min));
        }
    }

    protected void updateValue() {
        ButtonMethods.updateSlideValue(method, value);
    }

    protected boolean isSelected(int mouseX, int mouseY) {
        return mouseX >= position - radius && mouseX <= position + radius && mouseY >= posY - radius && mouseY <= posY + radius;
    }

    public void render(ShapeRenderer sr) {
        sr.rect(posX - width / 2, posY - height / 2, width, height);
        sr.circle(position, posY, radius);

    }

    public void renderText(SpriteBatch batch, BitmapFont font) {
        GlyphLayout layout;
        if (decimal) {
            layout = new GlyphLayout(font, text + String.format("%.1f", value));
        } else {
            layout = new GlyphLayout(font, text + (int) value);
        }
        font.draw(batch, layout, posX - layout.width / 2, posY + height + layout.height + radius);
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
        updatePosition();
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
