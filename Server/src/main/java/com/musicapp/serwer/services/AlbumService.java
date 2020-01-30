package com.musicapp.serwer.services;

import com.musicapp.serwer.model.response.AlbumRes;
import com.musicapp.serwer.model.response.ArtistRes;
import com.musicapp.serwer.utils.Utils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {


    String url = "\thttps://api.spotify.com/v1/albums/";

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
