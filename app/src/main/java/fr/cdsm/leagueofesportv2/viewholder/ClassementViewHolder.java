package fr.cdsm.leagueofesportv2.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.cdsm.leagueofesportv2.R;

/**
 * Created by cdsm06 on 26/09/2017.
 */

public class ClassementViewHolder extends RecyclerView.ViewHolder {

    public final TextView texte_team_classement;
    public final TextView texte_score_classement;

    public ClassementViewHolder(View itemView) {
        super(itemView);
        texte_team_classement = (TextView) itemView.findViewById(R.id.txt_team_classement);
        texte_score_classement = (TextView) itemView.findViewById(R.id.txt_score_classement);
    }
}
