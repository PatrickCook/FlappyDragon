package com.petree.DHelpers;

import com.badlogic.gdx.Gdx;


import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture texture,texture2,texture3, backgrounds,dirtTexture, dirtTexture2,dirtTexture3,titleTexture,splashTexture;
    
    public static TextureRegion bg,dirt,score;
    public static TextureRegion bg2,dirt2,score2;
    public static TextureRegion bg3,dirt3,score3;
    public static TextureRegion splash,title,credits; 
    public static TextureRegion playButtonUp, playButtonDown,rightButton,leftButton, menuButton, restartButton,creditButton,soundButtonOn,soundButtonOff;


    public static Animation birdAnimation, birdAnimation2,birdAnimation3;
    public static TextureRegion bird, birdDown, birdUp;
    public static TextureRegion bird2, birdDown2, birdUp2;
    public static TextureRegion bird3, birdDown3, birdUp3;

    public static TextureRegion pipeUp, pipeDown;
    public static TextureRegion pipeUp2, pipeDown2;
    public static TextureRegion pipeUp3, pipeDown3;
    
    public static Sound dead,flap,coin;
    public static Music theme;
    
    public static BitmapFont font, shadow;
    public static Preferences prefs;
    public static void assign(){
    	texture = new Texture(Gdx.files.internal("textures/texture.png"));
    	texture2 = new Texture(Gdx.files.internal("textures/texture2.png"));
    	texture3 = new Texture(Gdx.files.internal("textures/texture3.png"));
    	backgrounds = new Texture(Gdx.files.internal("textures/background.jpg"));
    	dirtTexture = new Texture(Gdx.files.internal("textures/dirt.png"));
    	dirtTexture2 = new Texture(Gdx.files.internal("textures/dirt2.png"));
    	dirtTexture3 = new Texture(Gdx.files.internal("textures/dirt3.png"));
    	titleTexture = new Texture(Gdx.files.internal("textures/title.png"));
    	splashTexture = new Texture(Gdx.files.internal("textures/splash.jpg"));
    	load();
    }
    

    public static void load() {
    	prefs = Gdx.app.getPreferences("DragonWings");
    	if (!prefs.contains("highScore")) {
    	    prefs.putInteger("highScore", 0);
    	}
    	
    	//Loading World 1 Textures
    	credits = new TextureRegion(texture,0, 270, 250, 225);
    	credits.flip(false, true);
    	
    	bg = new TextureRegion(backgrounds,0, 0, 480, 760);
        bg.flip(false, true);
         
        playButtonUp = new TextureRegion(texture, 0, 200, 85, 35);
        playButtonUp.flip(false, true);
        
        playButtonDown = new TextureRegion(texture, 85, 200, 85, 35);
        playButtonDown.flip(false, true);
        
        rightButton = new TextureRegion(texture, 315, 0, 35, 320);
        rightButton.flip(false, true);
        
        creditButton = new TextureRegion(texture, 385, 0, 85, 35);
        creditButton.flip(false, true);
        
        soundButtonOn = new TextureRegion(texture, 385, 35, 40, 40);
        soundButtonOn.flip(false, true);
        
        soundButtonOff = new TextureRegion(texture, 425, 35, 40, 40);
        soundButtonOff.flip(false, true);
        
        leftButton = new TextureRegion(texture, 350, 0, 35, 320);
        leftButton.flip(false, true);
       
        menuButton = new TextureRegion(texture, 171, 200, 79, 35);
        menuButton.flip(false, true);
        
        restartButton = new TextureRegion(texture, 171, 235, 79, 35);
        restartButton.flip(false, true);
        
        score= new TextureRegion(texture, 0, 0, 250, 200);
        score.flip(false, true);
          
        birdDown = new TextureRegion(texture, 0, 235, 55, 35);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 55, 235, 55, 35);
        bird.flip(false, true);

        birdUp = new TextureRegion(texture, 110, 235, 55, 35);
        birdUp.flip(false, true);
        
        pipeUp = new TextureRegion(texture, 250, 0, 65, 400);
        pipeDown = new TextureRegion(pipeUp);
        pipeDown.flip(false, true);
        
        dirt = new TextureRegion(dirtTexture, 0, 0, 660, 180);
        dirt.flip(false, true);
        
        TextureRegion[] birds = { birdDown, bird, birdUp };
        birdAnimation = new Animation(0.1f, birds);
        birdAnimation.setPlayMode(Animation.LOOP_PINGPONG);
        
        //Loading World 2 Textures
        bg2 = new TextureRegion(backgrounds,480, 0, 480, 760);
        bg2.flip(false, true);
        
        score2 = new TextureRegion(texture2, 0, 0, 250, 200);
        score2.flip(false, true);
        
        birdDown2 = new TextureRegion(texture2, 0, 235, 55, 35);
        birdDown2.flip(false, true);

        bird2 = new TextureRegion(texture2, 55, 235, 55, 35);
        bird2.flip(false, true);

        birdUp2 = new TextureRegion(texture2, 110, 235, 55, 35);
        birdUp2.flip(false, true);
        
        pipeUp2 = new TextureRegion(texture2, 250, 0, 65, 400);
        pipeDown2 = new TextureRegion(pipeUp2);
        pipeDown2.flip(false, true);
        
        dirt2 = new TextureRegion(dirtTexture2, 0, 0, 660, 180);
        dirt2.flip(false, true);
        
        TextureRegion[] birds2 = { birdDown2, bird2, birdUp2 };
        birdAnimation2 = new Animation(0.1f, birds2);
        birdAnimation2.setPlayMode(Animation.LOOP_PINGPONG);
        
        //Textures for World 3
        bg3 = new TextureRegion(backgrounds,960, 0, 480, 760);
        bg3.flip(false, true);
        
        score3 = new TextureRegion(texture3, 0, 0, 250, 200);
        score3.flip(false, true);
        
        birdDown3 = new TextureRegion(texture3, 0, 235, 55, 35);
        birdDown3.flip(false, true);

        bird3 = new TextureRegion(texture3, 55, 235, 55, 35);
        bird3.flip(false, true);

        
        birdUp3= new TextureRegion(texture3, 110, 235, 55, 35);
        birdUp3.flip(false, true);
        
        pipeUp3 = new TextureRegion(texture3, 250, 0, 65, 400);
        pipeDown3 = new TextureRegion(pipeUp3);
        pipeDown3.flip(false, true);
        
        dirt3 = new TextureRegion(dirtTexture3, 0, 0, 660, 180);
        dirt3.flip(false, true);
        
        TextureRegion[] birds3 = { birdDown3, bird3, birdUp3 };
        birdAnimation3 = new Animation(0.1f, birds3);
        birdAnimation3.setPlayMode(Animation.LOOP_PINGPONG);
        
        //Loading Title
        title = new TextureRegion(titleTexture, 0, 0, 380, 100);
        title.flip(false, true);
           
        //Loading Splash
        splash = new TextureRegion(splashTexture, 0, 0, 480, 800);
          
        
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(.5f, -.5f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(.5f, -.5f);
        
        theme = Gdx.audio.newMusic(Gdx.files.internal("data/theme.wav"));
        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
        backgrounds.dispose();
        splashTexture.dispose();
        dirtTexture.dispose();
        titleTexture.dispose();

        // Dispose sounds
        dead.dispose();
        flap.dispose();
        coin.dispose();

        font.dispose();
        shadow.dispose();
    }
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    // Retrieves the current high score
    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

}