package com.musicapp.serwer.services;

import com.musicapp.serwer.model.response.AlbumRes;
import com.musicapp.serwer.model.response.ArtistRes;
import com.musicapp.serwer.utils.Utils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Serwis wykozystywany do operacji na albumach
 */
@Service
public class AlbumService {

    String url = "https://api.spotify.com/v1/albums/";

    /**
     * Metoda pobiera informacje o danym albumie
     *
     * @param id id albumu
     * @return Zwraca informacje o artyscie.
     */
    public AlbumRes searchAlbumByID(String id) {
        Utils getReq = new Utils();
        StringBuilder response = null;
        AlbumRes result = null;
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
     * Metoda pobiera informacje z JSONa i zapisuje je w obiekcie AlbumRes
     *
     * @param json JSON z odpowiedza od API
     * @return Zwraca obiekt z danymi albumu
     */
    private AlbumRes JsonToArtistRes(JSONObject json){
        AlbumRes result = new AlbumRes();
        try{
            result.setId(json.getString("id"));
            result.setName(json.getString("name"));
            result.setImg(json.getJSONArray("images").getJSONObject(0).getString("url"));

        }catch (Exception e){
            System.out.println("Dżejson się źle przepisał :/");
            System.out.println(e);
        }
        return result;
    }
}
