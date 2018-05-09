package com.orionitbd.moviebox.moviebox.movie.nowplaying;


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
import com.orionitbd.moviebox.moviebox.movie.popular.PopularMovieAdapter;
import com.orionitbd.moviebox.moviebox.movie.popular.PopularMovieResponse;
import com.orionitbd.moviebox.moviebox.movie.popular.PopularMovieService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieTabNowplayingFragment extends Fragment {
    private String BASE_URL = Key.BASE_URL;
    private String LANGUAGE = Key.LANGUAGE;
    private String PAGE = Key.PAGE;

    private NowPlayingMovieService service;
    private List<NowPlayingMovieResponse.Result> movieResponseList;
    private NowPlayingMovieAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public MovieTabNowplayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie_tab_nowplaying, container, false);

        recyclerView = v.findViewById(R.id.nowplayMovieRV);
        progressBar = v.findViewById(R.id.pbnowplatingmovie);
        recyclerView.setVisibility(View.GONE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NowPlayingMovieService.class);

        Call<NowPlayingMovieResponse> call = service.getNowplayingMovie(getString(R.string.API_KEY),LANGUAGE,PAGE);
        call.enqueue(new Callback<NowPlayingMovieResponse>() {
            @Override
            public void onResponse(Call<NowPlayingMovieResponse> call, Response<NowPlayingMovieResponse> response) {
                if(response.code()==200){
                    NowPlayingMovieResponse movieResponse = response.body();
                    movieResponseList = movieResponse.getResults();
                    adapter = new NowPlayingMovieAdapter(getContext(),movieResponseList);
                    GridLayoutManager glm = new GridLayoutManager(getContext(),3);
                    recyclerView.setLayoutManager(glm);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<NowPlayingMovieResponse> call, Throwable t) {
                Log.e("upcoming movie fragment", "fail reason: code:"+t );

            }
        });



        return v;
    }

}
