package com.tangent.sorting;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main extends ApplicationAdapter {
	ShapeRenderer sr;
	Camera mainCamera;
	Camera settingsCamera;
	ScreenViewport mainViewport;
	ScreenViewport settingsViewport;

	@Override
	public void create () {
		sr = new ShapeRenderer();

		mainCamera = new OrthographicCamera(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight());
		mainViewport = new ScreenViewport(mainCamera);
		mainCamera.position.set(mainCamera.viewportWidth/2,mainCamera.viewportHeight/2,0);

		settingsCamera = new OrthographicCamera(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight());
		settingsViewport = new ScreenViewport(settingsCamera);
		settingsCamera.position.set(mainCamera.viewportWidth,settingsCamera.viewportHeight/2,0);

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		mainCamera.update();
		settingsCamera.update();

		sr.setProjectionMatrix(mainCamera.combined);
		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.rect(0, 0, mainCamera.viewportWidth, mainCamera.viewportHeight);
		sr.end();

	}
	
	@Override
	public void dispose () {
		sr.dispose();
	}

	@Override
	public void resize(int width, int height){
		mainViewport.update((int) (width * 0.8),height);
		mainCamera.position.set(mainCamera.viewportWidth/2,mainCamera.viewportHeight/2,0);

		settingsViewport.update((int) (width * 0.2), height);
		settingsCamera.position.set(mainCamera.viewportWidth,settingsCamera.viewportHeight/2,0);

	}
}
