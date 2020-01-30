package com.musicapp.serwer.model.response;

/**
 * Klasa modelu Artysty
 */
public class ArtistRes {
    /** id artysty. */
    private String id;
    /** nazwa albumu. */
    private String name;

    public ArtistRes() {
    }

    public ArtistRes(String id, String name) {
        this.id = id;
        this.name = name;
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
