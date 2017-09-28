package fr.cdsm.leagueofesportv2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fr.cdsm.leagueofesportv2.R;
import fr.cdsm.leagueofesportv2.interfaces.MatchAdapterListener;
import fr.cdsm.leagueofesportv2.model.Best_Of;
import fr.cdsm.leagueofesportv2.model.Joueur;
import fr.cdsm.leagueofesportv2.model.Match;
import fr.cdsm.leagueofesportv2.viewholder.MatchViewHolder;
import fr.cdsm.leagueofesportv2.viewholder.ResultatViewHolder;

public class ResultatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public MaterialDialog material;
    ArrayList<Match> arrayListMatch = new ArrayList<Match>();
    MatchAdapterListener listener;
    ArrayList<Joueur> data_team1 = new ArrayList<Joueur>();
    ArrayList<Joueur> data_team2 = new ArrayList<Joueur>();


    public ResultatAdapter(MaterialDialog material, ArrayList<Match> arrayListMatch, ArrayList<Joueur> data_team1, ArrayList<Joueur> data_team2) {
        this.material = material;
        this.arrayListMatch = arrayListMatch;
        this.data_team1 = data_team1;
        this.data_team2 = data_team2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultats, parent, false);
        return new ResultatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ResultatViewHolder) holder).texteResultPseudo1.setText(data_team1.get(position).pseudo);
        ((ResultatViewHolder) holder).texteResultPseudo2.setText(data_team2.get(position).pseudo);

        Picasso.with(((ResultatViewHolder) holder).imageResultChampion1.getContext()).load(data_team1.get(position).champion).fit().centerInside().into(((ResultatViewHolder) holder).imageResultChampion1);
        Picasso.with(((ResultatViewHolder) holder).imageResultChampion2.getContext()).load(data_team2.get(position).champion).fit().centerInside().into(((ResultatViewHolder) holder).imageResultChampion2);

        material.cancel();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
