package com.orionitbd.moviebox.moviebox.movie.popular;


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
import com.orionitbd.moviebox.moviebox.movie.toprated.TopRatedMovieAdapter;
import com.orionitbd.moviebox.moviebox.movie.toprated.TopRatedMovieResponse;
import com.orionitbd.moviebox.moviebox.movie.toprated.TopRatedMovieService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieTabPopularFragment extends Fragment {
    private String BASE_URL = Key.BASE_URL;
    private String LANGUAGE = Key.LANGUAGE;
    private String PAGE = Key.PAGE;


    private PopularMovieService service;
    private List<PopularMovieResponse.Result> movieResponseList;
    private PopularMovieAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public MovieTabPopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie_tab_popular, container, false);

        recyclerView = v.findViewById(R.id.popularMovieRV);
        progressBar = v.findViewById(R.id.pbmopularmovie);
        recyclerView.setVisibility(View.GONE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PopularMovieService.class);

        Call<PopularMovieResponse> call = service.getPopularMovie(getString(R.string.API_KEY),LANGUAGE,PAGE);
        call.enqueue(new Callback<PopularMovieResponse>() {
            @Override
            public void onResponse(Call<PopularMovieResponse> call, Response<PopularMovieResponse> response) {
                if(response.code()==200){
                    PopularMovieResponse movieResponse = response.body();
                    movieResponseList = movieResponse.getResults();
                    adapter = new PopularMovieAdapter(getContext(),movieResponseList);
                    GridLayoutManager glm = new GridLayoutManager(getContext(),3);
                    recyclerView.setLayoutManager(glm);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<PopularMovieResponse> call, Throwable t) {
                Log.e("upcoming movie fragment", "fail reason: code:"+t );

            }
        });


        return v;
    }

}
