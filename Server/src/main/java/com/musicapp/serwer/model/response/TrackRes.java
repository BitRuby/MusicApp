package com.musicapp.serwer.model.response;

/**
 * Klasa modelu utworu
 */
public class TrackRes {
    /** tytul utworu. */
    private String title;
    /** nazwa walbumu. */
    private AlbumRes album;
    /** nazwa artysty. */
    private ArtistRes artist;
    /** id utworu. */
    private String id;
    /** dlugosc utworu. */
    private Long duration_ms;
    /** link do utworu. */
    private String href;
    /** numer utworu na plycie. */
    private long track_number;
    /** typ. */
    private String type;

    public TrackRes() {
    }

    public TrackRes(AlbumRes album, ArtistRes artist, String id, Long duration_ms, String href, long track_number, String type, String title) {
        this.album = album;
        this.artist = artist;
        this.id = id;
        this.duration_ms = duration_ms;
        this.href = href;
        this.track_number = track_number;
        this.type = type;
        this.title = title;
    }

    public AlbumRes getAlbum() {
        return album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlbum(AlbumRes album) {
        this.album = album;
    }

    public ArtistRes getArtist() {
        return artist;
    }

    public void setArtist(ArtistRes artist) {
        this.artist = artist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(Long duration_ms) {
        this.duration_ms = duration_ms;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public long getTrack_number() {
        return track_number;
    }

    public void setTrack_number(long track_number) {
        this.track_number = track_number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
