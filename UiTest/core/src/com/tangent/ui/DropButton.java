package com.tangent.ui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DropButton extends Button{
    private Button[] subButtons;
    private Boolean show;

    DropButton(int width, int height, int posX, int posY, TextMethodPair[] subParams) {
        super(width, height, posX, posY, ButtonMethods.Method.Blank);
        this.show = false;
        this.subButtons = new Button[subParams.length];
        populateSubButtons(subParams);
    }

    public void populateSubButtons(TextMethodPair[] subParams) {
        for (int i = 0; i < subParams.length; i++) {
            TextMethodPair params = subParams[i];
            subButtons[i] = new Button(this.getWidth(), getHeight(), getPosX(), getPosY() - (i+1) * getHeight(), params.getMethod());
            subButtons[i].setText(params.getText());
        }
    }

  @Override
  public boolean isPressed(int mouseX, int mouseY) {
     if (collisionCheck(mouseX, mouseY)) {
         show = !show;
         return true;
     }
     return false;
  }


    @Override
    public void render(ShapeRenderer sr) {
        super.render(sr);
        if (show) {
            for (Button sub : subButtons) {
                sub.render(sr);
            }
        }
    }
}
