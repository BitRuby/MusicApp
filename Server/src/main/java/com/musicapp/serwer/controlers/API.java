package com.musicapp.serwer.controlers;

import com.google.gson.Gson;
import com.musicapp.serwer.model.response.*;
import com.musicapp.serwer.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


/**
 * Kontroler zawierający metody realizujące obsługę zadan klienta
 */
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

    /**
     * Endpoint służący do pobierania playlisty o podanym id
     * @param id id playlisty
     * @return Zwraca palyliste.
     */
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

    /**
     * Endpoint służący do pobierania kilku playlist
     * @param n id playlisty
     * @return Zwraca n playlist.
     */
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

    /**
     * Endpoint służący do pobierania wszystkich playlist
     * @return Zwraca wszystkie playlist.
     */
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

    /**
     * Endpoint służący do pobierania n ulubionych utworow
     * @param n ilosc utworow
     * @return Zwraca n ulubionych utworow.
     */
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

    /**
     * Endpoint służący do pobierania wszystkich ulubionych utworow
     * @return Zwraca wszystkie ulubionych utworow.
     */
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

    /**
     * Endpoint służący do pobierania konkretnego utworu
     * @param id id utworu
     * @return Zwraca ulubiony utwor
     */
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
    /**
     * Endpoint służący do pobierania informacji o utworze
     * @param id id utworu
     * @return Zwraca dane utworu
     */
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

    /**
     * Endpoint służący do pobierania informacji o artyscie
     * @param id id artysty
     * @return Zwraca dane artysty
     */
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


    /**
     * Endpoint służący do pobrania rekomendowanego utworu
     * @return Zwraca rekomendowany utwor
     */
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

    /**
     * Endpoint służący do pobierania informacji o albumie
     * @param id id artysty
     * @return Zwraca dane artysty
     */
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

    /**
     * Endpoint służący do pobierania utworow na podstawie nazwy z API
     * @param name nazwa szukanego utworu
     * @return Zwraca utwory o podanej nazwie
     */
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

    /**
     * Endpoint służący dodaniw utworu do ulubionych
     * @param id id utworu
     * @param name nazwa utworu
     * @param img img albumu
     */
    @PostMapping(path = "/addTrack")
    public void addOneTrack(@RequestParam(required = true) String id, @RequestParam(required = true) String name, @RequestParam(required = true) String img){
        favoriteService.addTrack(id,name,img);
    }

    /**
     * Endpoint służący do dodania przykladowych utworow do bazy
     */
    @GetMapping(path = "/addTracks")
    public void addSomeTracks(){
        favoriteService.addTrack("63yesoRJgXT5QALryYFV0X","Boy","https://i.scdn.co/image/ab67616d0000b27399a3a1c380019cdc2ba9b8c2");
        favoriteService.addTrack("2uZVfvOK7MTjBTRICYmpso","Birds (feat. Elisa)","https://i.scdn.co/image/f28b50cc1583b2627331e12d7ca3d1e4cb128875");
        favoriteService.addTrack("3lPr8ghNDBLc2uZovNyLs9","Supermassive Black Hole","https://i.scdn.co/image/ab67616d0000b27328933b808bfb4cbbd0385400");
        favoriteService.addTrack("5JHNg1hxZFT7TDEphhM4wj","Waiting for the End","https://i.scdn.co/image/ab67616d0000b27359211e56a493ac4509457bab");
        favoriteService.addTrack("0bRQiJTHxIXb45d8uwTMlG","Never Go Away","https://i.scdn.co/image/33cfad468a9f3012df179d4d5f31e3af37411e5b");
        favoriteService.addTrack("4xrzG4pw9JN8BJqqjurcPs","Gold","https://i.scdn.co/image/43dbaac0e5a97829636db85d838271e214e762ff");
    }

    /**
     * Endpoint służący do usuniecia wszystkich utworow z bazy
     */
    @GetMapping(path = "/dropAllTracks")
    public void deleteAllTracks(){
        favoriteService.dropAll();
    }

    /**
     * Endpoint służący do dodania przykładowych playlist do bazy
     */
    @GetMapping(path = "/addPlaylists")
    public void addSomePlaylists(){
        playlistService.addPlaylist("3fPG8zDPmtahUJkmdq3OSX");
        playlistService.addPlaylist("37i9dQZF1DX6GJVEkXUD2r");
    }

    /**
     * Endpoint służący do usuniecia wszystkich playlist z bazy
     */
    @GetMapping(path = "/dropAllPlaylists")
    public void deleteAllPlaylists(){
        playlistService.dropAll();
    }





}
