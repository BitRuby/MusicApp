package com.musicapp.serwer.model.response;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/**
 * Klasa modelu playlist
 */
@Document
public class PlaylistRes {
    /** id klucza. */
    private String id;
    /** nazwa playlisty. */
    private String name;
    /** utwory playlisty. */
    private ArrayList<TrackRes> content;
    /** link do obrazu playlisty. */
    private String imgCover;

    public PlaylistRes() {
    }

    public PlaylistRes(String id) {
        this.id = id;
    }

    public String getImgCover() {
        return imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TrackRes> getContent() {
        return content;
    }

    public void setContent(ArrayList<TrackRes> content) {
        this.content = content;
    }
}
