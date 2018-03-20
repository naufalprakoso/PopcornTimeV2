package com.fj.naufalprakoso.newpopcorntime.service;

import com.fj.naufalprakoso.newpopcorntime.entity.ResponseNewMovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by NaufalPrakoso on 19/03/18.
 */

public interface ApiInterface {
    @GET("movie/now_playing")
    Call<ResponseNewMovieModel> getNowPlayingMovie(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<ResponseNewMovieModel> getUpcomingMovie(@Query("api_key") String apiKey);
}
