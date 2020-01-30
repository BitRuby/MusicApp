package com.musicapp.serwer.services;

import com.musicapp.serwer.model.response.AlbumRes;
import com.musicapp.serwer.model.response.ArtistRes;
import com.musicapp.serwer.model.response.FavoriteRes;
import com.musicapp.serwer.model.response.TrackRes;
import com.musicapp.serwer.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RecommendationService {
    private String URL = "https://api.spotify.com/v1/recommendations";

    @Autowired
    FavoriteService favoriteService;

    //losuje do 5 ulubionych utworow
    private List<FavoriteRes> getRandomTracks() {
        ArrayList<FavoriteRes> favoriteResArrayList = (ArrayList<FavoriteRes>) favoriteService.getAll();
        ArrayList<FavoriteRes> randomFav = null;
        Random random = new Random();
        if (favoriteResArrayList.size() == 0)
            return null;
        if (favoriteResArrayList.size() < 5) {
            return favoriteResArrayList;
        }
        if (favoriteResArrayList.size() > 5) {
            randomFav = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                int index = random.nextInt(favoriteResArrayList.size());
                randomFav.add(favoriteResArrayList.get(index));
                favoriteResArrayList.remove(index);
            }
        }
        return randomFav;
    }

    public TrackRes getRecommendation() {
        StringBuilder seed_tracks = new StringBuilder();
        TrackRes result = null;
        if (getRandomTracks() == null)
            return null;
        else {
            ArrayList<FavoriteRes> fav = (ArrayList<FavoriteRes>) getRandomTracks();
            for (int i = 0; i < fav.size(); i++) {
                seed_tracks.append(fav.get(i).getTrackID());
                if (i < fav.size() - 1) {
                    seed_tracks.append(",");
                }
            }
            System.out.println(seed_tracks);

            //zapytanie do API
            String newURL = URL + "?seed_tracks=" + seed_tracks + "&min_energy=0.4&min_popularity=50";
            System.out.println(newURL);
            Utils utils = new Utils();
            StringBuilder json = null;
            try {
                json = utils.getResponseFromSpotify(newURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            result = jsonParser(json.toString());
//            System.out.println(json);
        }
        return result;
    }


    private TrackRes jsonParser(String json) {
        ArrayList<TrackRes> listOfTrack = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray tracks = jsonObject.getJSONArray("tracks");
            for (int i = 0; i < tracks.length(); i++) {
                TrackRes result = new TrackRes();
                result.setId(tracks.getJSONObject(i).getString("id"));
                result.setType("track");
                result.setTitle(tracks.getJSONObject(i).getString("name"));
                result.setAlbum(new AlbumRes(tracks.getJSONObject(i).getJSONObject("album").getString("id"),
                        tracks.getJSONObject(i).getJSONObject("album").getString("name"),
                        tracks.getJSONObject(i).getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url")));
                JSONArray artists = tracks.getJSONObject(i).getJSONArray("artists");
                result.setArtist(new ArtistRes(artists.getJSONObject(0).getString("id"),artists.getJSONObject(0).getString("name")));
                result.setDuration_ms(tracks.getJSONObject(i).getLong("duration_ms"));
                result.setTrack_number(tracks.getJSONObject(i).getLong("track_number"));
                result.setHref(tracks.getJSONObject(i).getString("preview_url"));

                if(!result.getHref().equals("null"))
                    listOfTrack.add(result);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listOfTrack.get(new Random().nextInt(listOfTrack.size()-1));
    }

}
