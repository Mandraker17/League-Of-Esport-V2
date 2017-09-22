package fr.cdsm.leagueofesportv2.model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cdsm06 on 22/09/2017.
 */

public class ListeMatch {

    public Match match1;
    public Match match2;
    public Match match3;

    public ListeMatch() {

    }

    public ListeMatch(Match combat1, Match combat2, Match combat3) {
        this.match1 = combat1;
        this.match2 = combat2;
        this.match3 = combat3;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("match1", match1);
        result.put("match2", match2);
        result.put("match3", match3);

        return result;
    }
}
