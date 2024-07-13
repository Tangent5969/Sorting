package com.tangent.sorting;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;

public class Settings {
    private static int startX = Bar.getWidth() * Controller.totalElements;
    private static int settingsWidth = Controller.width - startX;
    private static int heightMultiplier = 10;
    private static int settingsHeight = Controller.height / heightMultiplier;
    static Button[] buttonList = new Button[6];

    //private static Button startButton = new Button(width, 100, 0, 0, Button.ButtonMethod.Start);


    static void setButtons() {
        int buttonWidth = (settingsWidth / 2) - 50;
        int buttonHeight = 50;
        int buttonSpacer = 10;

        buttonList[0] = new Button(buttonWidth, buttonHeight, 0, settingsHeight - buttonHeight, Button.ButtonMethod.Start);
        buttonList[1] = new Button(buttonWidth, buttonHeight, buttonWidth + 100, settingsHeight - buttonHeight, Button.ButtonMethod.Step);
        buttonList[2] = new Button(buttonWidth, buttonHeight, 0, settingsHeight - buttonHeight * 2 - buttonSpacer, Button.ButtonMethod.Start);
        buttonList[3] = new Button(buttonWidth, buttonHeight, buttonWidth + 100, settingsHeight - buttonHeight * 2 - buttonSpacer, Button.ButtonMethod.Reset);
        buttonList[4] = new Button(buttonWidth, buttonHeight, 0, settingsHeight - buttonHeight * 3 - buttonSpacer * 2, Button.ButtonMethod.Mute);
        buttonList[5] = new Button(buttonWidth, buttonHeight, buttonWidth + 100, settingsHeight - buttonHeight * 3 - buttonSpacer * 2, Button.ButtonMethod.Random);

        offsetButtons();
    }

    static void offsetButtons() {
        for (Button button : buttonList) {
            button.setPosX(button.getPosX() + startX);
            button.setPosY(button.getPosY() * heightMultiplier);
            button.setHeight(button.getHeight() * heightMultiplier);
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
    }

}
