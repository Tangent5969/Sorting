package com.tangent.ui;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class InputManager extends InputAdapter {
    private Slider selectedSlider;

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {

        Vector3 coords =  Utils.unproject(x, y, 1000, 1000);
        for (Button current : Controller.buttonList) {
            System.out.println(current.isPressed((int) coords.x, (int) coords.y));
        }

        for (Slider slider : Controller.sliderList) {
            System.out.println(slider.isSelected((int) coords.x, (int) coords.y));
            if (slider.isSelected((int) coords.x, (int) coords.y)) {
                selectedSlider = slider;
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
        Vector3 coords =  Utils.unproject(x, y, 1000, 1000);
        if (selectedSlider != null) {
            selectedSlider.updatePosition((int) coords.x);
        }

        return true;
    }

    @Override
    public boolean scrolled (float amountX, float amountY) {

        return true;
    }


}
