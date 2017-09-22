package fr.cdsm.leagueofesportv2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import fr.cdsm.leagueofesportv2.R;
import fr.cdsm.leagueofesportv2.interfaces.MatchAdapterListener;
import fr.cdsm.leagueofesportv2.model.Best_Of;
import fr.cdsm.leagueofesportv2.model.Match;
import fr.cdsm.leagueofesportv2.viewholder.ResultatViewHolder;

public class ResultatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public MaterialDialog material;
    ArrayList<Match> arrayListMatch = new ArrayList<Match>();
    MatchAdapterListener listener;

    public ResultatAdapter(MaterialDialog material, ArrayList<Match> arrayListMatch, MatchAdapterListener listener) {
        this.material = material;
        this.arrayListMatch = arrayListMatch;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultats, parent, false);
        return new ResultatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ResultatViewHolder) holder).texteResultPseudo1.setText(arrayListMatch.get(position).team1.player1.pseudo);
        ((ResultatViewHolder) holder).texteResultPseudo1.setText(arrayListMatch.get(position).team1.player1.pseudo);
        material.cancel();
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
