package fr.cdsm.leagueofesportv2.fragment;

import android.app.FragmentTransaction;
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

import fr.cdsm.leagueofesportv2.adapter.MatchAdapter;
import fr.cdsm.leagueofesportv2.interfaces.MatchAdapterListener;
import fr.cdsm.leagueofesportv2.model.Match;
import fr.cdsm.leagueofesportv2.model.News;
import fr.cdsm.leagueofesportv2.R;

public class FragmentAccueil extends android.app.Fragment {

    Match rencontre;
    DatabaseReference newsDatabase = FirebaseDatabase.getInstance().getReference();
    ArrayList<Match> arrayListMatch = new ArrayList<Match>();
    public MaterialDialog material;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_accueil, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_match);

        material = new MaterialDialog.Builder(getActivity())
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0)
                .show();

        getData();
        return rootView;
    }

    private void getData() {
        newsDatabase.child("Accueil").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot match : dataSnapshot.getChildren()) {
                    rencontre = match.getValue(Match.class);
                    arrayListMatch.add(rencontre);
                }

                MatchAdapterListener listener = new MatchAdapterListener() {
                    public void onMatchClick() {
                        fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_main, new FragmentResultat());
                        fragmentTransaction.commit();
                    }
                };

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mAdapter = new MatchAdapter(material, arrayListMatch, listener);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
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
