package fr.cdsm.leagueofesportv2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fr.cdsm.leagueofesportv2.R;
import fr.cdsm.leagueofesportv2.activity.ResultatActivity;
import fr.cdsm.leagueofesportv2.adapter.ResultatAdapter;
import fr.cdsm.leagueofesportv2.interfaces.MatchAdapterListener;
import fr.cdsm.leagueofesportv2.model.Best_Of;
import fr.cdsm.leagueofesportv2.model.Match;

public class FragmentResultatSlide extends Fragment {

    Best_Of rencontre;
    DatabaseReference newsDatabase = FirebaseDatabase.getInstance().getReference();
    ArrayList<Best_Of> arrayListMatch = new ArrayList<Best_Of>();
    public MaterialDialog material;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    int position;
    ImageView image1, image2;
    Match test;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_fragment_resultat_slide, container, false);
        image1 = (ImageView) rootview.findViewById(R.id.img_result_team1);
        image2 = (ImageView) rootview.findViewById(R.id.img_result_team2);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.rv_result);

        Bundle args = getArguments();
        position = args.getInt("0");

        material = new MaterialDialog.Builder(getActivity())
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0)
                .show();

        getData();
        return rootview;
    }

    private void getData() {
        newsDatabase.child("Rencontre").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot match : dataSnapshot.getChildren()) {
                    rencontre = match.getValue(Best_Of.class);
                    arrayListMatch.add(rencontre);
                }

                MatchAdapterListener listener = new MatchAdapterListener() {
                    public void onMatchClick() {
                        Intent intent = new Intent(getActivity(), ResultatActivity.class);
                        intent.putExtra("size", arrayListMatch.size());
                        startActivity(intent);
                    }
                };

                Picasso.with(getActivity()).load(arrayListMatch.get(position).image_team1).into(image1);
                Picasso.with(getActivity()).load(arrayListMatch.get(position).image_team2).into(image2);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mAdapter = new ResultatAdapter(material, arrayListMatch, listener);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }



}
