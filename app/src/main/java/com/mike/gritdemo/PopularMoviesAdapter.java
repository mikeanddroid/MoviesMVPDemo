package com.mike.gritdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mike.gritdemo.apputils.GritUtils;
import com.mike.gritdemo.interfaces.RecyclerItemListener;
import com.mike.gritdemo.model.PopularMovies;
import com.mike.gritdemo.model.Results;
import com.squareup.picasso.Picasso;

public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.MoviesHolder> {

    private static final String TAG = PopularMoviesAdapter.class.getSimpleName();
    private PopularMovies popularMovies;
    private RecyclerItemListener recyclerItemListener;

    public PopularMoviesAdapter(PopularMovies popularMovies) {
        this.popularMovies = popularMovies;
    }

    public void setRecyclerItemListener(RecyclerItemListener recyclerItemListener) {
        this.recyclerItemListener = recyclerItemListener;
    }

    @NonNull
    @Override
    public MoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list_item, parent, false);
        return new MoviesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesHolder holder, int position) {

        String movieTitle = popularMovies.getResults().get(position).getTitle();
        String movieDesc = popularMovies.getResults().get(position).getOverview();
        String movieRating = String.valueOf(popularMovies.getResults().get(position).getVote_average());
        String movieRelease = popularMovies.getResults().get(position).getRelease_date();
        String movieImageUrl = buildImageUrl(position);
        final Results results = popularMovies.getResults().get(position);

        Log.d(TAG, "Movie Image Url : " + movieImageUrl);

        Picasso.get().load(movieImageUrl).into(holder.moviesImg);
        holder.movieTitle.setText(movieTitle);
        holder.movieDesc.setText(movieDesc);
        holder.movieRating.setText(movieRating);
        holder.movieRelease.setText(movieRelease);

        holder.show_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerItemListener.onItemClick(results);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularMovies.getResults().size();
    }

    /**
     * Holder representing different views
     */
    static class MoviesHolder extends RecyclerView.ViewHolder {

        ImageView moviesImg;
        TextView movieTitle, movieDesc, movieRating, movieRelease;
        Button show_details;

        public MoviesHolder(View itemView) {
            super(itemView);
            moviesImg = (ImageView) itemView.findViewById(R.id.movie_image);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            movieDesc = (TextView) itemView.findViewById(R.id.movie_desc);
            movieRating = (TextView) itemView.findViewById(R.id.movie_rating);
            movieRelease = (TextView) itemView.findViewById(R.id.movie_release_yr);
            show_details = (Button) itemView.findViewById(R.id.movie_details_btn);
        }
    }

    private String buildImageUrl(int position) {

        StringBuilder stringBuilder = new StringBuilder(GritUtils.IMAGE_BASE_URL);
        String img_url = popularMovies.getResults().get(position).getPoster_path();
        String poster_path = img_url.substring(1, img_url.length());
        stringBuilder.append(poster_path);
        return stringBuilder.toString();

    }

}
