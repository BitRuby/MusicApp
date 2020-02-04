package com.musicapp.serwer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.musicapp.serwer.model.response.AlbumRes;
import com.musicapp.serwer.model.response.ArtistRes;
import com.musicapp.serwer.model.response.FavoriteRes;
import com.musicapp.serwer.model.response.TrackRes;
import com.musicapp.serwer.repositories.FavoriteRepo;
import com.musicapp.serwer.repositories.TrackRepo;
import com.musicapp.serwer.utils.Utils;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Serwis wykozystywany do operacji na utworach
 */
@Service
public class TrackService {

    @Autowired
    private TrackRepo trackRepo;
    @Autowired
    private FavoriteRepo favoriteRepo;

    Gson dzejson = new Gson();
    String url = "https://api.spotify.com/v1/tracks/";

    /**
     * Metoda pobiera informacje o danym utworze
     *
     * @param id id utworu
     * @return Zwraca informacje o utowrze.
     */
    public TrackRes searchTrackByID(String id) {
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

        } catch (Exception e) {
            System.out.println("Dzejson nie żyje !!! :(");
        }
        result = JsonToTrackRes(jsonObject);
        System.out.println(result);
        System.out.println(response);
        return result;
    }

    public List<TrackRes> getAll() {
        return trackRepo.findAll();
    }

    /**
     * Metoda pobiera informacje z JSONa i zapisuje je w obiekcie TrackRes
     *
     * @param json JSON z zdpowiedza od API
     * @return Zwraca obiekt z danymi utworu
     */
    private TrackRes JsonToTrackRes(JSONObject json) {
        TrackRes result = new TrackRes();

        try {
            result.setId(json.getString("id"));
            result.setType(json.getString("type"));
            result.setDuration_ms(json.getLong("duration_ms"));
            result.setHref(json.getString("preview_url"));
            result.setTrack_number(json.getLong("track_number"));
            result.setTitle(json.getString("name"));
            result.setArtist(new ArtistRes(json.getJSONArray("artists").getJSONObject(0).getString("id"), json.getJSONArray("artists").getJSONObject(0).getString("name")));
            result.setAlbum(new AlbumRes(json.getJSONObject("album").getString("id"), json.getJSONObject("album").getString("name"),
                    json.getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url")));
            FavoriteRes tmp = favoriteRepo.findOneByTrackID(result.getId());
            if(tmp.getName()!=null){
                result.setFavorites(true);
            }
            else result.setFavorites(false);
        } catch (Exception e) {
            System.out.println("Dżejson się źle przepisał :/");
            System.out.println(e);
        }
        return result;
    }


    /**
     * Metoda pobiera liste utworow na bazie id albumu
     *
     * @param id id albumu
     * @return Zwraca liste utworow
     */
    public List<TrackRes> searchTrackByAlbumID(String id) {
        String newUrl = "https://api.spotify.com/v1/albums/" + id + "/tracks";
        Utils getReq = new Utils();
        StringBuilder response = null;
        ArrayList<TrackRes> result = null;
        JSONObject jsonObject = null;
        try {
            response = getReq.getResponseFromSpotify(newUrl);
        } catch (Exception e) {
            return null;
        }
        try {
            String json = response.toString();
            jsonObject = new JSONObject(json);

        } catch (Exception e) {
            System.out.println("Dzejson nie żyje !!! :(");
        }
        result = jsonToTrackList(jsonObject);
        System.out.println(result);
        System.out.println(response);
        return result;
    }

    /**
     * Metoda pobiera informacje z JSONa i zapisuje je w obiekcie TrackRes
     *
     * @param json JSON z zdpowiedza od API
     * @return Zwraca obiekt z danymi utworu
     */
    private ArrayList<TrackRes> jsonToTrackList(JSONObject json) {
        ArrayList<TrackRes> list = new ArrayList<>();
        try {
            JSONArray array = json.getJSONArray("items");
            for (int i = 0; i < array.length(); i++) {
                TrackRes result = new TrackRes();
                result.setId(array.getJSONObject(i).getString("id"));
                result.setType(array.getJSONObject(i).getString("type"));
                result.setDuration_ms(array.getJSONObject(i).getLong("duration_ms"));
                result.setHref(array.getJSONObject(i).getString("preview_url"));
                result.setTrack_number(array.getJSONObject(i).getLong("track_number"));
                result.setTitle(array.getJSONObject(i).getString("name"));
                result.setArtist(new ArtistRes(array.getJSONObject(i).getJSONArray("artists").getJSONObject(0).getString("id"), array.getJSONObject(i).getJSONArray("artists").getJSONObject(0).getString("name")));
                FavoriteRes tmp = favoriteRepo.findOneByTrackID(result.getId());
                if(tmp.getName()!=null){
                    result.setFavorites(true);
                }
                else result.setFavorites(false);
                list.add(result);
            }
        } catch (Exception e) {
            System.out.println("Dżejson się źle przepisał :/");
            System.out.println(e);
        }
        return list;
    }

    /**
     * Metoda pobiera informacje o kilku utworach
     *
     * @param url url do endpointa zwracajacego informacje o kilku utowrach
     * @return Zwraca informacje o utworach.
     */
    public List<TrackRes> searchTracksByIDs(String url) {
        Utils getReq = new Utils();
        StringBuilder response = null;
        List<TrackRes> result = null;
        JSONObject jsonObject = null;
        try {
            response = getReq.getResponseFromSpotify(url);
        } catch (Exception e) {
            return result;
        }
        try {
            String json = response.toString();
            jsonObject = new JSONObject(json);

        } catch (Exception e) {
            System.out.println("Dzejson nie żyje !!! :(");
        }
        result = jsonToList(jsonObject);
        System.out.println(result);
        System.out.println(response);
        return result;
    }


    /**
     * Metoda pobiera informacje z JSONa i zapisuje je w obiekcie TrackRes
     *
     * @param json JSON z zdpowiedza od API
     * @return Zwraca obiekt z danymi utworu
     */
    private ArrayList<TrackRes> jsonToList(JSONObject json) {
        ArrayList<TrackRes> list = new ArrayList<>();
        try {
            JSONArray track = json.getJSONArray("tracks");
            for (int i = 0; i < track.length(); i++) {
                TrackRes result = new TrackRes();
                JSONObject obj = track.getJSONObject(i);
                result.setId(obj.getString("id"));
                result.setType(obj.getString("type"));
                result.setDuration_ms(obj.getLong("duration_ms"));
                result.setHref(obj.getString("preview_url"));
                result.setTrack_number(obj.getLong("track_number"));
                result.setTitle(obj.getString("name"));
                result.setArtist(new ArtistRes(obj.getJSONArray("artists").getJSONObject(0).getString("id"), obj.getJSONArray("artists").getJSONObject(0).getString("name")));
                result.setAlbum(new AlbumRes(obj.getJSONObject("album").getString("id"), obj.getJSONObject("album").getString("name"),
                        obj.getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url")));
                FavoriteRes tmp = favoriteRepo.findOneByTrackID(result.getId());
                if(tmp.getName()!=null){
                    result.setFavorites(true);
                }
                else result.setFavorites(false);
            list.add(result);
            }
        } catch (Exception e) {
            System.out.println("Dżejson się źle przepisał :/");
            System.out.println(e);
        }
        return list;
    }

}
