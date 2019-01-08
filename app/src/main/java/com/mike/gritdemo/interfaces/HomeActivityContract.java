package com.mike.gritdemo.interfaces;

import android.support.annotation.StringRes;

import com.mike.gritdemo.model.PopularMovies;

/**
 * A contract holding the presenter and the view
 */
public interface HomeActivityContract {

    /**
     * View action controls
     */
    interface HomeView {

        void showToast(@StringRes int idString);

        void showErrorToast(@StringRes  int error_ID);

        void showProgress();

        void hideProgress();

        /**
         * Update adapter with the available result set
         */
        void updateRecyclerAdapter(PopularMovies popularMovies);

    }

    /**
     * Controlling the actions invoked by views
     */
    interface HomePresenter extends BasePresenter<HomeView> {

        /**
         * Gets list of available popular movies
         */
        void getPopularMovies();

        /**
         * For testing
         */
        void getPopularMovies(String api_key);

    }

}
