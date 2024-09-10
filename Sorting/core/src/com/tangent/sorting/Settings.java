package com.tangent.sorting;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tangent.sorting.ui.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Settings {
    private static int startX = Bar.getWidth() * Controller.totalElements;
    private static int settingsWidth = Controller.width - startX;
    private static int heightMultiplier = 10;
    private static int settingsHeight = Controller.height / heightMultiplier;
    static Button[] buttonList = new Button[6];
    static DropButton[] dropButtonList = new DropButton[2];
    static Slider[] sliderList = new Slider[3];





    static void setButtons() {
        int buttonWidth = (settingsWidth / 2) - 50;
        int buttonHeight = 50;
        int buttonSpacer = 10;

        buttonList[0] = new Button(buttonWidth, buttonHeight, 0, settingsHeight - buttonHeight, "Start", ButtonMethods.Method.Start);
        buttonList[1] = new Button(buttonWidth, buttonHeight, buttonWidth + 100, settingsHeight - buttonHeight, "Pause", ButtonMethods.Method.Pause);
        buttonList[2] = new Button(buttonWidth, buttonHeight, 0, settingsHeight - buttonHeight * 2 - buttonSpacer, "Step", ButtonMethods.Method.Step);
        buttonList[3] = new Button(buttonWidth, buttonHeight, buttonWidth + 100, settingsHeight - buttonHeight * 2 - buttonSpacer, "Reset", ButtonMethods.Method.Reset);
        buttonList[4] = new Button(buttonWidth, buttonHeight, 0, settingsHeight - buttonHeight * 3 - buttonSpacer * 2, "Mute", ButtonMethods.Method.Mute);
        buttonList[5] = new Button(buttonWidth, buttonHeight, buttonWidth + 100, settingsHeight - buttonHeight * 3 - buttonSpacer * 2, "Random", ButtonMethods.Method.Random);


    }

    static void setDropButtons() {
        TextMethodPair[] shuffleButton = {new TextMethodPair("Shuffle", ButtonMethods.Method.Shuffle), new TextMethodPair("Reverse", ButtonMethods.Method.Reverse)};
        TextMethodPair[] sortButton = {new TextMethodPair("Bubble", ButtonMethods.Method.Bubble), new TextMethodPair("Bogo", ButtonMethods.Method.Bogo), new TextMethodPair("Bozo", ButtonMethods.Method.Bozo)};

        int buttonWidth = (settingsWidth / 2) - 50;
        int buttonHeight = 50;

        dropButtonList[0] = new DropButton(buttonWidth, buttonHeight, 0, 500, "Shuffle", shuffleButton);
        dropButtonList[1] = new DropButton(buttonWidth, buttonHeight, buttonWidth + 100, 500, "Sort", sortButton);
    }

    static void setSliders() {
        int sliderStart = settingsHeight - 210;
        int sliderWidth = (int) (settingsWidth * 0.75);
        int sliderSpacer = 35;

        sliderList[0] = new Slider(Controller.minSpeed, Controller.maxSpeed, Controller.speed, settingsWidth/2, sliderStart, sliderWidth, ButtonMethods.SlideMethod.Speed, "Speed");
        sliderList[1] = new Slider(Controller.minElements, Controller.maxElements, Controller.totalElements, settingsWidth/2, sliderStart - sliderSpacer, sliderWidth,ButtonMethods.SlideMethod.Size, "Size");
        sliderList[2] = new Slider(1, 5, 3, settingsWidth/2, sliderStart - sliderSpacer * 2, sliderWidth,ButtonMethods.SlideMethod.Pitch, "Pitch");

    }

    static void offsetSettings() {
        for (Button button : buttonList) {
            button.setPosX(button.getPosX() + startX);
            button.setPosY(button.getPosY() * heightMultiplier);
            button.setHeight(button.getHeight() * heightMultiplier);
        }

        for (DropButton dropButton : dropButtonList) {
            dropButton.setPosX(dropButton.getPosX() + startX);
            dropButton.setPosY(dropButton.getPosY() * heightMultiplier);
            dropButton.setHeight(dropButton.getHeight() * heightMultiplier);
        }

        for (Slider slider : sliderList) {
            slider.setPosX(slider.getPosX() + startX);
            slider.setPosY(slider.getPosY() * heightMultiplier);
        }
    }

    public static int getStartX() {
        return startX;
    }

    public static int getWidth() {
        return settingsWidth;
    }

    public static int getHeightMultiplier() {
        return heightMultiplier;
    }



    static void render(ShapeRenderer sr) {
        for (Button button : buttonList) {
            button.render(sr);
        }
        for (DropButton dropButton : dropButtonList) {
            dropButton.render(sr);
        }
        for (Slider slider : sliderList) {
            slider.render(sr);
        }
    }

    static void renderText(SpriteBatch batch, BitmapFont font) {
        for (Button button : buttonList) {
            button.renderText(batch, font);
        }
        for (DropButton dropButton : dropButtonList) {
            dropButton.renderText(batch, font);
        }
        for (Slider slider : sliderList) {
            slider.renderText(batch, font);
        }
    }

}
