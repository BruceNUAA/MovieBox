package com.orionitbd.moviebox.moviebox.movie.toprated;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.key.Key;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieTabTopratedFragment extends Fragment {

    private String BASE_URL = Key.BASE_URL;
    private String LANGUAGE = Key.LANGUAGE;
    private String PAGE = Key.PAGE;

    private TopRatedMovieService service;
    private List<TopRatedMovieResponse.Result> movieResponseList;
    private TopRatedMovieAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public MovieTabTopratedFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie_tab_toprated, container, false);
        recyclerView = v.findViewById(R.id.topratedMovieRV);
        progressBar = v.findViewById(R.id.pb2);
        recyclerView.setVisibility(View.GONE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(TopRatedMovieService.class);

        Call<TopRatedMovieResponse> call = service.getUpcomingMovie(getString(R.string.API_KEY),LANGUAGE,PAGE);
        call.enqueue(new Callback<TopRatedMovieResponse>() {
            @Override
            public void onResponse(Call<TopRatedMovieResponse> call, Response<TopRatedMovieResponse> response) {
                if(response.code()==200){
                    TopRatedMovieResponse movieResponse = response.body();
                    movieResponseList = movieResponse.getResults();
                    adapter = new TopRatedMovieAdapter(getContext(),movieResponseList);
                    GridLayoutManager glm = new GridLayoutManager(getContext(),3);
                    recyclerView.setLayoutManager(glm);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<TopRatedMovieResponse> call, Throwable t) {
                Log.e("upcoming movie fragment", "fail reason: code:"+t );

            }
        });

        return v;
    }




}
