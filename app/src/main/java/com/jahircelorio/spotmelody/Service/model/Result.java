package com.jahircelorio.spotmelody.Service.model;



public class Result {
    private int trackId;
    private String artistName;
    private String trackName;
    private String previewUrl;
    private String artworkUrl100;

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    private String trackViewUrl;
    private  int state;

    private String primaryGenreName;

    public String getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(String collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    private String collectionPrice;

    public Result(int newtrackId , String newArtistName, String newTrackName, String newPreviewUrl, String newArtworkUrl100, String newtrackViewUrl, String newprimaryGenreName, String newcollectionPrice){
        trackId = newtrackId;
        artistName = newArtistName;
        trackName = newTrackName;
        previewUrl = newPreviewUrl;
        artworkUrl100 = newArtworkUrl100;
        trackViewUrl = newtrackViewUrl;
        primaryGenreName = newprimaryGenreName;
        collectionPrice = newcollectionPrice;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }
}
