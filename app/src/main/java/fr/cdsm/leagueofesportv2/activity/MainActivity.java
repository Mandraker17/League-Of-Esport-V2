package fr.cdsm.leagueofesportv2.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.cdsm.leagueofesportv2.R;
import gr.net.maroulis.library.EasySplashScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(MainActivity.this)
                .withFullScreen()
                .withTargetActivity(MatchFilActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundResource(R.color.back_splash)
                .withLogo(R.drawable.icon);

        View easySplashScreenView = config.create();
        setContentView(easySplashScreenView);

    }
}
