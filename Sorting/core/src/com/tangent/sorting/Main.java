package com.tangent.sorting;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main extends ApplicationAdapter {

	OrthographicCamera mainCamera;
	OrthographicCamera settingsCamera;
	ScreenViewport mainViewport;
	ScreenViewport settingsViewport;
	ShapeRenderer sr;
	
	@Override
	public void create () {
		mainCamera = new OrthographicCamera(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight());
		settingsCamera = new OrthographicCamera(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight());

		mainViewport = new ScreenViewport(mainCamera);
		mainViewport.setScreenBounds(0, 0, (int) (Gdx.graphics.getWidth() * 0.8), Gdx.graphics.getHeight());

		settingsViewport = new ScreenViewport(settingsCamera);
		settingsViewport.setScreenBounds((int) (Gdx.graphics.getWidth() * 0.8), 0, (int) (Gdx.graphics.getWidth() * 0.2), Gdx.graphics.getHeight());

		mainCamera.translate(mainCamera.viewportWidth/2, mainCamera.viewportHeight/2);
		mainCamera.update();
		mainViewport.apply();
		settingsViewport.apply();

		sr = new ShapeRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 0);
		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setProjectionMatrix(mainCamera.combined);
		sr.rect(0, 0, mainCamera.viewportWidth, mainCamera.viewportHeight);
		sr.end();
	}

	@Override
	public void dispose () {
		sr.dispose();
	}
}
