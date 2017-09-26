package fr.cdsm.leagueofesportv2.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Classement {

    public String equipe;
    public String score;

    public Classement() {

    }

    public Classement(String team, String score_classement) {
        this.equipe = team;
        this.score = score_classement;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("equipe", equipe);
        result.put("score", score);
        return result;
    }
}
