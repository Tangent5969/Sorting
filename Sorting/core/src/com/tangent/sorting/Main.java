package com.tangent.sorting;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Main extends ApplicationAdapter {
	private ShapeRenderer sr;
	private SpriteBatch batch;
	private BitmapFont font;
	private Camera camera;
	private StretchViewport viewport;






	@Override
	public void create () {
		Gdx.input.setInputProcessor(new InputManager());

		sr = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();

		camera = new OrthographicCamera();
		viewport = new StretchViewport(Controller.width, Controller.height, camera);
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

		Controller.logic();

		Settings.setButtons();
		Settings.setDropButtons();
		Settings.setSliders();
		Settings.offsetSettings();

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);



		viewport.apply();
		sr.setProjectionMatrix(camera.combined);
		sr.begin(ShapeRenderer.ShapeType.Filled);
		Controller.renderArray(sr);
		Settings.render(sr);
		sr.end();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		font.getData().setScale(10);
		Settings.renderText(batch, font);
		batch.end();

	}
	
	@Override
	public void dispose () {
		sr.dispose();
		batch.dispose();
		font.dispose();
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
		viewport.setScreenPosition(viewport.getScreenX(), viewport.getScreenY());
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}



}
