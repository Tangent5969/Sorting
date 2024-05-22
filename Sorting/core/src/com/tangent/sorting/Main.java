package com.tangent.sorting;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Main extends ApplicationAdapter {
	private ShapeRenderer sr;
	private Camera camera;
	private StretchViewport viewport;

	private Camera settingsCamera;
	private ExtendViewport settingsViewport;





	@Override
	public void create () {
		sr = new ShapeRenderer();

		camera = new OrthographicCamera();
		viewport = new StretchViewport(Controller.graphWidth, Controller.graphHeight, camera);
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

		settingsCamera = new OrthographicCamera();
		settingsViewport = new ExtendViewport(Settings.settingsWidth, Settings.settingsHeight, settingsCamera);
		settingsCamera.position.set(settingsCamera.viewportWidth/2,settingsCamera.viewportHeight/2,0);


		Controller.logic();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		viewport.apply();
		sr.setProjectionMatrix(camera.combined);
		sr.begin(ShapeRenderer.ShapeType.Filled);
		Controller.renderArray(sr);

		settingsViewport.apply();
		sr.setProjectionMatrix(settingsCamera.combined);
		Settings.render(sr);
		sr.rect(0, 0, 200, 200);


		sr.end();


	}
	
	@Override
	public void dispose () {
		sr.dispose();
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
		viewport.setScreenPosition(viewport.getScreenX(), viewport.getScreenY());
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

		settingsViewport.update(width, height);
		settingsViewport.setScreenPosition(settingsViewport.getScreenX(), settingsViewport.getScreenY());
		settingsCamera.position.set(settingsCamera.viewportWidth/2,settingsCamera.viewportHeight/2,0);


	}
}
