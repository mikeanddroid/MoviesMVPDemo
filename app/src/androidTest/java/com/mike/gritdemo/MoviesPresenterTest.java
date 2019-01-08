package com.mike.gritdemo;

import android.support.test.runner.AndroidJUnit4;

import com.mike.gritdemo.interfaces.HomeActivityContract;
import com.mike.gritdemo.interfaces.presenter.MoviesListPresenter;
import com.mike.gritdemo.model.PopularMovies;
import com.mike.gritdemo.model.Results;
import com.mike.gritdemo.rest_api.MoviesCallback;
import com.mike.gritdemo.rest_api.service.MoviesDataHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class MoviesPresenterTest {

    @Mock
    private MoviesDataHelper dataHelper;

    @Mock
    private HomeActivityContract.HomeView homeView;

    @Captor
    private ArgumentCaptor<MoviesCallback<PopularMovies>> moviesCallbackArgumentCaptor;

    private MoviesListPresenter presenter;

    /**
     * Init the mocks
     * Setup the presenter with mock data helper
     */
    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        presenter = new MoviesListPresenter(dataHelper);
        presenter.attach(homeView);

    }

    /**
     * Check the api call and the methods invoked from the presenter and the view
     */
    @Test
    public void testGetMovieList() {

        PopularMovies popularMovies = getMockPopularMoviesResults();

        // Make the api call
        presenter.getPopularMovies();

        // Check if the method is invoked
        verify(dataHelper, times(1)).getMoviesList(moviesCallbackArgumentCaptor.capture());

        // Verify the show progress before the api call
        verify(homeView, times(1)).showProgress();

        // Verify the success call with the mock data
        moviesCallbackArgumentCaptor.getValue().onSuccess(popularMovies);

        // Verify the hide progress after the api call
        verify(homeView, times(1)).hideProgress();

        // Mock the rest api
        ArgumentCaptor<PopularMovies> moviesArgumentCaptor = ArgumentCaptor.forClass(PopularMovies.class);

        verify(homeView).updateRecyclerAdapter(moviesArgumentCaptor.capture());

        // Check against the mocked data set and verify
        assertTrue(moviesArgumentCaptor.getValue().getResults().size() == 2);

    }

    /**
     * Check the failure response when hitting the api
     */
    @Test
    public void testError() {

        presenter.getPopularMovies();

        verify(dataHelper, times(1)).getMoviesList(moviesCallbackArgumentCaptor.capture());

        // On failure, show the error message.
        // Although you can use anyString() from mockito
        moviesCallbackArgumentCaptor.getValue().onFailure("Error");

    }

    /**
     * Create a mock object for popular movies
     */
    private PopularMovies getMockPopularMoviesResults() {

        PopularMovies popularMovies = new PopularMovies();
        popularMovies.setPage(1);
        popularMovies.setTotal_pages(1);
        popularMovies.setTotal_results(2);

        List<Results> movieResults = Arrays.asList(new Results(), new Results());
        popularMovies.setResults(movieResults);

        return popularMovies;
    }

    /**
     * Detach the view when test is complete
     */
    @After
    public void tearDown() {
        presenter.detachView();
    }

}
