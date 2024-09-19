package com.tangent.sorting.controls;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tangent.sorting.Audio;
import com.tangent.sorting.Bar;
import com.tangent.sorting.ui.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Settings {
    private static final int startX = Bar.getWidth() * MainController.arrayController.getLength();
    private static final int settingsWidth = MainController.width - startX;
    private static final int heightMultiplier = 10;
    private static final int settingsHeight = MainController.height / heightMultiplier;

    public static Button[] buttonList = new Button[6];
    public static DropButton[] dropButtonList = new DropButton[2];
    public static Slider[] sliderList = new Slider[3];


    public static void initialise() {
        setButtons();
        setDropButtons();
        setSliders();
        offsetSettings();
    }


    private static void setButtons() {
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

    private static void setDropButtons() {
        TextMethodPair[] shuffleButton = {new TextMethodPair("Shuffle", ButtonMethods.Method.Shuffle), new TextMethodPair("Reverse", ButtonMethods.Method.Reverse)};
        TextMethodPair[] sortButton = {new TextMethodPair("Bubble", ButtonMethods.Method.Bubble), new TextMethodPair("Bogo", ButtonMethods.Method.Bogo), new TextMethodPair("Bozo", ButtonMethods.Method.Bozo)};

        int buttonWidth = (settingsWidth / 2) - 50;
        int buttonHeight = 50;

        dropButtonList[0] = new DropButton(buttonWidth, buttonHeight, 0, 500, "Shuffle", shuffleButton);
        dropButtonList[1] = new DropButton(buttonWidth, buttonHeight, buttonWidth + 100, 500, "Sort", sortButton);
    }

    private static void setSliders() {
        int sliderStart = settingsHeight - 210;
        int sliderWidth = (int) (settingsWidth * 0.75);
        int sliderSpacer = 35;

        sliderList[0] = new Slider(MainController.minSpeed, MainController.maxSpeed, MainController.speed, settingsWidth/2, sliderStart, sliderWidth, false, ButtonMethods.SlideMethod.Speed, "Speed");
        sliderList[1] = new Slider(MainController.minElements, MainController.maxElements, MainController.arrayController.getLength(), settingsWidth/2, sliderStart - sliderSpacer, sliderWidth, false, ButtonMethods.SlideMethod.Size, "Size");
        sliderList[2] = new Slider(Audio.minPitch, Audio.maxPitch, Audio.midPitch, settingsWidth/2, sliderStart - sliderSpacer * 2, sliderWidth, true, ButtonMethods.SlideMethod.Pitch, "Pitch");
    }

    public static void renderStatistics(SpriteBatch batch, BitmapFont font) {
        GlyphLayout layout = new GlyphLayout(font, "Comparisons\n" + MainController.arrayController.getComparisons() + "\nSwaps\n" + MainController.arrayController.getSwaps() + "\nWrites\n" + MainController.arrayController.getWrites());
        font.draw(batch, layout, startX + 10, (settingsHeight - 315) * heightMultiplier);
    }

    private static void offsetSettings() {
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




    public static void render(ShapeRenderer sr) {
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

    public static void renderText(SpriteBatch batch, BitmapFont font) {
        for (Button button : buttonList) {
            button.renderText(batch, font);
        }
        for (DropButton dropButton : dropButtonList) {
            dropButton.renderText(batch, font);
        }
        for (Slider slider : sliderList) {
            slider.renderText(batch, font);
        }
        renderStatistics(batch, font);
    }

}
