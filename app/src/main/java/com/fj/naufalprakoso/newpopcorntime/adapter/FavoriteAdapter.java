package com.fj.naufalprakoso.newpopcorntime.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fj.naufalprakoso.newpopcorntime.R;
import com.fj.naufalprakoso.newpopcorntime.activity.MovieItemActivity;
import com.fj.naufalprakoso.newpopcorntime.entity.Favorite;
import com.fj.naufalprakoso.newpopcorntime.entity.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

/**
 * Created by NaufalPrakoso on 19/03/18.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private LinkedList<Favorite> listFavorites;
    private Activity activity;
    private Context context;

    public FavoriteAdapter(Context context) {
        this.context = context;
    }

    public LinkedList<Favorite> getListFavorite() {
        return listFavorites;
    }

    public void setListFavorite(LinkedList<Favorite> listFavorites) {
        this.listFavorites = listFavorites;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_now_playing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvJudul.setText(listFavorites.get(position).getTitle());
        holder.tvDesc.setText(listFavorites.get(position).getOverview());
        Picasso.with(context).load(listFavorites.get(position).getPoster()).into(holder.imgMovie);
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindahActivity(position, v);
            }
        });
    }

    private void pindahActivity(int position, View v) {
        MovieModel mMovie = new MovieModel();

        mMovie.setId(listFavorites.get(position).getId());
        mMovie.setTitle(listFavorites.get(position).getTitle());
        mMovie.setOverview(listFavorites.get(position).getOverview());
        mMovie.setPopulariry(listFavorites.get(position).getPopulariry());
        mMovie.setRelease_date(listFavorites.get(position).getRelease_date());
        mMovie.setBanner(listFavorites.get(position).getBanner());
        mMovie.setPoster(listFavorites.get(position).getPoster());
        Intent intent = new Intent(v.getContext(), MovieItemActivity.class);
        intent.putExtra(MovieItemActivity.EXTRA_MOVIE, (Parcelable) mMovie);
        intent.putExtra(MovieItemActivity.IS_FAVORITE, 1);
        v.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return getListFavorite().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvDesc, tvTerbit;
        ImageView imgMovie;
        Button btnDetail;
        public ViewHolder(View itemView) {
            super(itemView);
            tvJudul = (TextView)itemView.findViewById(R.id.np_text_judul);
            tvDesc = (TextView)itemView.findViewById(R.id.np_text_desc);
            imgMovie = (ImageView)itemView.findViewById(R.id.np_img_movie);
            btnDetail = (Button) itemView.findViewById(R.id.btn_detail);
        }
    }
}
