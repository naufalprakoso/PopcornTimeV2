package com.fj.naufalprakoso.newpopcorntime.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fj.naufalprakoso.newpopcorntime.R;
import com.fj.naufalprakoso.newpopcorntime.adapter.UpComingAdapter;
import com.fj.naufalprakoso.newpopcorntime.entity.NewMovieModel;
import com.fj.naufalprakoso.newpopcorntime.entity.ResponseNewMovieModel;
import com.fj.naufalprakoso.newpopcorntime.service.ApiInterface;
import com.fj.naufalprakoso.newpopcorntime.service.ApiService;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingFragment extends Fragment {

    private final static String api_key = "5bc5c506bf3ad45dd420e17ebb8486cf";
    private final static String SAVE_DATA_UPCOMING = "save_data_upcoming";

    RecyclerView UpComingRecyclerView;
    List<NewMovieModel> movieModels;

    public UpComingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);
        UpComingRecyclerView = (RecyclerView) view.findViewById(R.id.rv_up_coming);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UpComingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpComingRecyclerView.setHasFixedSize(true);
        if (savedInstanceState != null) {
            movieModels = (List<NewMovieModel>) savedInstanceState.getSerializable(SAVE_DATA_UPCOMING);
            UpComingRecyclerView.setAdapter(new UpComingAdapter(movieModels, getActivity()));
        }else {
            getRequest();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVE_DATA_UPCOMING, (Serializable) movieModels);
    }

    private void getRequest(){
        ApiInterface apiInterface = ApiService.getClient().create(ApiInterface.class);
        Call<ResponseNewMovieModel> call1 = apiInterface.getUpcomingMovie(api_key);

        call1.enqueue(new Callback<ResponseNewMovieModel>() {
            @Override
            public void onResponse(Call<ResponseNewMovieModel> call, Response<ResponseNewMovieModel> response) {
                movieModels = response.body().getResults();
                UpComingRecyclerView.setAdapter(new UpComingAdapter(movieModels, getActivity()));
            }

            @Override
            public void onFailure(Call<ResponseNewMovieModel> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
