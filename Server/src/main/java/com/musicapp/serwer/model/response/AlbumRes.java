package com.musicapp.serwer.model.response;

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

    public AlbumRes() {
    }


    public AlbumRes(String id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
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
