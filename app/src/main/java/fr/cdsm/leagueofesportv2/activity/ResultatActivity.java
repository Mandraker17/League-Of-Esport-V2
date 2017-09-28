package fr.cdsm.leagueofesportv2.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fr.cdsm.leagueofesportv2.R;
import fr.cdsm.leagueofesportv2.fragment.FragmentResultatSlide;
import fr.cdsm.leagueofesportv2.model.Best_Of;

public class ResultatActivity extends AppCompatActivity {

    Best_Of rencontre;
    DatabaseReference newsDatabase = FirebaseDatabase.getInstance().getReference();
    ArrayList<Best_Of> arrayListMatch = new ArrayList<Best_Of>();
    private PagerAdapter mPagerAdapter;
    private ViewPager mPager;
    int temp_position;
    int test = 0;
    public int nombre_pages;
    String test1, test2;
    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        Intent intent = getIntent();
        temp_position = intent.getIntExtra("position", 0);
        nombre_pages = intent.getIntExtra("count_pagers", 1);

        getData();

    }

    private void getData() {
        newsDatabase.child("Rencontre").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot match : dataSnapshot.getChildren()) {
                    test = Integer.valueOf(match.getKey());
                    if (test == temp_position) {
                        rencontre = match.getValue(Best_Of.class);
                        arrayListMatch.add(rencontre);
                    }
                }
                test1 = arrayListMatch.get(0).image_team1;
                test2 = arrayListMatch.get(0).image_team2;
                mPager = (ViewPager) findViewById(R.id.pager);
                mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
                mPager.setAdapter(mPagerAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            args.putInt("0", position);
            args.putInt("1", temp_position);
            args.putString("test1",test1);
            args.putString("test2",test2);
            FragmentResultatSlide fragment = new FragmentResultatSlide();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return nombre_pages;
        }
    }
}
