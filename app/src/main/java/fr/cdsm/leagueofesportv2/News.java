package fr.cdsm.leagueofesportv2;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cdsm06 on 06/07/2017.
 */

public class News {

    public String image;
    public String text;


    public News() {
        //Constructeur par defaut
    }

    public News(String url_image, String texte_actu) {
        this.image = url_image;
        this.text = texte_actu;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("image", image);
        result.put("text", text);
        return result;
    }

}
