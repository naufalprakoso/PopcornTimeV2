package com.fj.naufalprakoso.newpopcorntime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fj.naufalprakoso.newpopcorntime.R;
import com.fj.naufalprakoso.newpopcorntime.entity.MovieItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by NaufalPrakoso on 19/03/18.
 */

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public MovieAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items){
        mData = items;
        notifyDataSetChanged();
    }

    public void addItems(final MovieItems item){
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null){
            return 0;
        }
        return mData.size();
    }

    @Override
    public MovieItems getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.movie_item, null);
            holder.textViewJudul = (TextView) view.findViewById(R.id.textJudul);
            holder.textViewDeskripsi = (TextView) view.findViewById(R.id.textDesc);
            holder.textViewTerbit = (TextView) view.findViewById(R.id.textTerbit);
            holder.imgMovie = (ImageView) view.findViewById(R.id.img_movie);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.textViewJudul.setText(mData.get(i).getJudul());
        holder.textViewDeskripsi.setText(mData.get(i).getDeskripsi());
        holder.textViewTerbit.setText(mData.get(i).getTerbit());
        Picasso.with(context).load(mData.get(i).getImgMovie()).into(holder.imgMovie);
        return view;
    }

    private static class ViewHolder {
        TextView textViewJudul;
        TextView textViewDeskripsi;
        TextView textViewTerbit;
        ImageView imgMovie, imgBanner;
    }
}
