package com.musicapp.serwer.services;

import com.musicapp.serwer.model.response.FavoriteRes;
import com.musicapp.serwer.model.response.TrackRes;
import com.musicapp.serwer.utils.Utils;
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
        StringBuilder seed_artists = new StringBuilder();
        StringBuilder seed_tracks = new StringBuilder();
        if (getRandomTracks() == null)
            return null;
        else {
            ArrayList<FavoriteRes> fav = (ArrayList<FavoriteRes>) getRandomTracks();
            for (int i = 0; i < fav.size(); i++) {
                seed_artists.append(fav.get(i).getArtistID());
                seed_tracks.append(fav.get(i).getTrackID());
                if(i < fav.size() -1){
                    seed_artists.append(",");
                    seed_tracks.append(",");
                }
            }
            System.out.println(seed_artists);
            System.out.println(seed_tracks);

            //zapytanie do API
            //TODO odkomentowac
//            URL = URL + "?seed_artists=" + seed_artists + "&seed_tracks=" + seed_tracks + "&min_energy=0.4&min_popularity=50";
            URL = "https://api.spotify.com/v1/recommendations?seed_artists=4NHQUGzhtTLFvgF5SZesLK&seed_tracks=0c6xIDDpzE81m2q797ordA&min_energy=0.4&min_popularity=50";
            System.out.println(URL);
            Utils utils = new Utils();
            StringBuilder json = null;
            try {
                json = utils.getResponseFromSpotify(URL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //TODO wyciagniecie info z JSONa
            System.out.println(json);
        }
        return null;
    }

}
