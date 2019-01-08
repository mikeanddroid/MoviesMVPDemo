package com.mike.gritdemo.interfaces;

/**
 * Responsible for attaching and detaching resources for presenter
 */
public interface BasePresenter<T> {

    // Attaches the view to its presenter
    void attach(T view);

    // Invoked when the view is destroyed and no longer visible to the user
    void detachView();

}
