package fr.cdsm.leagueofesportv2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fr.cdsm.leagueofesportv2.R;
import fr.cdsm.leagueofesportv2.model.Joueur;

public class FragmentResultat extends android.app.Fragment {

    TextView mTextview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_resultat, container, false);
        mTextview = (TextView) rootview.findViewById(R.id.txt_result_test);
        return rootview;
    }

}
