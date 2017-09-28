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
import fr.cdsm.leagueofesportv2.model.Joueur;
import fr.cdsm.leagueofesportv2.model.Match;

public class FragmentResultatSlide extends Fragment {

    Match match_test;
    ArrayList<Match> data_match_test = new ArrayList<Match>();
    DatabaseReference newsDatabase = FirebaseDatabase.getInstance().getReference();
    public MaterialDialog material;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    int position, temp_position;
    ImageView image1, image2;
    String url_image1, url_image2;
    Joueur player;
    ArrayList<Joueur> data_array_player1 = new ArrayList<Joueur>();
    ArrayList<Joueur> data_array_player2 = new ArrayList<Joueur>();
    String test_key;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_fragment_resultat_slide, container, false);
        image1 = (ImageView) rootview.findViewById(R.id.img_result_team1);
        image2 = (ImageView) rootview.findViewById(R.id.img_result_team2);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.rv_result);

        Bundle args = getArguments();
        position = args.getInt("0");
        temp_position = args.getInt("1");
        url_image1 = args.getString("test1");
        url_image2 = args.getString("test2");

        material = new MaterialDialog.Builder(getActivity())
                .title(R.string.progress_dialog)
                .content("RecyclerView")
                .progress(true, 0)
                .show();

        getData();
        return rootview;
    }

    private void getData() {
        newsDatabase.child("Rencontre").child(String.valueOf(temp_position)).child("data_match").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    match_test = data.getValue(Match.class);
                    data_match_test.add(match_test);
                }
                Picasso.with(getActivity()).load(url_image1).into(image1);
                Picasso.with(getActivity()).load(url_image2).into(image2);

                newsDatabase.child("Rencontre").child(String.valueOf(temp_position)).child("data_match").child(String.valueOf(position)).child("team1").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                                player = data.getValue(Joueur.class);
                                data_array_player1.add(player);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                newsDatabase.child("Rencontre").child(String.valueOf(temp_position)).child("data_match").child(String.valueOf(position)).child("team2").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            player = data.getValue(Joueur.class);
                            data_array_player2.add(player);
                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(linearLayoutManager);
                        mAdapter = new ResultatAdapter(material, data_match_test, data_array_player1, data_array_player2);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
