package com.fj.naufalprakoso.newpopcorntime.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by NaufalPrakoso on 19/03/18.
 */

public class MovieModel extends ArrayList<Parcelable> implements Parcelable {

    private String title;
    private String populariry;
    private String release_date;
    private String overview;
    private String banner;
    private String poster;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPopulariry() {
        return populariry;
    }

    public void setPopulariry(String populariry) {
        this.populariry = populariry;
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

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public static Creator<MovieModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.populariry);
        dest.writeString(this.release_date);
        dest.writeString(this.overview);
        dest.writeString(this.banner);
        dest.writeString(this.poster);
        dest.writeInt(this.id);
    }

    public MovieModel() {
    }

    protected MovieModel(Parcel in) {
        this.title = in.readString();
        this.populariry = in.readString();
        this.release_date = in.readString();
        this.overview = in.readString();
        this.banner = in.readString();
        this.poster = in.readString();
        this.id = in.readInt();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    @Override
    public Stream<Parcelable> stream() {
        return null;
    }

    @Override
    public Stream<Parcelable> parallelStream() {
        return null;
    }
}
