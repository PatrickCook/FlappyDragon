package com.petree.DHelpers;

import java.util.ArrayList;

import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.petree.GameObjects.Bird;
import com.petree.GameWorld.GameRenderer;
import com.petree.GameWorld.GameWorld;

import com.petree.ui.SimpleButton;

public class InputHandler implements InputProcessor {
    private Bird myBird;
    private GameWorld myWorld;
    private GameRenderer renderer;

    private List<SimpleButton> menuButtons,restartButtons;

    private SimpleButton playButton,rightButton,leftButton, menuButton,restartButton,creditButton,soundButton;

    private float scaleFactorX;
    private float scaleFactorY;

    public InputHandler(GameWorld myWorld, float scaleFactorX, float scaleFactorY) {
        this.myWorld = myWorld;
        myBird = myWorld.getBird();


        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        menuButtons = new ArrayList<SimpleButton>();
        restartButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(187, 400, 85, 35, AssetLoader.playButtonUp,AssetLoader.playButtonDown);
        rightButton = new SimpleButton(445, 240, 35, 320, AssetLoader.rightButton,AssetLoader.rightButton);
        leftButton = new SimpleButton(0, 240, 35, 320, AssetLoader.leftButton,AssetLoader.leftButton);
        menuButton = new SimpleButton(190, 500, 79, 35, AssetLoader.menuButton,AssetLoader.menuButton);
        restartButton = new SimpleButton(190, 560, 79, 35, AssetLoader.restartButton,AssetLoader.restartButton);
        creditButton = new SimpleButton(190, 560, 79, 35, AssetLoader.creditButton,AssetLoader.creditButton);
        soundButton = new SimpleButton(400, 40, 40, 40, AssetLoader.soundButtonOn,AssetLoader.soundButtonOff);
        menuButtons.add(playButton);
        menuButtons.add(rightButton);
        menuButtons.add(leftButton);
        menuButtons.add(creditButton);
        menuButtons.add(soundButton);
        restartButtons.add(menuButton);
        restartButtons.add(restartButton);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        if (myWorld.isMenu()) {
            playButton.isTouchDown(screenX, screenY);
            rightButton.isTouchDown(screenX, screenY);
            leftButton.isTouchDown(screenX, screenY);
            creditButton.isTouchDown(screenX, screenY);
            soundButton.isTouchDown(screenX, screenY);
        } else if (myWorld.isReady()) {
            myWorld.start();
        }else if (myWorld.isCredits()){
        	menuButton.isTouchDown(screenX, screenY);
        } else{ 
        	menuButton.isTouchDown(screenX, screenY);
        	restartButton.isTouchDown(screenX, screenY);
        }

        myBird.onClick();

        

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        if (myWorld.isMenu()) {
            if (playButton.isTouchUp(screenX, screenY)) {
                myWorld.ready();
                return true;
            }
            if (soundButton.isTouchUp(screenX, screenY)) {
            	if(soundButton.sound == true){
            		myWorld.soundPlay();
            		myBird.soundPlay();
            	}
            	else{
            		myWorld.soundStop();
            		myBird.soundStop();
            	}
                return true;
            }
            if (rightButton.isTouchUp(screenX, screenY)) {
                renderer.changeMap(true);
                return true;
            }
            if (leftButton.isTouchUp(screenX, screenY)) {
                renderer.changeMap(false);
                return true;
            }
            if (creditButton.isTouchUp(screenX, screenY)) {
                myWorld.credits();
                return true;
            }
            
            
        } else if (myWorld.isGameOver() || myWorld.isHighScore()||myWorld.isCredits()) {
        	if (menuButton.isTouchUp(screenX, screenY)) {
        		myWorld.restart();
                myWorld.menu();
                return true;
            }
            if (restartButton.isTouchUp(screenX, screenY)) {
            	myWorld.restart(); 
            	return true;
            }
            		
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {

        // Can now use Space Bar to play the game
        if (keycode == Keys.SPACE) {

            if (myWorld.isMenu()) {
                myWorld.ready();
            } else if (myWorld.isReady()) {
                myWorld.start();
            }

            myBird.onClick();

            if (myWorld.isGameOver() || myWorld.isHighScore()) {
                myWorld.restart();
            }

        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }
    public List<SimpleButton> getRestartButtons() {
        return restartButtons;
    }
    public void setRenderer(GameRenderer r){
    	renderer = r;
    }
}