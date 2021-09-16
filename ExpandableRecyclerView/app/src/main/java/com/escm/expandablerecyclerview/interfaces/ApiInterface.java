package com.escm.expandablerecyclerview.interfaces;

import com.escm.expandablerecyclerview.models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("movielist.json")
    Call<List<Movie>> getAllMovies();
}