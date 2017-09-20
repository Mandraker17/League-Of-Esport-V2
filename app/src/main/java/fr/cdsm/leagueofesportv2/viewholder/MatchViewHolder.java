package fr.cdsm.leagueofesportv2.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.cdsm.leagueofesportv2.R;


public class MatchViewHolder extends RecyclerView.ViewHolder {

    //Déclaration des variables qui composent l'item
    public final ImageView imageTeam1;
    public final ImageView imageTeam2;
    public final TextView textescoreTeam1;
    public final TextView textescoreTeam2;

    public MatchViewHolder(View itemView) {
        super(itemView);
        //Initialisation des variables en les récupérant du layout
        imageTeam1 = (ImageView) itemView.findViewById(R.id.img_team1_match);
        imageTeam2 = (ImageView) itemView.findViewById(R.id.img_team2_match);
        textescoreTeam1 = (TextView) itemView.findViewById(R.id.txt_score1_match);
        textescoreTeam2 = (TextView) itemView.findViewById(R.id.txt_score2_match);

    }
}
