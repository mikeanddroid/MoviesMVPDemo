package com.mike.gritdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Hosting activity for MoviesListFragment
 */
public class MoviesListActivity extends AppCompatActivity {

    private static final String TAG = MoviesListActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_list_activity_view_container);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.list_container, MoviesListFragment.newInstance()).commit();
        }
    }

}
