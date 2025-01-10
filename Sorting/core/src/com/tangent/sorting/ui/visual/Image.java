package com.tangent.sorting.ui.visual;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;
import com.tangent.sorting.controls.Settings;
import com.tangent.sorting.ui.input.Slider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private static final Texture blankImage = new Texture("blankImage.png");
    private static Color[][] imageArray;
    private static int width;
    private static int height;
    private static float xScale;
    private static float yScale;

    public static void selectImage(String path) throws IOException, NullPointerException {
        BufferedImage image = ImageIO.read(new File(path));
        if (image.getWidth() * image.getHeight() > MainController.maxElements) {
            MainController.setErrorCode(MainController.Error.BigImage);
            return;
        }

        width = image.getWidth();
        height = image.getHeight();
        xScale = (float) MainController.width * MainController.widthMultiplier / width;
        yScale = (float) MainController.height / height;

        imageArray = convertImage(image);
        MainController.setTotalElements(width * height);
        for (Slider slider : Settings.sliderList) {
            if (slider.getText().equals("Size")) {
                slider.setValue(width * height);
                slider.updatePosition();
                break;
            }
        }
    }


    public static void resetImage() {
        if (imageArray != null) {
            imageArray = null;
            MainController.setErrorCode(MainController.Error.ImageReset);
        }
    }

    private static Color[][] convertImage(BufferedImage image) {
        Color[][] pixels = new Color[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                // converts ARGB to RGBA
                rgb = (rgb & 0x00FFFFFF) << 8 | (rgb & 0xFF000000) >>> 24;
                pixels[x][image.getHeight() - y - 1] = new Color(rgb);
            }
        }
        return pixels;
    }

    public static void renderArray(ArrayController arrayController, ShapeRenderer sr) {
        if (imageArray == null) return;

        for (int i = 0; i < arrayController.getLength(); i++) {
            sr.setColor(imageArray[(arrayController.getElement(i) - 1) % (width)][(arrayController.getElement(i) - 1) / width]);
            sr.rect((i % width) * xScale, (i / width) * yScale, xScale, yScale);
        }
    }

    public static void renderBlankImage(SpriteBatch batch) {
        if (imageArray == null && MainController.getRenderMode() == MainController.RenderMethod.Image) {
            batch.draw(blankImage, 0, 0, MainController.width * MainController.widthMultiplier, MainController.height, 0, 1, 1, 0);
        }
    }
}
