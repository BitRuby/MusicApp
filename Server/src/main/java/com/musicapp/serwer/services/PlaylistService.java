package com.musicapp.serwer.services;

import com.musicapp.serwer.model.response.*;
import com.musicapp.serwer.repositories.PlaylistRepo;
import com.musicapp.serwer.utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Serwis wykozystywany do operacji na playlistach
 */
@Service
public class PlaylistService {
    String url = "https://api.spotify.com/v1/playlists/";

    @Autowired
    PlaylistRepo playlistRepo;

    /**
     * Metoda dodaje playliste do bazy
     * @param id id playlisty
     */
    public void addPlaylist(String id) {
        playlistRepo.save(new PlaylistRes(id));
    }

    /**
     * Metoda pobiera wszystkie playlisty z bazy
     * @return Zwraca liste playlist
     */
    public List<PlaylistRes> getAll() {
        ArrayList<PlaylistRes> id = (ArrayList<PlaylistRes>) playlistRepo.findAll();
        ArrayList<PlaylistRes> result = new ArrayList<>();
        for (PlaylistRes d : id) {
            result.add(searchPlaylistByID(d.getId()));
        }
        return result;
    }

    /**
     * Metoda usuwa wszystkie playlisty z bazy
     */
    public void dropAll() {
        playlistRepo.deleteAll();
    }

    /**
     * Metoda pobiera N playlistlist
     * @param n liczba list do pobrania
     * @return Zwraca N playlist
     */
    public List<PlaylistRes> getNPlaylist(int n) {
        if (playlistRepo.findAll().size() < n)
            return getAll();
        else {
            ArrayList<PlaylistRes> id = new ArrayList<>(playlistRepo.findAll().subList(0, n));
            ArrayList<PlaylistRes> result = new ArrayList<>();
            for (PlaylistRes d : id) {
                result.add(searchPlaylistByID(d.getId()));
            }
            return result;
        }
    }

    /**
     * Metoda pobiera informacje o playliscie z API Spotify
     * @param id id playlisty
     * @return Zwraca informacje o danej playliscie
     */
    public PlaylistRes searchPlaylistByID(String id) {
        String newUrl = url + id;
        Utils getReq = new Utils();
        StringBuilder response = null;
        PlaylistRes result = null;
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
        result = jsonToPlaylistRes(jsonObject);
        System.out.println(result);
//        result = dzejson.fromJson(json, TrackRes.class);
//        System.out.println(response);
        return result;
    }

    /**
     * Metoda pobiera z JSONa informacje o playliscie
     *
     * @param json JSON z API
     * @return Zwraca obiekt playlisty
     */
    private PlaylistRes jsonToPlaylistRes(JSONObject json) {
        PlaylistRes result = new PlaylistRes();
        ArrayList<TrackRes> trackResArrayList = new ArrayList<>();
        try {
            result.setId(json.getString("id"));
            result.setName(json.getString("name"));
            result.setImgCover(json.getJSONArray("images").getJSONObject(0).getString("url"));

            JSONArray items = json.getJSONObject("tracks").getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                TrackRes trackRes = new TrackRes();
                trackRes.setId(items.getJSONObject(i).getJSONObject("track").getString("id"));
                trackRes.setType("track");
                trackRes.setTitle(items.getJSONObject(i).getJSONObject("track").getString("name"));
                trackRes.setAlbum(new AlbumRes(items.getJSONObject(i).getJSONObject("track").getJSONObject("album").getString("id"),
                        items.getJSONObject(i).getJSONObject("track").getJSONObject("album").getString("name"),
                        items.getJSONObject(i).getJSONObject("track").getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url")));
                JSONArray artists = items.getJSONObject(i).getJSONObject("track").getJSONArray("artists");
                trackRes.setArtist(new ArtistRes(artists.getJSONObject(0).getString("id"), artists.getJSONObject(0).getString("name")));
                trackRes.setDuration_ms(items.getJSONObject(i).getJSONObject("track").getLong("duration_ms"));
                trackRes.setTrack_number(items.getJSONObject(i).getJSONObject("track").getLong("track_number"));
                trackRes.setHref(items.getJSONObject(i).getJSONObject("track").getString("preview_url"));
                trackResArrayList.add(trackRes);
            }
            result.setContent(trackResArrayList);

        } catch (Exception e) {
            System.out.println("Dżejson się źle przepisał :/");
            System.out.println(e);
        }
        return result;

    }
}
