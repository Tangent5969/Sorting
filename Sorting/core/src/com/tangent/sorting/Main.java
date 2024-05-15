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
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Main extends ApplicationAdapter {
	private ShapeRenderer sr;
	private Camera camera;
	private StretchViewport viewport;

	Controller controller;





	@Override
	public void create () {
		controller = new Controller();
		sr = new ShapeRenderer();

		camera = new OrthographicCamera();
		viewport = new StretchViewport(Controller.graphWidth, Controller.graphHeight, camera);
		viewport.apply();
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

		controller.logic();
		System.out.println(Controller.mainArray[1]);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);


		sr.setProjectionMatrix(camera.combined);
		sr.begin(ShapeRenderer.ShapeType.Filled);
		controller.renderArray(sr);
		sr.end();

	}
	
	@Override
	public void dispose () {
		sr.dispose();
	}

	@Override
	public void resize(int width, int height){
		viewport.update((int) (width * 0.8), height);
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);


	}
}
