package fr.cdsm.leagueofesportv2.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fr.cdsm.leagueofesportv2.interfaces.MatchAdapterListener;
import fr.cdsm.leagueofesportv2.model.Match;
import fr.cdsm.leagueofesportv2.viewholder.MatchViewHolder;
import fr.cdsm.leagueofesportv2.model.News;
import fr.cdsm.leagueofesportv2.R;

public class MatchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public MaterialDialog material;
    ArrayList<Match> arrayListMatch = new ArrayList<>();
    MatchAdapterListener listener;

    public MatchAdapter(MaterialDialog material, ArrayList<Match> arrayListMatch, MatchAdapterListener listener) {
        this.material = material;
        this.arrayListMatch = arrayListMatch;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MatchViewHolder) holder).textescoreTeam1.setText(arrayListMatch.get(position).team1.score);
        ((MatchViewHolder) holder).textescoreTeam2.setText(arrayListMatch.get(position).team2.score);

        Picasso.with(((MatchViewHolder) holder).imageTeam1.getContext()).load(arrayListMatch.get(position).team1.image).fit().centerInside().into(((MatchViewHolder) holder).imageTeam1);
        Picasso.with(((MatchViewHolder) holder).imageTeam2.getContext()).load(arrayListMatch.get(position).team2.image).fit().centerInside().into(((MatchViewHolder) holder).imageTeam2);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.onMatchClick();
            }
        });

        material.cancel();
    }

    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayListMatch.size();
    }
}