package com.petree.GameWorld;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.petree.GameObjects.Bird;
import com.petree.GameObjects.Grass;
import com.petree.GameObjects.Pipe;
import com.petree.GameObjects.ScrollHandler;
import com.petree.TweenAccessors.Value;
import com.petree.TweenAccessors.ValueAccessor;
import com.petree.DHelpers.AssetLoader;
import com.petree.DHelpers.InputHandler;
import com.petree.ui.SimpleButton;

public class GameRenderer {
	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	public int midPointY,level = 1;
	

	// Game Objects
	private Bird bird;
	private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	private Pipe pipe1, pipe2, pipe3;

	// Game Assets
	private TextureRegion bg, grass, currentBird, birdMid, pipeUp, pipeDown,
			score, dirt, credits;
	private Animation birdAnimation;

	// Tween stuff
	private TweenManager manager;
	private Value alpha = new Value();

	// Buttons
	private List<SimpleButton> menuButtons,restartButtons;

	float stateTime = 0f;

	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;

		this.midPointY = midPointY;
		this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor()).getMenuButtons();
		this.restartButtons = ((InputHandler) Gdx.input.getInputProcessor()).getRestartButtons();

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 480, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		AssetLoader.font.setScale(.5f, -.5f);
		AssetLoader.shadow.setScale(.5f, -.5f);
		initGameObjects();
		initAssets();
		setupTweens();

	}

	private void setupTweens() {
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, .5f).target(0).ease(TweenEquations.easeOutQuad)
				.start(manager);
	}

	private void initGameObjects() {
		bird = myWorld.getBird();
		scroller = myWorld.getScroller();
		frontGrass = scroller.getFrontGrass();
		backGrass = scroller.getBackGrass();
		pipe1 = scroller.getPipe1();
		pipe2 = scroller.getPipe2();
		pipe3 = scroller.getPipe3();
	}

	private void initAssets() {
		credits = AssetLoader.credits;
		if (level == 1) {
			bg = AssetLoader.bg;
			dirt = AssetLoader.dirt;
			score = AssetLoader.score;
			birdAnimation = AssetLoader.birdAnimation;
			birdMid = AssetLoader.bird;
			pipeUp = AssetLoader.pipeUp;
			pipeDown = AssetLoader.pipeDown;
		}
		else if (level == 2) {
			bg = AssetLoader.bg2;
			dirt = AssetLoader.dirt2;
			score = AssetLoader.score2;
			birdAnimation = AssetLoader.birdAnimation2;
			birdMid = AssetLoader.bird2;
			pipeUp = AssetLoader.pipeUp2;
			pipeDown = AssetLoader.pipeDown2;
		}
		else if (level == 3) {
			bg = AssetLoader.bg3;
			dirt = AssetLoader.dirt3;
			score = AssetLoader.score3;
			birdAnimation = AssetLoader.birdAnimation3;
			birdMid = AssetLoader.bird3;
			pipeUp = AssetLoader.pipeUp3;
			pipeDown = AssetLoader.pipeDown3;
		}
	}

	private void drawGrass() {
		// Draw the grass
		batcher.draw(dirt, backGrass.getX() + 20, backGrass.getY(),
				backGrass.getWidth(), 180);
		batcher.draw(dirt, frontGrass.getX() + 20, frontGrass.getY(),
				frontGrass.getWidth(), 180);

	}

	private void drawBirdCentered(float runTime) {
		stateTime += Gdx.graphics.getDeltaTime(); // #15
		currentBird = birdAnimation.getKeyFrame(stateTime, true);
		batcher.draw(currentBird, bird.getX(), bird.getY(),
				bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
				bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
	}

	private void drawPipes() {

		batcher.draw(pipeUp, pipe1.getX(), pipe1.getY() - pipe1.rand(), 65, 400);
		batcher.draw(pipeDown, pipe1.getX(), pipe1.getY() - pipe1.rand() + 525,
				65, 600);

		batcher.draw(pipeUp, pipe2.getX(), pipe2.getY() - pipe2.rand(), 65, 400);
		batcher.draw(pipeDown, pipe2.getX(), pipe1.getY() - pipe2.rand() + 525,
				65, 600);

		batcher.draw(pipeUp, pipe3.getX(), pipe1.getY() - pipe3.rand(), 65, 400);
		batcher.draw(pipeDown, pipe3.getX(), pipe1.getY() - pipe3.rand() + 525,
				65, 600);

	}

	private void drawBird(float runTime) {
		stateTime += Gdx.graphics.getDeltaTime(); // #15
		currentBird = birdAnimation.getKeyFrame(stateTime, true);
		if (bird.shouldntFlap()) {
			batcher.draw(birdMid, bird.getX(), bird.getY(),
					bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
					bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
		} else {
			batcher.draw(currentBird, bird.getX(), bird.getY(),
					bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
					bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());

		}
	}

	private void drawMenuUI() {
		batcher.draw(AssetLoader.title, 60, 120, 380, 100);

		for (SimpleButton button : menuButtons) {
			button.draw(batcher);
		}

	}

	private void drawScore() {
		AssetLoader.font.draw(batcher, "" + myWorld.getScore(), 220, 40);
	}

	private void drawEndScore() {
		AssetLoader.font.draw(batcher, "" + myWorld.getScore(), 290, 230);

		AssetLoader.font.draw(batcher, "" + AssetLoader.getHighScore(), 290,
				280);
	}

	private void drawRetry() {
		for (SimpleButton button : restartButtons) {
			button.draw(batcher);
		}
	}

	private void drawReady() {
		AssetLoader.font.draw(batcher, "Touch", 180, 100);
	}

	private void drawGameOver() {
		AssetLoader.font.draw(batcher, "Game Over", 145, 180);
		AssetLoader.font.draw(batcher, "Score: ", 145, 230);
		AssetLoader.font.draw(batcher, "Best: ", 145, 280);
	}
	private void drawCredits(){
		batcher.draw(credits, 115, 250, 250, 225);
		restartButtons.get(0).draw(batcher);
	}

	public void render(float runTime) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		batcher.disableBlending();
		batcher.draw(bg, 0, 0, 480, 760);

		drawPipes();
		

		batcher.enableBlending();
		drawGrass();
		if (myWorld.isRunning()) {
			drawBird(runTime);
			drawScore();
		} else if (myWorld.isReady()) {
			drawReady();
			drawBird(runTime);
			drawScore();
		} else if (myWorld.isMenu()) {
			drawBirdCentered(runTime);
			drawMenuUI();
		} else if (myWorld.isGameOver()) {
			batcher.draw(score, 115, 150, 250, 200);
			drawGameOver();
			drawRetry();
			drawEndScore();
			drawBird(runTime);
			drawScore();
		} else if (myWorld.isHighScore()) {
			batcher.draw(score, 115, 150, 250, 200);
			drawGameOver();
			drawRetry();
			drawEndScore();
			drawBird(runTime);
			drawScore();
		} else if (myWorld.isCredits()) {
			drawBirdCentered(runTime);
			drawCredits();
		}

		batcher.end();

	}
	public void changeMap(boolean lvl){
		//change level
		if(lvl) level++;
		else level--;
		//level bounds
		if(level <1)
			level = 1;
		if(level >3) 
			level = 1;
		//changing ground
		if(level == 1)
			myWorld.ground = new Rectangle(0, 640, 400, 160);
		else if(level == 2)
			myWorld.ground = new Rectangle(0, 900, 400, 160);
		else if(level == 3)
			myWorld.ground = new Rectangle(0, 640, 400, 160);
		initAssets();
	}
	private void drawTransition(float delta) {
		if (alpha.getValue() > 0) {
			manager.update(delta);
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(1, 1, 1, alpha.getValue());
			shapeRenderer.rect(0, 0, 480, 800);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL10.GL_BLEND);

		}
	}

}
