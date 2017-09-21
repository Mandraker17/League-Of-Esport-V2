package fr.cdsm.leagueofesportv2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Match {

    public EquipeMatch team1;
    public EquipeMatch team2;

    public Match() {
        //Constructeur par défaut
    }

    public Match(EquipeMatch equipe1, EquipeMatch equipe2) {

        this.team1 = equipe1;
        this.team2 = equipe2;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("team1", team1);
        result.put("team2", team2);
        return result;
    }

}
