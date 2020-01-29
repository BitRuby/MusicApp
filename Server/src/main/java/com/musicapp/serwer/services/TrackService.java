package com.musicapp.serwer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.musicapp.serwer.model.response.AlbumRes;
import com.musicapp.serwer.model.response.ArtistRes;
import com.musicapp.serwer.model.response.TrackRes;
import com.musicapp.serwer.repositories.TrackRepo;
import com.musicapp.serwer.utils.Utils;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;

@Service
public class TrackService {

    @Autowired
    private TrackRepo trackRepo;
    Gson dzejson = new Gson();
    String url = "https://api.spotify.com/v1/tracks/";

    public TrackRes searchTrackByID(String id) {
        ObjectMapper mapper = new ObjectMapper();
        Utils getReq = new Utils();
        StringBuilder response = null;
        TrackRes result = null;
        JSONObject jsonObject = null;
        try {
            response = getReq.getResponseFromSpotify(url + id);
        } catch (Exception e) {
            result = new TrackRes();
            result.setId("0");
            return result;
        }
        try {
            String json = response.toString();
            jsonObject = new JSONObject(json);

        }catch (Exception e){
            System.out.println("Dzejson nie żyje !!! :(");
        }
        result = JsonToTrackRes(jsonObject);
        System.out.println(result);
//        result = dzejson.fromJson(json, TrackRes.class);
        System.out.println(response);
        return result;
    }

    public List<TrackRes> getAll() {
        return trackRepo.findAll();
    }

    private TrackRes JsonToTrackRes(JSONObject json){
        TrackRes result = new TrackRes();
        try{
            result.setId(json.getString("id"));
            result.setType(json.getString("type"));
            result.setDuration_ms(json.getLong("duration_ms"));
            result.setHref(json.getString("href"));
            result.setTrack_number(json.getLong("track_number"));
            result.setTitle(json.getString("name"));
            result.setArtist(new ArtistRes(json.getJSONArray("artists").getJSONObject(0).getString("id"), json.getJSONArray("artists").getJSONObject(0).getString("name")));
            result.setAlbum(new AlbumRes(json.getJSONObject("album").getString("id"), json.getJSONObject("album").getString("name")));
        }catch (Exception e){
            System.out.println("Dżejson się źle przepisał :/");
            System.out.println(e);
        }
        return result;
    }
}
