package com.tangent.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
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
		viewport = new StretchViewport(1000, 1000, camera);
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

		Controller.logic();

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		viewport.apply();
		sr.setProjectionMatrix(camera.combined);
		sr.begin(ShapeRenderer.ShapeType.Filled);
		for (Button button : Controller.buttonList) {
			button.render(sr);
		}
		for (DropButton dropButton : Controller.dropButtonList) {
			dropButton.render(sr);
		}
		for (Slider slider : Controller.sliderList) {
			slider.render(sr);
		}
		sr.end();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		font.getData().setScale(2);
		for (Button button : Controller.buttonList) {
			button.renderText(batch, font);
		}
		for (DropButton dropButton : Controller.dropButtonList) {
			dropButton.renderText(batch, font);
		}
		for (Slider slider : Controller.sliderList) {
			slider.renderText(batch, font);
		}
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
