package com.petree.DragonWings;

import com.badlogic.gdx.Game;
import com.petree.Screens.SplashScreen;
import com.petree.DHelpers.AssetLoader;

public class DGame extends Game {

    @Override
    public void create() {
    	AssetLoader.assign();
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

}