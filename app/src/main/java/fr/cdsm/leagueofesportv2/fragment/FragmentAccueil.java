package fr.cdsm.leagueofesportv2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fr.cdsm.leagueofesportv2.activity.ResultatActivity;
import fr.cdsm.leagueofesportv2.adapter.ClassementAdapter;
import fr.cdsm.leagueofesportv2.adapter.MatchAdapter;
import fr.cdsm.leagueofesportv2.interfaces.MatchAdapterListener;
import fr.cdsm.leagueofesportv2.interfaces.RecyclerViewClickListener;
import fr.cdsm.leagueofesportv2.model.Best_Of;
import fr.cdsm.leagueofesportv2.model.Classement;
import fr.cdsm.leagueofesportv2.R;

public class FragmentAccueil extends android.app.Fragment {

    Best_Of rencontre;
    DatabaseReference newsDatabase = FirebaseDatabase.getInstance().getReference();
    ArrayList<Best_Of> arrayListMatch = new ArrayList<Best_Of>();
    ArrayList<Classement> arrayListClassement = new ArrayList<Classement>();
    public MaterialDialog material;
    RecyclerView mRecyclerViewMatch;
    RecyclerView mRecyclerViewRank;
    RecyclerView.Adapter mAdapter;
    Classement classement;
    int count_pagers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_accueil, container, false);
        mRecyclerViewMatch = (RecyclerView) rootView.findViewById(R.id.rv_match);
        mRecyclerViewRank = (RecyclerView) rootView.findViewById(R.id.rv_rank);

        material = new MaterialDialog.Builder(getActivity())
                .title("Chargement des données")
                .content("Veuillez Attendre")
                .progress(true, 0)
                .show();

        getData();
        getDataClassement();
        return rootView;
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
                    @Override
                    public void onMatchClick(int position) {
                        count_pagers = (Integer.valueOf(arrayListMatch.get(position).score_final_team1) + Integer.valueOf(arrayListMatch.get(position).score_final_team2));
                        Intent intent = new Intent(getActivity(), ResultatActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("count_pagers", count_pagers);
                        startActivity(intent);
                    }
                };

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerViewMatch.setLayoutManager(linearLayoutManager);
                mAdapter = new MatchAdapter(material, arrayListMatch, listener);
                mRecyclerViewMatch.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                material = new MaterialDialog.Builder(getActivity())
                        .title("ERROR")
                        .content("Match")
                        .progress(true, 0)
                        .show();
            }
        });
    }

    private void getDataClassement() {
        newsDatabase.child("Classement").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot match : dataSnapshot.getChildren()) {
                    classement = match.getValue(Classement.class);
                    arrayListClassement.add(classement);
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerViewRank.setLayoutManager(linearLayoutManager);
                mAdapter = new ClassementAdapter(arrayListClassement);
                mRecyclerViewRank.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                material = new MaterialDialog.Builder(getActivity())
                        .title("ERROR")
                        .content("Classement")
                        .progress(true, 0)
                        .show();
            }
        });
    }

}
