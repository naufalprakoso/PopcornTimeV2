package com.fj.naufalprakoso.newpopcorntime.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NaufalPrakoso on 19/03/18.
 */

public class Favorite implements Parcelable {

    private int id;
    private String title;
    private String populariry;
    private String release_date;
    private String overview;
    private String banner;
    private String poster;

    public Favorite(Parcel in) {
        title = in.readString();
        populariry = in.readString();
        release_date = in.readString();
        overview = in.readString();
        banner = in.readString();
        poster = in.readString();
        id= in.readInt();
    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };

    public Favorite() {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Creator<Favorite> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(populariry);
        dest.writeString(release_date);
        dest.writeString(overview);
        dest.writeString(banner);
        dest.writeString(poster);
        dest.writeInt(id);
    }
}
