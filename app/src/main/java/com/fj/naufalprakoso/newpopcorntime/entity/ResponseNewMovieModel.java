package com.fj.naufalprakoso.newpopcorntime.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by NaufalPrakoso on 19/03/18.
 */

public class ResponseNewMovieModel {
    @SerializedName("results")
    private List<NewMovieModel> results;

    public List<NewMovieModel> getResults() {
        return results;
    }

    public void setResults(List<NewMovieModel> results) {
        this.results = results;
    }
}
