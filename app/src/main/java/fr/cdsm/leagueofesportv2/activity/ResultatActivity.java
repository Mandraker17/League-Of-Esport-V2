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
import fr.cdsm.leagueofesportv2.model.Match;

public class ResultatActivity extends AppCompatActivity {

    Match rencontre;
    DatabaseReference newsDatabase = FirebaseDatabase.getInstance().getReference();
    ArrayList<Match> arrayListMatch = new ArrayList<Match>();

    private PagerAdapter mPagerAdapter;
    private ViewPager mPager;
    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        Intent intent = getIntent();
        temp = intent.getIntExtra("size",1);
        getData();

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    private void getData() {
        newsDatabase.child("Rencontre").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot match : dataSnapshot.getChildren()) {
                    rencontre = match.getValue(Match.class);
                    arrayListMatch.add(rencontre);
                }
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
            args.putInt("0",position);
            FragmentResultatSlide fragment = new FragmentResultatSlide();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return temp;
        }
    }
}
