package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer sr;
	
	@Override
	public void create () {
		sr = new ShapeRenderer();
	}

	@Override
	public void render () {
		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.rect(50, 100, 200, 200);
		sr.end();
	}
	
	@Override
	public void dispose () {
	sr.dispose();
	}
}

