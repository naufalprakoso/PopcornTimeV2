package com.fj.naufalprakoso.newpopcorntime.activity;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fj.naufalprakoso.newpopcorntime.R;
import com.fj.naufalprakoso.newpopcorntime.adapter.MovieAdapter;
import com.fj.naufalprakoso.newpopcorntime.entity.MovieItems;
import com.fj.naufalprakoso.newpopcorntime.entity.MovieModel;
import com.fj.naufalprakoso.newpopcorntime.service.MyAsyncTaskLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>, AdapterView.OnItemClickListener{

    ListView listView;
    private MovieAdapter adapter;
    EditText editMovie;
    Button btnCari;
    private String url;

    MovieModel mMovie = new MovieModel();

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        editMovie = (EditText) findViewById(R.id.edit_movie);
        btnCari = (Button) findViewById(R.id.btn_cari);

        btnCari.setOnClickListener(myListener);

        String query = editMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, query);

        getLoaderManager().initLoader(0, bundle, this);
        getSupportActionBar().setTitle(getResources().getString(R.string.search));
    }

    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int i, Bundle bundle) {
       String mQuery ="";
       if (bundle != null){
           mQuery = bundle.getString(EXTRAS_MOVIE);
           url = "https://api.themoviedb.org/3/search/movie?api_key=5bc5c506bf3ad45dd420e17ebb8486cf&language=en-US&query="+mQuery;
       }
       return new MyAsyncTaskLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> movieItems) {
        adapter.setData(movieItems);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String query = editMovie.getText().toString();

            if (TextUtils.isEmpty(query))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, query);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MovieItems movieItems = adapter.getItem(i);

        mMovie.setTitle(movieItems.getJudul());
        mMovie.setRelease_date(movieItems.getTerbit());
        mMovie.setOverview(movieItems.getDeskripsi());
        mMovie.setPopulariry(movieItems.getPopularity());
        mMovie.setPoster(movieItems.getImgMovie());
        mMovie.setBanner(movieItems.getBanner());
        Intent intent = new Intent(this, MovieItemActivity.class);
        intent.putExtra(MovieItemActivity.EXTRA_MOVIE, (Parcelable) mMovie);
        startActivity(intent);
    }
}
