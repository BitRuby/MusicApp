package com.musicapp.serwer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicapp.serwer.model.response.AlbumRes;
import com.musicapp.serwer.model.response.ArtistRes;
import com.musicapp.serwer.model.response.SearchRes;
import com.musicapp.serwer.model.response.TrackRes;
import com.musicapp.serwer.utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Serwis wykozystywany do szukania utworow, albumow i artystow
 */
@Service
public class SearchService {

    String url = "https://api.spotify.com/v1/search?q=";

    /**
     * Metoda szuka utworow, albumow i artystow o podanej nazwie za pomoca API Spoptify
     *
     * @param name ciag znakow ktory bedzie wyszukiwany
     * @return Zwraca obiekt zawierajacy znalezione utwory, albumy i artystow
     */
    public SearchRes search(String name) {
        name = name.replaceAll(" ", "%2B");
        String newUrl = url + name + "&type=album%2Cartist%2Ctrack&market=PL&limit=20";
        Utils getReq = new Utils();
        StringBuilder response = null;
        SearchRes result = null;
        JSONObject jsonObject = null;
        try {
            response = getReq.getResponseFromSpotify(newUrl);
        } catch (Exception e) {
            return result;
        }
        try {
            String json = response.toString();
            jsonObject = new JSONObject(json);

        } catch (Exception e) {
            System.out.println("Dzejson nie żyje !!! :(");
        }
        result = jsonParser(jsonObject);
//        System.out.println(result);
//        result = dzejson.fromJson(json, TrackRes.class);
//        System.out.println(response);
        return result;
    }

    /**
     * Metoda parsuje pobranego JSONa
     *
     * @param json JSON z API
     * @return Zwraca obiekt zawierajacy znalezione utwory, albumy i artystow
     */
    private SearchRes jsonParser(JSONObject json) {
        SearchRes result = null;
        result = new SearchRes(getArtistFromJson(json),getAlbumsFromJson(json),getTracksFromJson(json));
        return result;
    }

    /**
     * Metoda pobiera z JSONa liste utworow
     *
     * @param json JSON z API
     * @return Zwraca liste utworow
     */
    private ArrayList<TrackRes> getTracksFromJson(JSONObject json) {
        ArrayList<TrackRes> trackResArrayList = new ArrayList<>();
        try {
            JSONArray items = json.getJSONObject("tracks").getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                TrackRes trackRes = new TrackRes();
                trackRes.setId(items.getJSONObject(i).getString("id"));
                trackRes.setType("track");
                trackRes.setTitle(items.getJSONObject(i).getString("name"));
                trackRes.setAlbum(new AlbumRes(items.getJSONObject(i).getJSONObject("album").getString("id"),
                        items.getJSONObject(i).getJSONObject("album").getString("name"),
                        items.getJSONObject(i).getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url")));
                JSONArray artists = items.getJSONObject(i).getJSONArray("artists");
                trackRes.setArtist(new ArtistRes(artists.getJSONObject(0).getString("id"), artists.getJSONObject(0).getString("name")));
                trackRes.setDuration_ms(items.getJSONObject(i).getLong("duration_ms"));
                trackRes.setTrack_number(items.getJSONObject(i).getLong("track_number"));
                trackRes.setHref(items.getJSONObject(i).getString("preview_url"));
                trackResArrayList.add(trackRes);
            }
        } catch (Exception e) {
            System.out.println("Dżejson się źle przepisał :/");
            System.out.println(e);
        }
        return trackResArrayList;
    }
    /**
     * Metoda pobiera z JSONa liste albumow
     *
     * @param json JSON z API
     * @return Zwraca liste albumow
     */
    private ArrayList<AlbumRes> getAlbumsFromJson(JSONObject json) {
        ArrayList<AlbumRes> albumResArrayList = new ArrayList<>();
        try {
            JSONArray items = json.getJSONObject("albums").getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                AlbumRes albumRes = new AlbumRes();
                albumRes.setId(items.getJSONObject(i).getString("id"));
                albumRes.setName(items.getJSONObject(i).getString("name"));
                albumRes.setImg(items.getJSONObject(i).getJSONArray("images").getJSONObject(0).getString("url"));
                albumResArrayList.add(albumRes);
            }
        } catch (Exception e) {
            System.out.println("Dżejson się źle przepisał :/");
            System.out.println(e);
        }
        return albumResArrayList;
    }
    /**
     * Metoda pobiera z JSONa liste artystow
     *
     * @param json JSON z API
     * @return Zwraca liste aartystow
     */
    private ArrayList<ArtistRes> getArtistFromJson(JSONObject json) {
        ArrayList<ArtistRes> artistResArrayList = new ArrayList<>();
        try {
            JSONArray items = json.getJSONObject("artists").getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                ArtistRes artistRes = new ArtistRes();
                artistRes.setId(items.getJSONObject(i).getString("id"));
                artistRes.setName(items.getJSONObject(i).getString("name"));
                artistResArrayList.add(artistRes);
            }
        } catch (Exception e) {
            System.out.println("Dżejson się źle przepisał :/");
            System.out.println(e);
        }
        return artistResArrayList;
    }
}
