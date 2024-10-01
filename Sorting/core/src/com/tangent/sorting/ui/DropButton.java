package com.tangent.sorting.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DropButton extends Button{
    private Button[] subButtons;
    private Boolean show;
    private Button[] currentShown;
    private int currentPos;

    public DropButton(int width, int height, int posX, int posY, TextMethodPair[] subParams) {
        super(width, height, posX, posY, ButtonMethods.Method.Blank);
        this.show = false;
        this.subButtons = new Button[subParams.length];
        this.currentPos = 0;
        if (subParams.length < 5) {
            this.currentShown = new Button[subButtons.length];
        }
        else {
            this.currentShown = new Button[5];
        }
        populateSubButtons(subParams);
    }

    public DropButton(int width, int height, int posX, int posY, String text, TextMethodPair[] subParams) {
        this(width, height, posX, posY, subParams);
        this.setText(text);
    }

    private void populateSubButtons(TextMethodPair[] subParams) {
        for (int i = 0; i < subParams.length; i++) {
            TextMethodPair params = subParams[i];
            subButtons[i] = new Button(getWidth(), getHeight(), getPosX(), getPosY(), params.getText(), params.getMethod());
        }
        updateCurrentShown();
    }

    private void updateCurrentShown() {
        System.arraycopy(subButtons, currentPos, currentShown, 0, currentShown.length);
        for (int i = 0; i < currentShown.length; i++) {
            currentShown[i].setPosY(getPosY() - (i+1) * getHeight());
        }
    }

  @Override
  protected boolean isPressed(int mouseX, int mouseY) {
     if (collisionCheck(mouseX, mouseY)) {
         show = !show;
         return true;
     }

     if (show) {
         for (Button sub : currentShown) {
             if (sub.isPressed(mouseX, mouseY)) {
                 return true;
             }
         }
     }
     return false;
  }

  protected boolean scrollDetect(int mouseX, int mouseY) {
        if (show) {
            return mouseX >= getPosX() && mouseX <= getPosX() + getWidth() && mouseY <= getPosY() && mouseY >= getPosY() - currentShown.length * getHeight();
        }
        return false;
  }

  protected void scroll(float scrollAmount) {
      // down positive
      if (scrollAmount > 0) {
            if (currentPos + currentShown.length < subButtons.length) {
                currentPos++;
                updateCurrentShown();
            }
        }
        else {
            if (currentPos > 0) {
                currentPos--;
                updateCurrentShown();
            }
        }
  }

    @Override
    public void render(ShapeRenderer sr) {
        super.render(sr);
        if (show) {
            for (Button sub : currentShown) {
                sub.render(sr);
            }
        }
    }

    @Override
    public void renderText(SpriteBatch batch, BitmapFont font) {
        super.renderText(batch, font);
        if (show) {
            for (Button sub : currentShown) {
                sub.renderText(batch, font);
            }
        }
    }

    @Override
    public void setPosX(int posX) {
        super.setPosX(posX);
        for (Button sub : subButtons) {
            sub.setPosX(posX);
        }
        updateCurrentShown();
    }

    @Override
    public void setPosY(int posY) {
        super.setPosY(posY);
        for (Button sub : subButtons) {
            sub.setPosY(posY);
        }
        updateCurrentShown();
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        for (Button sub : subButtons) {
            sub.setHeight(height);
        }
        updateCurrentShown();

    }



    public Button[] getSubButtons() {
        return subButtons;
    }

    public void setSubButtons(Button[] subButtons) {
        this.subButtons = subButtons;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Button[] getCurrentShown() {
        return currentShown;
    }

    public void setCurrentShown(Button[] currentShown) {
        this.currentShown = currentShown;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }
}
