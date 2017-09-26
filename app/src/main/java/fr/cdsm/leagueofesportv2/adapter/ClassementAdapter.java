package fr.cdsm.leagueofesportv2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fr.cdsm.leagueofesportv2.R;
import fr.cdsm.leagueofesportv2.model.Classement;
import fr.cdsm.leagueofesportv2.viewholder.ClassementViewHolder;
import fr.cdsm.leagueofesportv2.viewholder.MatchViewHolder;

/**
 * Created by cdsm06 on 26/09/2017.
 */

public class ClassementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Classement> arrayListClassement = new ArrayList<Classement>();

    public ClassementAdapter(ArrayList<Classement> arrayListClassement) {
        this.arrayListClassement = arrayListClassement;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classement, parent, false);
        return new ClassementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ClassementViewHolder) holder).texte_score_classement.setText(arrayListClassement.get(position).score);
        ((ClassementViewHolder) holder).texte_team_classement.setText(arrayListClassement.get(position).equipe);
    }

    @Override
    public int getItemCount() {
        return arrayListClassement.size();
    }
}
