package com.fj.naufalprakoso.newpopcorntime.entity;

import org.json.JSONObject;

/**
 * Created by NaufalPrakoso on 19/03/18.
 */

public class MovieItems {

    private int id;
    private String judul;
    private String deskripsi;
    private String terbit;
    private String imgMovie;
    private String popularity;
    private String banner;

    public MovieItems(JSONObject object) {
        try{
            this.judul = object.getString("title");
            this.deskripsi = object.getString("overview");
            this.terbit = object.getString("release_date");
            this.popularity = object.getString("vote_average");
            String urlImgMovie = object.getString("poster_path");
            String urlBanner = object.getString("backdrop_path");
            this.imgMovie = "http://image.tmdb.org/t/p/w185"+urlImgMovie;
            this.banner = "http://image.tmdb.org/t/p/w500" + urlBanner;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getJudul(){
        return judul;
    }

    public void setJudul(String judul){
        this.judul = judul;
    }

    public String getDeskripsi(){
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }

    public String getTerbit(){
        return terbit;
    }
    public void setTerbit(String terbit){
        this.terbit = terbit;
    }

    public String getImgMovie(){
        return imgMovie;
    }

    public void setImgMovie(String imgMovie){
        this.imgMovie = imgMovie;
    }
    public String getPopularity () { return popularity; }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
