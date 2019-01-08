package com.mike.gritdemo.model;

import java.util.Arrays;

public class Results {

    private String title;

    private String overview;

    private double vote_average;

    private int vote_count;

    private String backdrop_path;

    private String adult;

    private int id;

    private String original_language;

    private String[] genre_ids;

    private String release_date;

    private String original_title;

    private String poster_path;

    private boolean video;

    private double popularity;

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(String[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public boolean getVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "{" +
                "title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", vote_average=" + vote_average +
                ", vote_count=" + vote_count +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", adult='" + adult + '\'' +
                ", id=" + id +
                ", original_language='" + original_language + '\'' +
                ", genre_ids=" + Arrays.toString(genre_ids) +
                ", release_date='" + release_date + '\'' +
                ", original_title='" + original_title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", video=" + video +
                ", popularity=" + popularity +
                '}';
    }
}
