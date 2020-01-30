package com.musicapp.serwer.model.response;

import java.util.ArrayList;

/**
 * Klasa modelu wyszukiwania
 */
public class SearchRes {
    /** lista artyswo. */
    private ArrayList<ArtistRes> artistList;
    /** lista albumow. */
    private ArrayList<AlbumRes> albumList;
    /** lista utworow. */
    private ArrayList<TrackRes> trackList;

    public SearchRes(ArrayList<ArtistRes> artistList, ArrayList<AlbumRes> albumList, ArrayList<TrackRes> trackList) {
        this.artistList = artistList;
        this.albumList = albumList;
        this.trackList = trackList;
    }

    public SearchRes(ArrayList<TrackRes> trackList) {
        this.trackList = trackList;
    }

    public ArrayList<ArtistRes> getArtistList() {
        return artistList;
    }

    public void setArtistList(ArrayList<ArtistRes> artistList) {
        this.artistList = artistList;
    }

    public ArrayList<AlbumRes> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(ArrayList<AlbumRes> albumList) {
        this.albumList = albumList;
    }

    public ArrayList<TrackRes> getTrackList() {
        return trackList;
    }

    public void setTrackList(ArrayList<TrackRes> trackList) {
        this.trackList = trackList;
    }
}
