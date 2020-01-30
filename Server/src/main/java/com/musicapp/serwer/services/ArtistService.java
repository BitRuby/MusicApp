package com.musicapp.serwer.services;

import com.musicapp.serwer.model.response.ArtistRes;
import com.musicapp.serwer.utils.Utils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Serwis wykozystywany do operacji na artysrach
 */
@Service
public class ArtistService {

    String url = "https://api.spotify.com/v1/artists/";

    /**
     * Metoda pobiera informacje o danym artyscie
     *
     * @param id id artysty
     * @return Zwraca informacje o artyscie.
     */
    public ArtistRes searchArtistByID(String id) {
        Utils getReq = new Utils();
        StringBuilder response = null;
        ArtistRes result = null;
        JSONObject jsonObject = null;
        try {
            response = getReq.getResponseFromSpotify(url + id);
        } catch (Exception e) {
            return result;
        }
        try {
            String json = response.toString();
            jsonObject = new JSONObject(json);

        }catch (Exception e){
            System.out.println("Dzejson nie żyje !!! :(");
        }
        result = JsonToArtistRes(jsonObject);
//        System.out.println(result);
//        result = dzejson.fromJson(json, TrackRes.class);
//        System.out.println(response);
        return result;
    }

    /**
     * Metoda pobiera informacje z JSONa i zapisuje je w obiekcie ArtistRes
     *
     * @param json JSON z odpowiedza od API
     * @return Zwraca obiekt z danymi artysty
     */
    private ArtistRes JsonToArtistRes(JSONObject json){
        ArtistRes result = new ArtistRes();
        try{
            result.setId(json.getString("id"));
            result.setId(json.getString("name"));
        }catch (Exception e){
            System.out.println("Dżejson się źle przepisał :/");
            System.out.println(e);
        }
        return result;
    }

}
