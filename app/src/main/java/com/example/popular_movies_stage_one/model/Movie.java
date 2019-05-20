package com.example.popular_movies_stage_one.model;

import java.io.Serializable;

public class Movie implements Serializable {

    private String title;
    private String poster;
    private String release;
    private String rate;
    private String overview;

    public Movie(String title, String poster, String release, String rate, String overview) {
        this.title = title;
        this.poster = poster;
        this.release = release;
        this.rate = rate;
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
