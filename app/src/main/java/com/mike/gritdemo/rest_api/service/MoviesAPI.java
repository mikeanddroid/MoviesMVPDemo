package com.mike.gritdemo.rest_api.service;

import com.mike.gritdemo.model.PopularMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Interface for interacting with the service
 */
public interface MoviesAPI {

    @GET("discover/movie?sort_by=popularity.desc")
    Call<PopularMovies> getPopularMoviesList(@Query("api_key") String apiKey);

}
