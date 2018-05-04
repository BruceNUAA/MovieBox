package com.orionitbd.moviebox.moviebox.movie.upcoming;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orionitbd.moviebox.moviebox.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieTabUpcomingFragment extends Fragment {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String LANGUAGE = "en-US";
    public static final String PAGE = "1";

    private UpcomingMovieService service;
    private List<UpcomingMovieResponse.Result> movieResponseList;
    private UpcomingMovieAdapter adapter;
    private RecyclerView recyclerView;

    public MovieTabUpcomingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie_tab_upcoming, container, false);
        recyclerView = v.findViewById(R.id.upcomingRV);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(UpcomingMovieService.class);

        Call<UpcomingMovieResponse> call = service.getUpcomingMovie(getString(R.string.API_KEY),LANGUAGE,PAGE);
        call.enqueue(new Callback<UpcomingMovieResponse>() {
            @Override
            public void onResponse(Call<UpcomingMovieResponse> call, Response<UpcomingMovieResponse> response) {
                if(response.code()==200){
                 UpcomingMovieResponse movieResponse = response.body();
                    movieResponseList = movieResponse.getResults();
                    adapter = new UpcomingMovieAdapter(getContext(),movieResponseList);
                    GridLayoutManager glm = new GridLayoutManager(getContext(),3);
                    recyclerView.setLayoutManager(glm);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<UpcomingMovieResponse> call, Throwable t) {
                Log.e("upcoming movie fragment", "fail reason: code:"+t );

            }
        });

        return v;
    }


}
