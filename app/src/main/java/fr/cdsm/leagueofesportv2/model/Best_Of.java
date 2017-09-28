package fr.cdsm.leagueofesportv2.model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cdsm06 on 21/09/2017.
 */

public class Best_Of {

    public String score_final_team1;
    public String score_final_team2;
    public String image_team1;
    public String image_team2;
    public ArrayList<Match> data_match;

    public Best_Of() {

    }

    public Best_Of(String score_final_equipe1, String score_final_equipe2, String image_equipe1, String image_equipe2, ArrayList<Match> liste_data_match) {
        this.score_final_team1 = score_final_equipe1;
        this.score_final_team2 = score_final_equipe2;
        this.image_team1 = image_equipe1;
        this.image_team2 = image_equipe2;
        this.data_match = liste_data_match;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("score_final_team1", score_final_team1);
        result.put("score_final_team2", score_final_team2);
        result.put("image_team1", image_team1);
        result.put("image_team2", image_team2);
        result.put("data_match", data_match);
        return result;
    }
}
