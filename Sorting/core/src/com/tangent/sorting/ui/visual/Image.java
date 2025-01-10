package com.tangent.sorting.ui.visual;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tangent.sorting.controls.ArrayController;
import com.tangent.sorting.controls.MainController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class Image {
    private static Color[][] imageArray;
    private static int width;
    private static int height;
    private static float xScale;
    private static float yScale;



    private static boolean validFile(String path) {
        int index = path.lastIndexOf(".");
        if (index == -1) return false;
        switch (path.substring(index)) {
            case ".png":
            case ".jpg":
            case ".jpeg":
                return true;
        }
        return false;
    }

   public static void selectImage(String path) throws IOException {
       System.out.println(path);
       if(!validFile(path)) {
           MainController.setErrorCode(MainController.Error.InvalidImage);
           return;
       }
       BufferedImage tempImage = ImageIO.read(new File(path));
       if (tempImage.getWidth() * tempImage.getHeight() > MainController.maxElements) {
           MainController.setErrorCode(MainController.Error.BigImage);
           return;
       }
       BufferedImage image = tempImage;
       width = image.getWidth();
       height = image.getHeight();
       xScale = (float) MainController.width * MainController.widthMultiplier / width;
       yScale = (float) MainController.height / height;

       imageArray = convertImage(image);
       MainController.setTotalElements(width * height);
   }


    private static Color[][] convertImage(BufferedImage image) {
        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Color[][] result = new Color[image.getHeight()][image.getWidth()];
        int x = 0;
        int y = image.getHeight() - 1;
        if (image.getAlphaRaster() == null) {
            for (int pixel = 0; pixel + 2 < pixels.length; pixel += 3) {
                result[x][y] = new Color((pixels[pixel + 2] & 0xff) / 255f, (pixels[pixel + 1] & 0xff) / 255f, (pixels[pixel] & 0xff) / 255f, 1);
                x++;
                if (x == image.getWidth()) {
                    x = 0;
                    y--;
                }
            }
        }
        else {
            for (int pixel = 0; pixel + 3 < pixels.length; pixel += 4) {
                result[x][y] = new Color((pixels[pixel + 3] & 0xff) / 255f, (pixels[pixel + 2] & 0xff) / 255f, (pixels[pixel + 1] & 0xff) / 255f, (pixels[pixel] & 0xff) / 255f);
                x++;
                if (x == image.getWidth()) {
                    x = 0;
                    y--;
                }
            }
        }
        return result;
    }

    public static void renderArray(ArrayController arrayController, ShapeRenderer sr) {
       if (imageArray == null) {
           //MainController.setErrorCode(MainController.Error.NoImage);
           return;
       }

       for (int i = 0; i < arrayController.getLength(); i++) {
           sr.setColor(imageArray[(arrayController.getElement(i) - 1) % (width)][(arrayController.getElement(i) - 1) / width]);
            sr.rect((i % width) * xScale, (i / width) * yScale, xScale, yScale);
       }


    }
}
