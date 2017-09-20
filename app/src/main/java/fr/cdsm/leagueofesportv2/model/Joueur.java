package fr.cdsm.leagueofesportv2.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Joueur {

    public String pseudo;
    public String champion;

    public Joueur() {
        //Constructeur par défaut
    }

    public Joueur(String pseudonyme, String champion_joue) {
        this.pseudo = pseudonyme;
        this.champion = champion_joue;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("pseudo", pseudo);
        result.put("champion", champion);
        return result;
    }
}
