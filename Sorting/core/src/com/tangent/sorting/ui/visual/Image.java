package com.tangent.sorting.ui.visual;

import com.badlogic.gdx.graphics.Color;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Image {


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
}
