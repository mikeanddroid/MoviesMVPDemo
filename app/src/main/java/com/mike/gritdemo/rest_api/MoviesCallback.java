package com.mike.gritdemo.rest_api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Binder for the rest api calls
 */
public abstract class MoviesCallback<T> implements Callback<T> {

    public abstract void onSuccess(T response);

    public abstract void onFailure(String error_message);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (response.isSuccessful()) {
            if (response.body() != null) {
                onSuccess(response.body());
            }
        } else {
            onFailure(response.message());
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure(t.getMessage());
    }
}
