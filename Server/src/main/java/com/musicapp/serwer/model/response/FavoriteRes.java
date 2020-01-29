package com.musicapp.serwer.model.response;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FavoriteRes {
    @Id
    private String id;
    private String trackID;
    private String artistID;

    public FavoriteRes() {
    }


    public FavoriteRes( String trackID, String artistID) {
        this.trackID = trackID;
        this.artistID = artistID;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrackID() {
        return trackID;
    }

    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }
}
