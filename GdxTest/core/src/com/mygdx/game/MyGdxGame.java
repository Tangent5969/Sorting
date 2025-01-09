package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	BufferedImage image;
	Color[][] array;
	ShapeRenderer sr;


	@Override
	public void create() {
		sr = new ShapeRenderer();
		batch = new SpriteBatch();
		try {
			image = ImageIO.read(new File("assets/badlogicAlpha.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		array = convertImage(image);
		for (Color[] a : array) {
			System.out.println(Arrays.toString(a));
		}

	}


	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1);
		sr.begin(ShapeRenderer.ShapeType.Filled);

		int pixelSize = 1;
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				sr.setColor(array[x][y]);
				sr.rect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
			}
		}

		sr.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		sr.dispose();
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
}


