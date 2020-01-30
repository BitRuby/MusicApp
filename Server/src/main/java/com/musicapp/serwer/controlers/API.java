package com.musicapp.serwer.controlers;

import com.google.gson.Gson;
import com.musicapp.serwer.model.response.*;
import com.musicapp.serwer.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class API {

    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private RecommendationService recommendationService;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private TrackService trackService;
    @Autowired
    ArtistService artistService;
    @Autowired
    AlbumService albumService;
    @Autowired
    SearchService searchService;

    private Gson gson = new Gson();

    @GetMapping(path = "/playlist/{id}")
    public ResponseEntity<String> getPlaylist(@PathVariable String id) {
        PlaylistRes returnValue = playlistService.searchPlaylistByID(id);
        Gson gson = new Gson();
        String json = gson.toJson(returnValue);
        if (returnValue == null) {
            json = "Nie znaleziono playlisty";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/playlists/{n}")
    public ResponseEntity<String> getPlaylists(@PathVariable int n) {
        ArrayList<PlaylistRes> response = (ArrayList<PlaylistRes>) playlistService.getNPlaylist(n);

        Gson gson = new Gson();
        String json = gson.toJson(response);
        if (response.size() < 1) {
            json = "Brak zawartości w playlist";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/playlists")
    public ResponseEntity<String> getPlaylists() {
        ArrayList<PlaylistRes> response = (ArrayList<PlaylistRes>) playlistService.getAll();

        Gson gson = new Gson();
        String json = gson.toJson(response);
        if (response.size() < 1) {
            json = "Brak zawartości w playlist";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/favorites/{n}")
    public ResponseEntity<String> getFavorites(@PathVariable int n) {
        ArrayList<FavoriteRes> response = new ArrayList<> (favoriteService.getNtracks(n));

        Gson gson = new Gson();
        String json = gson.toJson(response);
        if (response.size() < 1) {
            json = "Brak zawartości w ulubione";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/favorites")
    public ResponseEntity<String> getFavorites() {
        ArrayList<FavoriteRes> response = (ArrayList<FavoriteRes>) favoriteService.getAll();

        String json = gson.toJson(response);
        if (response.size() < 1) {
            json = "Brak zawartości w ulubione";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @PostMapping(path = "/favorites")
    public ResponseEntity<String> postFavotires(@RequestParam(required = true) String id){
        FavoriteRes response = favoriteService.findOne(id);

        Gson gson = new Gson();
        String json = gson.toJson(response);
        if (response == null) {
            json = "Nie znaleziono utworu";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/track/{id}")
    public ResponseEntity<String> getTrack(@PathVariable String id){
        TrackRes response = trackService.searchTrackByID(id);
        String json = gson.toJson(response);
        if(response==null || response.getId().equals("0")){
            json = null;
            json = "Nie znaleziono utworu";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }


    @GetMapping(path = "/artist/{id}")
    public ResponseEntity<String> getArtist(@PathVariable String id){
        ArtistRes returnValue = artistService.searchArtistByID(id);
        Gson gson = new Gson();
        String json = gson.toJson(returnValue);
        if(returnValue == null){
            json = "Nie znaleziono albumu";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }



    @GetMapping(path = "/recommendations")
    public ResponseEntity<String> getRecommendation() {
        TrackRes response = recommendationService.getRecommendation();
        String json = gson.toJson(response);
        if (response == null) {
            json = "Brak proponowanych utworow";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/album/{id}")
    public ResponseEntity<String> getAlbum(@PathVariable String id){
        AlbumRes returnValue = albumService.searchAlbumByID(id);
        Gson gson = new Gson();
        String json = gson.toJson(returnValue);
        if(returnValue == null){
            json = "Nie znaleziono albumu";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/search/{name}")
    public ResponseEntity<String> getSearch(@PathVariable String name){
        SearchRes response = searchService.search(name);

        Gson gson = new Gson();
        String json = gson.toJson(response);
        if(response==null){
            json = "Nie znaleziono";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/addTracks")
    public void addSomeTracks(){
        favoriteService.addTrack("63yesoRJgXT5QALryYFV0X");
        favoriteService.addTrack("2uZVfvOK7MTjBTRICYmpso");
        favoriteService.addTrack("3lPr8ghNDBLc2uZovNyLs9");
        favoriteService.addTrack("5JHNg1hxZFT7TDEphhM4wj");
        favoriteService.addTrack("0bRQiJTHxIXb45d8uwTMlG");
        favoriteService.addTrack("4xrzG4pw9JN8BJqqjurcPs");
    }

    @GetMapping(path = "/dropAllTracks")
    public void deleteAllTracks(){
        favoriteService.dropAll();
    }

    @GetMapping(path = "/addPlaylists")
    public void addSomePlaylists(){
        playlistService.addPlaylist("3fPG8zDPmtahUJkmdq3OSX");
        playlistService.addPlaylist("37i9dQZF1DX6GJVEkXUD2r");
    }

    @GetMapping(path = "/dropAllPlaylists")
    public void deleteAllPlaylists(){
        playlistService.dropAll();
    }





}
