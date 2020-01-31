package com.musicapp.serwer.model.response;

import java.util.ArrayList;

/**
 * Klasa modelu Albumu
 */
public class AlbumRes {
    /** id albumu. */
    private String id;
    /** nazwa albumu. */
    private String name;
    /** obraz albumu. */
    private String img;
    /** utwory albumu. */
    private ArrayList<TrackRes> content;

    public AlbumRes() {
    }

    public AlbumRes(String id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public AlbumRes(String id, String name, String img, ArrayList<TrackRes> content) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.content = content;
    }

    public ArrayList<TrackRes> getContent() {
        return content;
    }

    public void setContent(ArrayList<TrackRes> content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
}
