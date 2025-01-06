package com.tangent.sorting.ui.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Matrix4;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.controls.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;

public class InputManager extends InputAdapter {
    private Slider selectedSlider;
    private Slider previousSlider;

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        Vector3 coords = unproject(x, y);

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
        Vector3 coords = unproject(x, y);

        if (selectedSlider != null) {
            selectedSlider.updatePosition((int) coords.x);
            return true;
        }

        return false;
    }

    @Override
    public boolean scrolled (float amountX, float amountY) {
        // down positive
        Vector3 coords = unproject(Gdx.input.getX(), Gdx.input.getY());
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
            case Input.Keys.BACKSPACE:
                MainController.randomSort();
                return true;
            case Input.Keys.C:
                MainController.setErrorCode(null);
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

        Vector3 coords = unproject(Gdx.input.getX(), Gdx.input.getY());

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

    // converts screen coordinates to world coordinates
    // derived from camera.unproject
    private static Vector3 unproject (float x, float y) {
        // normalize screen coordinates
        x = (2 * x) / Gdx.graphics.getWidth() - 1;
        y = (2 * (Gdx.graphics.getHeight() - y)) / Gdx.graphics.getHeight() - 1;

        // generates the inverse of the orthographic projection matrix
        Matrix4 proj = new Matrix4();
        proj.setToOrtho(0, MainController.width, 0,  MainController.height, 0, 1);
        Matrix4.inv(proj.val);

        return new Vector3(x, y, 0).prj(proj);
    }

}
