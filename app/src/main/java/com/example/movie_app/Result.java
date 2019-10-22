package com.example.movie_app;

import java.io.Serializable;

public class Result implements Serializable {

    String Title;
    String poster_path;
    String overview;
    String releasedate;
    String voteaverage;
    String popularity;

    public Result(String poster_path, String title, String overview, String releasedate, String voteaverage,String popularity) {
        this.poster_path = poster_path;
        Title = title;
        this.overview = overview;
        this.releasedate = releasedate;
        this.voteaverage = voteaverage;
        this.popularity=popularity;
    }

    public Result() {
    }
}
