package com.mike.gritdemo.interfaces.presenter;

import android.support.annotation.NonNull;

import com.mike.gritdemo.R;
import com.mike.gritdemo.interfaces.HomeActivityContract;
import com.mike.gritdemo.model.PopularMovies;
import com.mike.gritdemo.rest_api.MoviesCallback;
import com.mike.gritdemo.rest_api.service.MoviesDataHelper;

/**
 * Implementation for the Presenter that interacts with the data layer
 */
public class MoviesListPresenter implements HomeActivityContract.HomePresenter {

    public HomeActivityContract.HomeView view;
    private MoviesDataHelper moviesDataHelper;

    /**
     * Have a data helper dependency as a middle layer.
     * Also used to mock the dependency in the test case.
     */
    public MoviesListPresenter(@NonNull MoviesDataHelper dataHelper) {
        this.moviesDataHelper = dataHelper;
    }

    @Override
    public void getPopularMovies() {

        if (view == null) {
            return;
        }

        view.showProgress();
        moviesDataHelper.getMoviesList(new MoviesCallback<PopularMovies>() {

            @Override
            public void onSuccess(PopularMovies popularMovies) {

                view.showToast(R.string.success_message);
                view.hideProgress();
                view.updateRecyclerAdapter(popularMovies);

            }

            @Override
            public void onFailure(String error_message) {
                view.hideProgress();
                view.showErrorToast(R.string.error_message);
            }
        });

    }

    /**
     * Was meant to be for testing the api call with/without the api key or invalid key
     */
    @Override
    public void getPopularMovies(String api_key) {

    }

    @Override
    public void attach(HomeActivityContract.HomeView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
