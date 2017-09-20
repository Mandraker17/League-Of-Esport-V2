package fr.cdsm.leagueofesportv2.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class EquipeMatch {

    public String score;
    public String image;
    public Joueur player1;
    public Joueur player2;
    public Joueur player3;
    public Joueur player4;
    public Joueur player5;

    public EquipeMatch() {

    }

    public EquipeMatch(String score_fin, String image_logo, Joueur joueur1, Joueur joueur2, Joueur joueur3, Joueur joueur4, Joueur joueur5) {
        this.score = score_fin;
        this.image = image_logo;
        this.player1 = joueur1;
        this.player2 = joueur2;
        this.player3 = joueur3;
        this.player4 = joueur4;
        this.player5 = joueur5;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("score", score);
        result.put("image", image);
        result.put("player1", player1);
        result.put("player2", player2);
        result.put("player3", player3);
        result.put("player4", player4);
        result.put("player5", player5);
        return result;
    }
}
