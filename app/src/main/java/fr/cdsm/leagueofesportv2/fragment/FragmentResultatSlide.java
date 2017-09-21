package fr.cdsm.leagueofesportv2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import fr.cdsm.leagueofesportv2.R;
import fr.cdsm.leagueofesportv2.activity.ResultatActivity;
import fr.cdsm.leagueofesportv2.adapter.MatchAdapter;
import fr.cdsm.leagueofesportv2.interfaces.MatchAdapterListener;
import fr.cdsm.leagueofesportv2.model.Match;

public class FragmentResultatSlide extends Fragment {

    Match rencontre;
    DatabaseReference newsDatabase = FirebaseDatabase.getInstance().getReference();
    ArrayList<Match> arrayListMatch = new ArrayList<Match>();
    public MaterialDialog material;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    public TextView mText;
    int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_fragment_resultat_slide, container, false);
        Bundle args = getArguments();
        position = args.getInt("0");

        getData();
        return rootview;
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
                material = new MaterialDialog.Builder(getActivity())
                        .title("ERROR")
                        .content("ERROR")
                        .progress(true, 0)
                        .show();
            }
        });
    }
}
