package com.tangent.sorting.controls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tangent.sorting.ui.input.*;
import com.tangent.sorting.ui.sound.MidiAudio;

public class Settings {
    private static final int startX = (int) (MainController.width * MainController.widthMultiplier);
    private static final int settingsWidth = MainController.width - startX;

    public static Button[] buttonList;
    public static DropButton[] dropButtonList;
    public static Slider[] sliderList;


    public static void initialise() {
        setButtons();
        setDropButtons();
        setSliders();
    }


    private static void setButtons() {
        buttonList = new Button[7];
        int buttonWidth = (settingsWidth / 2) - 50;
        int buttonHeight = 500;
        int buttonSpacer = 100;

        buttonList[0] = new Button(buttonWidth, buttonHeight, startX, MainController.height - buttonHeight, "Start", ButtonMethods.Method.Start);
        buttonList[1] = new Button(buttonWidth, buttonHeight, startX + buttonWidth + 100, MainController.height - buttonHeight, "Pause", ButtonMethods.Method.Pause);
        buttonList[2] = new Button(buttonWidth, buttonHeight, startX, MainController.height - buttonHeight * 2 - buttonSpacer, "Step", ButtonMethods.Method.Step);
        buttonList[3] = new Button(buttonWidth, buttonHeight, startX + buttonWidth + 100, MainController.height - buttonHeight * 2 - buttonSpacer, "Reset", ButtonMethods.Method.Reset);
        buttonList[4] = new Button(buttonWidth, buttonHeight, startX, MainController.height - buttonHeight * 3 - buttonSpacer * 2, "Mute", ButtonMethods.Method.Mute);
        buttonList[5] = new Button(buttonWidth, buttonHeight, startX + buttonWidth + 100, MainController.height - buttonHeight * 3 - buttonSpacer * 2, "Random", ButtonMethods.Method.Random);
        buttonList[6] = new Button(settingsWidth, buttonHeight, startX, 0, "Render Switch", ButtonMethods.Method.Render);
    }

    private static void setDropButtons() {
        dropButtonList = new DropButton[2];

        TextMethodPair[] shuffleButton = {new TextMethodPair("Shuffle", ButtonMethods.Method.Shuffle), new TextMethodPair("Reverse", ButtonMethods.Method.Reverse)};
        // works on assumption SortType name matches that of equivalent ButtonMethod
        TextMethodPair[] sortButton = new TextMethodPair[MainController.SortType.values().length];
        for (int i = 0; i < MainController.SortType.values().length; i++) {
            String sort = MainController.SortType.values()[i].name();
            sortButton[i] = new TextMethodPair(sort, ButtonMethods.Method.valueOf(sort));
        }

        int buttonWidth = (settingsWidth / 2) - 50;
        int buttonHeight = 500;

        dropButtonList[0] = new DropButton(buttonWidth, buttonHeight, startX, 4150, "Shuffle", shuffleButton);
        dropButtonList[1] = new DropButton(buttonWidth, buttonHeight, startX + buttonWidth + 100, 4150, "Sort", sortButton);
    }

    private static void setSliders() {
        sliderList = new Slider[4];
        int sliderStart = MainController.height - 2100;
        int sliderWidth = (int) (settingsWidth * 0.75);
        int sliderSpacer = 350;

        sliderList[0] = new Slider(MainController.minSpeed, MainController.maxSpeed, MainController.speed, startX + settingsWidth / 2, sliderStart, sliderWidth, false, ButtonMethods.SlideMethod.Speed, "Speed");
        sliderList[1] = new Slider(MainController.minElements, MainController.maxElements, MainController.arrayController.getLength(), startX + settingsWidth / 2, sliderStart - sliderSpacer, sliderWidth, false, ButtonMethods.SlideMethod.Size, "Size");
        sliderList[2] = new Slider(MidiAudio.minVolume, MidiAudio.maxVolume, MainController.audio.getVolume(), startX + settingsWidth / 2, sliderStart - sliderSpacer * 2, sliderWidth, false, ButtonMethods.SlideMethod.Volume, "Volume");
        sliderList[3] = new Slider(MidiAudio.minPitch, MidiAudio.maxPitch, MidiAudio.midPitch, startX + settingsWidth / 2, sliderStart - sliderSpacer * 3, sliderWidth, true, ButtonMethods.SlideMethod.Pitch, "Pitch");
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

    private static void renderStatistics(SpriteBatch batch, BitmapFont font) {
        GlyphLayout layout = new GlyphLayout(font, "Sort\n" + MainController.getSelectedSort() + MainController.arrayController.settingsDisplay());
        font.draw(batch, layout, startX + 10, 6650);
    }

    private static void renderError(SpriteBatch batch, BitmapFont font) {
        GlyphLayout layout = new GlyphLayout(font, MainController.getErrorMessage(), Color.RED, 990, 1000, true);
        font.draw(batch, layout, startX + 10, 1500);
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
        renderError(batch, font);
    }
}
