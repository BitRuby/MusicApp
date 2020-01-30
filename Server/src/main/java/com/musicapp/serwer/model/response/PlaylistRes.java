package com.musicapp.serwer.model.response;

import java.util.ArrayList;

public class PlaylistRes {
    private String id;
    private String name;
    private ArrayList<TrackRes> content;
    private String imgCover;

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
