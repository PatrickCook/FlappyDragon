package com.petree.DragonWings;



import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;



import com.badlogic.gdx.backends.android.AndroidApplication;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import com.google.android.gms.ads.*;



public class MainActivity extends AndroidApplication {

 
    AdView adView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        RelativeLayout layout = new RelativeLayout(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        View gameView = initializeForView(new DGame(), cfg); 

        AdView AdView = new AdView(this);
        AdView.setAdSize(AdSize.SMART_BANNER);
        AdView.setAdUnitId("ca-app-pub-8658819654230701/7873804610");
        AdRequest.Builder adRequest = new AdRequest.Builder();
        //adRequest.addTestDevice("41EAF0AEAAF1F4F696F648D06DE4989B"); //Jacobs Phone
        AdView.loadAd(adRequest.build());
        layout.addView(gameView);

        RelativeLayout.LayoutParams adParams = 
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        layout.addView(AdView, adParams);

        setContentView(layout);

                

    }
}