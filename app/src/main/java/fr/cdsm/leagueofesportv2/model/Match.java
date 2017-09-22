package fr.cdsm.leagueofesportv2.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Match {

    public Equipe team1;
    public Equipe team2;

    public Match() {
        //Constructeur par défaut
    }

    public Match(Equipe equipe1, Equipe equipe2) {

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
