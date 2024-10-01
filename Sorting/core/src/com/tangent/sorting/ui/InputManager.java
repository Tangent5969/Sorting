package com.tangent.sorting.ui;

import com.badlogic.gdx.Input;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.controls.Settings;
import com.tangent.utils.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;

public class InputManager extends InputAdapter {
    private Slider selectedSlider;
    private Slider previousSlider;

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        Vector3 coords =  Utils.unproject(x, y, MainController.width, MainController.height);

        for (Button current : Settings.buttonList) {
            if (current.isPressed((int) coords.x, (int) coords.y)) {
                return true;
            }
        }

        for (DropButton dropButton : Settings.dropButtonList) {
            if (dropButton.isPressed((int) coords.x, (int) coords.y)) {
                return true;
            }
        }

        for (Slider slider : Settings.sliderList) {
            if (slider.isSelected((int) coords.x, (int) coords.y)) {
                selectedSlider = slider;
                previousSlider = slider;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        if (selectedSlider != null) {
            selectedSlider.updateValue();
            selectedSlider = null;
            return true;
        }

        return false;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        Vector3 coords =  Utils.unproject(x, y, MainController.width, MainController.height);

        if (selectedSlider != null) {
            selectedSlider.updatePosition((int) coords.x);
            return true;
        }

        return false;
    }

    @Override
    public boolean scrolled (float amountX, float amountY) {
        // down positive
        Vector3 coords =  Utils.unproject(Gdx.input.getX(), Gdx.input.getY(), MainController.width, MainController.height);
        for (DropButton dropButton : Settings.dropButtonList) {
            if (dropButton.scrollDetect((int) coords.x, (int) coords.y)) {
                dropButton.scroll(amountY);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        int amount;
        switch (keycode) {
            case Input.Keys.ENTER:
                MainController.start();
                return true;
            case Input.Keys.SPACE:
                MainController.step();
                return true;
            case Input.Keys.P:
                MainController.pause();
                return true;
            case Input.Keys.R:
                MainController.reset();
                return true;
            case Input.Keys.M:
                MainController.audio.mute();
                return true;
            case Input.Keys.UP:
                amount = 1;
                break;
            case Input.Keys.DOWN:
                amount = -1;
                break;
            case Input.Keys.LEFT:
                amount = -1;
                break;
            case Input.Keys.RIGHT:
                amount = 1;
                break;
            default:
                return false;
        }

        Vector3 coords =  Utils.unproject(Gdx.input.getX(), Gdx.input.getY(), MainController.width, MainController.height);

        for (DropButton dropButton : Settings.dropButtonList) {
            if (dropButton.scrollDetect((int) coords.x, (int) coords.y)) {
                if (keycode == Input.Keys.UP) dropButton.scroll(-1);
                else if (keycode == Input.Keys.DOWN) dropButton.scroll(1);
                else return false;
                return true;
            }
        }

        if (previousSlider != null) {
            previousSlider.incrementValue(amount);
            previousSlider.updatePosition();
            previousSlider.updateValue();
            return true;
        }
        
        return false;
    }

}
