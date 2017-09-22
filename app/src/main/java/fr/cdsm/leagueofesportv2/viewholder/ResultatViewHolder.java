package fr.cdsm.leagueofesportv2.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.cdsm.leagueofesportv2.R;

/**
 * Created by cdsm06 on 22/09/2017.
 */

public class ResultatViewHolder extends RecyclerView.ViewHolder {

    public final ImageView imageResultChampion1;
    public final ImageView imageResultChampion2;
    public final TextView texteResultPseudo1;
    public final TextView texteResultPseudo2;

    public ResultatViewHolder(View itemView) {
        super(itemView);

        imageResultChampion1 = (ImageView) itemView.findViewById(R.id.img_result_champ1);
        imageResultChampion2 = (ImageView) itemView.findViewById(R.id.img_result_champ2);
        texteResultPseudo1 = (TextView) itemView.findViewById(R.id.txt_result_pseudo1);
        texteResultPseudo2 = (TextView) itemView.findViewById(R.id.txt_result_pseudo2);

    }
}
