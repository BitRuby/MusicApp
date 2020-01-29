package com.musicapp.serwer.controlers;

import com.google.gson.Gson;
import com.musicapp.serwer.model.response.*;
import com.musicapp.serwer.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("api")
public class API {

    @Autowired
    private FavoriteService favoriteService;

    @Value("${token}")
    private String token;

    @GetMapping(path = "/playlist/{id}")
    public ResponseEntity<String> getPlaylist(@PathVariable long id){
        PlaylistRes returnValue =  new PlaylistRes();
        returnValue.setId(id);
        returnValue.setName("number123");
        returnValue.setImgCover("img"+id);
        Gson gson = new Gson();
        String json = gson.toJson(returnValue);
        if(returnValue.getId() == 0){
            json = "Brak playlisty o podanym identyfikatorze";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/playlists/{n}")
    public ResponseEntity<String> getPlaylists(@PathVariable long n) {
        ArrayList<PlaylistRes> response = new ArrayList<>();
        int i = 0;
        for (i = 0; i < n; i++) {
            PlaylistRes returnValue = new PlaylistRes();
            returnValue.setId(i + 1);
            returnValue.setImgCover("img"+i);
            response.add(returnValue);
        }
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
        ArrayList<PlaylistRes> response = new ArrayList<>();
        int i = 0;
        int nr = 100;
        for (i = 0; i < nr; i++) {
            PlaylistRes returnValue = new PlaylistRes();
            returnValue.setId(i + 1);
            returnValue.setImgCover("img"+i);
            response.add(returnValue);
        }
        Gson gson = new Gson();
        String json = gson.toJson(response);
        if (response.size() < 1) {
            json = "Brak zawartości w playlist";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/favorites/{n}")
    public ResponseEntity<String> getFavorites(@PathVariable long n) {
        ArrayList<FavoriteRes> response = new ArrayList<>();
        int i = 0;
        for (i = 0; i < n; i++) {
            FavoriteRes returnValue = new FavoriteRes();
            returnValue.setId(i + 1);
            returnValue.setTrackID("number123number"+i);
            response.add(returnValue);
        }
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
        ArrayList<FavoriteRes> response = new ArrayList<>();
        int i = 0;
        int n= 100;
        for (i = 0; i < n; i++) {
            FavoriteRes returnValue = new FavoriteRes();
            returnValue.setId(i + 1);
            returnValue.setTrackID("number123number"+i);
            response.add(returnValue);
        }
        Gson gson = new Gson();
        String json = gson.toJson(response);
        if (response.size() < 1) {
            json = "Brak zawartości w ulubione";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/track/{id}")
    public ResponseEntity<String> getTrack(@PathVariable String id){
        TrackRes returnValue =  new TrackRes();
        returnValue.setId(id);
        returnValue.setTitle("Gender");
        returnValue.setAlbum(new AlbumRes("12", "O-dur C-ból"));
        returnValue.setArtist(new ArtistRes( "13", "Łydka Grubasa"));
        returnValue.setDuration_ms(69420L);
        returnValue.setHref("https://open.spotify.com/track/5YchihSamhn4REnnfW3ESJ");
        returnValue.setTrack_number(13L);
        returnValue.setType("Dzika Viksa");
        Gson gson = new Gson();
        String json = gson.toJson(returnValue);
        if(returnValue.getId().equals("0")){
            json = "Nie znaleziono utworu";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/album/{id}")
    public ResponseEntity<String> getAlbum(@PathVariable String id){
        AlbumRes returnValue = new AlbumRes(id, "O-dur C-ból");
        Gson gson = new Gson();
        String json = gson.toJson(returnValue);
        if(returnValue.getId().equals("0")){
            json = "Nie znaleziono albumu";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/artist/{id}")
    public ResponseEntity<String> getArtist(@PathVariable String id){
        ArtistRes returnValue = new ArtistRes( id, "Łydka Grubasa");
        Gson gson = new Gson();
        String json = gson.toJson(returnValue);
        if(returnValue.getId().equals("0")){
            json = "Nie znaleziono albumu";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping(path = "/search/{name}")
    public ResponseEntity<String> getSearch(@PathVariable String name){
        ArtistRes ar = new ArtistRes( name, "Łydka Grubasa");
        AlbumRes al = new AlbumRes(name, "O-dur C-ból");
        TrackRes tr =  new TrackRes();
        tr.setId(name);
        tr.setAlbum(al);
        tr.setArtist(ar);
        tr.setDuration_ms(69420L);
        tr.setHref("https://open.spotify.com/track/5YchihSamhn4REnnfW3ESJ");
        tr.setTrack_number(13L);
        tr.setType("Dzika Viksa");


        ArrayList<Object> response = new ArrayList<>();
        response.add(al);
        response.add(ar);
        response.add(tr);

        Gson gson = new Gson();
        String json = gson.toJson(response);
        if(tr.getId().equals("0")){
            json = "Nie znaleziono";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }

    @PostMapping(path = "/favorites")
    public ResponseEntity<String> postFavotires(@RequestParam(name = "id") String id){
        ArrayList<FavoriteRes> response = new ArrayList<>();
        int i = 0;
        int n = Integer.parseInt(id);
        for (i = 0; i < n; i++) {
            FavoriteRes returnValue = new FavoriteRes();
            returnValue.setId(i + 1);
            returnValue.setTrackID("number123number"+i);
            response.add(returnValue);
        }

        Gson gson = new Gson();
        String json = gson.toJson(response);
        if (response.size() < 1) {
            json = "Nie znaleziono utworu";
            return ResponseEntity.status(404).body(json);
        }
        return ResponseEntity.ok(json);
    }


    @PostMapping(path = "/test")
    public void test(){
        favoriteService.addTrack("4fda3443rfasd","dsadas434");
    }


}
