package com.musicapp.serwer.model.response;

public class FavoriteRes {
    private long id;
    private String trackID;

    public FavoriteRes() {
    }

    public FavoriteRes(long id, String trackID) {
        this.id = id;
        this.trackID = trackID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrackID() {
        return trackID;
    }

    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }
}
