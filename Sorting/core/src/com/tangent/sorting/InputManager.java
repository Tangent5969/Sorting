package com.tangent.sorting;

import com.tangent.sorting.ui.*;
import com.tangent.utils.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;

public class InputManager extends InputAdapter {
    private Slider selectedSlider;

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {

        Vector3 coords =  Utils.unproject(x, y, Controller.width, Controller.height);
        for (Button current : Settings.buttonList) {
            if(current.isPressed((int) coords.x, (int) coords.y)) {
                return true;
            }
        }

        for (DropButton dropButton : Settings.dropButtonList) {
            if(dropButton.isPressed((int) coords.x, (int) coords.y)) {
                return true;
            }
        }

        for (Slider slider : Settings.sliderList) {
            if (slider.isSelected((int) coords.x, (int) coords.y)) {
                selectedSlider = slider;
                return true;
            }
        }

        return true;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        if (selectedSlider != null) {
            selectedSlider.updateValue();
            selectedSlider = null;
        }
        return true;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        Vector3 coords =  Utils.unproject(x, y, Controller.width, Controller.height);
        if (selectedSlider != null) {
            selectedSlider.updatePosition((int) coords.x);
        }

        return true;
    }

    @Override
    public boolean scrolled (float amountX, float amountY) {
        System.out.println(amountY);
        // down positive
        Vector3 coords =  Utils.unproject(Gdx.input.getX(), Gdx.input.getY(), Controller.width, Controller.height);
        for (DropButton dropButton : Settings.dropButtonList) {
            if (dropButton.scrollDetect((int) coords.x, (int) coords.y)) {
                dropButton.scroll(amountY);
                return true;
            }
        }

        return true;
    }
}
