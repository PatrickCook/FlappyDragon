package com.petree.GameWorld;

import com.badlogic.gdx.math.Intersector;

import com.badlogic.gdx.math.Rectangle;
import com.petree.GameObjects.Bird;
import com.petree.GameObjects.ScrollHandler;
import com.petree.DHelpers.AssetLoader;

public class GameWorld {
	private GameState currentState;
	public enum GameState {

	    MENU, READY, RUNNING, GAMEOVER, HIGHSCORE,CREDITS

	}


    private Bird bird;
    private ScrollHandler scroller;
    public Rectangle ground;
    private float runTime = 0;
    private int score = 0;
    private int midPointY;
    private boolean sound = true;
    
    public GameWorld(int midPointY) {
    	this.midPointY = midPointY;
    	currentState = GameState.MENU;
        bird = new Bird(170, midPointY-75, 55, 33);
        // The grass should start 66 pixels below the midPointY
        scroller = new ScrollHandler(this,midPointY + 150);
        ground = new Rectangle(0, 640, 400, 160);
    }

    public void update(float delta) {
    	
        switch (currentState) {
        case MENU:
        case READY:
            updateReady(delta);
            break;

        case RUNNING:
        	updateRunning(delta);
        	break;
        default:
            break;
        }

    }

    private void updateReady(float delta) {
    	 bird.updateReady(runTime);
         scroller.updateReady(delta);
         
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scroller.update(delta);

        if (scroller.collides(bird) && bird.isAlive()) {
            scroller.stop();
            bird.die();
            if (sound)
            	AssetLoader.dead.play();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;
            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
    }
    
    public Bird getBird() {
        return bird;
    }

    public int getMidPointY() {
        return midPointY;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void ready() {
        currentState = GameState.READY;
    }
    public void menu() {
        currentState = GameState.MENU;
    }
    public void credits() {
        currentState = GameState.CREDITS;
    }


    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }
    public boolean isCredits() {
        return currentState == GameState.CREDITS;
    }
    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }
    public void soundPlay(){
    	sound = true;
    	scroller.sound = true;
    	AssetLoader.theme.play();
    }
    public void soundStop(){
    	sound = false;
    	scroller.sound = false;
    	AssetLoader.theme.stop();
    }
}