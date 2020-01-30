package com.musicapp.serwer.model.response;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Klasa modelu ulubionych utworow
 */
@Document
public class FavoriteRes {
    /** id klucza. */
    @Id
    private String id;
    /** id utworu. */
    private String trackID;
    /** nazwa utworu. */
    private String name;
    /** img utworu. */
    private String imgCover;
//    private String artistID;

    public FavoriteRes() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgCover() {
        return imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    public FavoriteRes(String trackID, String name, String imgCover) {
        this.trackID = trackID;
        this.name = name;
        this.imgCover = imgCover;
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
