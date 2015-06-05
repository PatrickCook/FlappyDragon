package com.petree.Screens;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.petree.GameWorld.GameRenderer;
import com.petree.GameWorld.GameWorld;
import com.petree.DHelpers.AssetLoader;
import com.petree.DHelpers.InputHandler;

public class GameScreen implements Screen {

	private GameWorld world;
	public GameRenderer renderer;
	private float runTime;

	// This is the constructor, not the class declaration
	public GameScreen() {
		 float screenWidth = Gdx.graphics.getWidth();
	     float screenHeight = Gdx.graphics.getHeight();
	     float gameWidth = 480;
	     float scale = screenWidth/gameWidth;
	     float gameHeight = 800;
	     int midPointY = (int) (gameHeight / 2);
	     AssetLoader.theme.setLooping(true);
	     AssetLoader.theme.play();
	     world = new GameWorld(midPointY); 
	     Gdx.input.setInputProcessor(new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight));	   
	     renderer = new GameRenderer(world, (int) gameHeight, midPointY);
	     ((InputHandler) Gdx.input.getInputProcessor()).setRenderer(renderer);
	}
	@Override
	public void render(float delta) {

        runTime += delta;
        world.update(delta);
        renderer.render(delta);
    }

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
