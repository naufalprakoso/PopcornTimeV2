package com.fj.naufalprakoso.newpopcorntime.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NaufalPrakoso on 19/03/18.
 */

public class NewMovieModel implements Parcelable{

    @SerializedName("title")
    private String title;
    @SerializedName("vote_average")
    private String popularity;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("backdrop_path")
    private String banner;

    protected NewMovieModel(Parcel in) {
        title = in.readString();
        popularity = in.readString();
        release_date = in.readString();
        overview = in.readString();
        poster = in.readString();
        banner = in.readString();
    }

    public static final Creator<NewMovieModel> CREATOR = new Creator<NewMovieModel>() {
        @Override
        public NewMovieModel createFromParcel(Parcel in) {
            return new NewMovieModel(in);
        }

        @Override
        public NewMovieModel[] newArray(int size) {
            return new NewMovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return "http://image.tmdb.org/t/p/w185" + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBanner() {
        return "http://image.tmdb.org/t/p/w500" + banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(popularity);
        dest.writeString(release_date);
        dest.writeString(overview);
        dest.writeString(poster);
        dest.writeString(banner);
    }
}
