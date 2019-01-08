package com.mike.gritdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mike.gritdemo.interfaces.HomeActivityContract;
import com.mike.gritdemo.interfaces.RecyclerItemListener;
import com.mike.gritdemo.interfaces.presenter.MoviesListPresenter;
import com.mike.gritdemo.model.PopularMovies;
import com.mike.gritdemo.model.Results;
import com.mike.gritdemo.rest_api.service.MoviesDataHelper;

public class MoviesListFragment extends Fragment implements HomeActivityContract.HomeView, RecyclerItemListener {

    private static final String TAG = MoviesListFragment.class.getSimpleName();

    private AppCompatActivity activityContext;

    private MoviesListPresenter presenter;
    private RecyclerView moviesRecyclerView;
    private ProgressBar progressBar;

    public MoviesListFragment() {
    }

    public static MoviesListFragment newInstance() {
        return newInstance(null);
    }

    public static MoviesListFragment newInstance(@Nullable Bundle args) {

        MoviesListFragment moviesListFragment = new MoviesListFragment();
        if (args != null) {
            moviesListFragment.setArguments(args);
        }

        return moviesListFragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        // Instantiate the presenter
        presenter = new MoviesListPresenter(MoviesDataHelper.getInstance());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movies_list_fragment_view, container, false);

        // Initialize views
        initViews(view);

        // Attach current view to the presenter
        presenter.attach(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Make the api call when the activity is created and visible
        presenter.getPopularMovies();
    }

    // Init/Prepare views for fragment
    private void initViews(View view) {

        activityContext = (AppCompatActivity) getActivity();
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        moviesRecyclerView = (RecyclerView) view.findViewById(R.id.movies_recycler_view);
        moviesRecyclerView.setHasFixedSize(true);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(activityContext));

    }

    @Override
    public void showToast(int idString) {
        Toast.makeText(activityContext, idString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorToast(int error_ID) {
        Toast.makeText(activityContext, error_ID, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateRecyclerAdapter(PopularMovies popularMovies) {

        // On API success, update the list adapter with the results

        moviesRecyclerView.setVisibility(View.VISIBLE);
        for (Results results : popularMovies.getResults()) {
            Log.d(TAG, "Results : Title : " + results.getTitle());
        }

        PopularMoviesAdapter popularMoviesAdapter = new PopularMoviesAdapter(popularMovies);
        popularMoviesAdapter.setRecyclerItemListener(this);
        moviesRecyclerView.setAdapter(popularMoviesAdapter);

    }

    @Override
    public void onItemClick(Results results) {
        // Todo Navigate to the movie details.

        // Show toast when interacted the more button in the list
        Toast.makeText(activityContext, results.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {

        // Detach the view when fragment is no longer visible
        presenter.detachView();
        super.onDestroy();
    }
}
