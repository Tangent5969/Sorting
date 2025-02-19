package com.tangent.sorting.controls;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.tangent.sorting.ui.input.InputManager;
import com.tangent.sorting.ui.visual.Image;

public class Main extends ApplicationAdapter {
    private ShapeRenderer sr;
    private SpriteBatch batch;
    private BitmapFont font;
    private Camera camera;
    private StretchViewport viewport;


    @Override
    public void create() {
        Gdx.graphics.setContinuousRendering(false);
        Gdx.input.setInputProcessor(new InputManager());

        sr = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();

        camera = new OrthographicCamera();
        viewport = new StretchViewport(MainController.width, MainController.height, camera);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        MainController.initialise();
        Settings.initialise();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        viewport.apply();
        sr.setProjectionMatrix(camera.combined);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        MainController.renderArray(sr);
        Settings.render(sr);
        sr.end();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        font.getData().setScale(8);
        Image.renderBlankImage(batch);
        Settings.renderText(batch, font);
        batch.end();
    }

    @Override
    public void dispose() {
        MainController.sortThread.interrupt();
        sr.dispose();
        batch.dispose();
        font.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        viewport.setScreenPosition(viewport.getScreenX(), viewport.getScreenY());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

}
