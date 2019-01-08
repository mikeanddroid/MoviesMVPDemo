package com.mike.gritdemo.rest_api.service;

import com.mike.gritdemo.apputils.GritUtils;
import com.mike.gritdemo.model.PopularMovies;
import com.mike.gritdemo.rest_api.MoviesCallback;
import com.mike.gritdemo.rest_api.RetrofitAPI;

/**
 * Medium for data exchange to presenter
 */
public class MoviesDataHelper {

    private static MoviesDataHelper moviesDataHelper;

    private final MoviesAPI moviesAPI;

    public static MoviesDataHelper getInstance() {

        if (moviesDataHelper == null) {
            moviesDataHelper = new MoviesDataHelper();
        }

        return moviesDataHelper;
    }

    private MoviesDataHelper() {
        moviesAPI = RetrofitAPI.getRetrofitInstance().getMoviesAPI();
    }

    public void getMoviesList(MoviesCallback<PopularMovies> popularMoviesMoviesCallback) {
        moviesAPI.getPopularMoviesList(GritUtils.API_KEY).enqueue(popularMoviesMoviesCallback);
    }

}
