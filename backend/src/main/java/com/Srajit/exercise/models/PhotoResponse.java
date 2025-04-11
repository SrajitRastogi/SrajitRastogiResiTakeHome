package com.Srajit.exercise.models;

public class PhotoResponse {
    private String date;
    private String imageUrl;

    public PhotoResponse(String date, String imageUrl) {
        this.date = date;
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
