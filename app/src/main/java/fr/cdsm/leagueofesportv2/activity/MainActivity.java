package fr.cdsm.leagueofesportv2.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import fr.cdsm.leagueofesportv2.R;
import fr.cdsm.leagueofesportv2.fragment.FragmentAccueil;
import fr.cdsm.leagueofesportv2.fragment.FragmentListe;
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

        //finally create the view
        View easySplashScreenView = config.create();
        setContentView(easySplashScreenView);

    }
}
