package com.orionitbd.moviebox.moviebox.movie;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.WelcomeActivity;
import com.orionitbd.moviebox.moviebox.key.Key;
import com.orionitbd.moviebox.moviebox.movie.adapter.NowPlayingMovieAdapter;
import com.orionitbd.moviebox.moviebox.movie.others.MovieService;
import com.orionitbd.moviebox.moviebox.movie.response.NowPlayingMovieResponse;
import com.orionitbd.moviebox.moviebox.movie.adapter.PopularMovieAdapter;
import com.orionitbd.moviebox.moviebox.movie.response.PopularMovieResponse;
import com.orionitbd.moviebox.moviebox.movie.adapter.TopRatedMovieAdapter;
import com.orionitbd.moviebox.moviebox.movie.response.TopRatedMovieResponse;
import com.orionitbd.moviebox.moviebox.movie.adapter.UpcomingMovieAdapter;
import com.orionitbd.moviebox.moviebox.movie.response.UpcomingMovieResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieTabFragment extends Fragment {

    private String BASE_URL = Key.BASE_URL;
    private String LANGUAGE = Key.LANGUAGE;
    private String PAGE = Key.PAGE;

    //service class
    private MovieService service;

    //upcoming movie
    private RecyclerView upComingMovieRV;
    private List<UpcomingMovieResponse.Result> upcomingMovieList;
    private UpcomingMovieAdapter upcomingMovieadapter;

    //top rated movie
    private RecyclerView topRatedMovieRV;
    private List<TopRatedMovieResponse.Result> topRatedMovieList;
    private TopRatedMovieAdapter topRatedMovieAdapter;

    //popular movie
    private RecyclerView popularMovieRV;
    private List<PopularMovieResponse.Result> popularMovieList;
    private PopularMovieAdapter popularMovieAdapter;

    //now playing movie
    private RecyclerView nowPlayingMovieRV;
    private List<NowPlayingMovieResponse.Result> nowPlayingMovieList;
    private NowPlayingMovieAdapter nowPlayingMovieAdapter;


    //loading animation
    private AnimationDrawable animation;
    private ImageView loading;
    private boolean upcomingM, topratedM , popularM, nowplayingM;
    private LinearLayout layout;


    public MovieTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie_tab, container, false);

        //find view by id
        upComingMovieRV = v.findViewById(R.id.upcomingMovieRV);
        topRatedMovieRV = v.findViewById(R.id.topratedMovieRV);
        popularMovieRV = v.findViewById(R.id.popularMovieRV);
        nowPlayingMovieRV = v.findViewById(R.id.nowplayMovieRV);

        loading = v.findViewById(R.id.loading);
        layout = v.findViewById(R.id.movieLL);

        // loading animation start
        animation = (AnimationDrawable) loading.getDrawable();
        animation.start();

        // recycle view loaded
        upComingMovie();
        topRatedMovie();
        popularMovie();
        nowPlayingMovie();

        return v;
    }

    private void upComingMovie(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MovieService.class);

        Call<UpcomingMovieResponse> call = service.getUpcomingMovie(getString(R.string.API_KEY),LANGUAGE,PAGE);
        call.enqueue(new Callback<UpcomingMovieResponse>() {
            @Override
            public void onResponse(Call<UpcomingMovieResponse> call, Response<UpcomingMovieResponse> response) {
                if(response.code()==200){
                    UpcomingMovieResponse movieResponse = response.body();
                    upcomingMovieList = movieResponse.getResults();
                    upcomingMovieadapter = new UpcomingMovieAdapter(getContext(),upcomingMovieList);
                    LinearLayoutManager llm = new LinearLayoutManager(getContext());
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    upComingMovieRV.setLayoutManager(llm);
                    upComingMovieRV.setAdapter(upcomingMovieadapter);
                    upcomingM= true;
                    checkVisiblity();
                }
            }
            @Override
            public void onFailure(Call<UpcomingMovieResponse> call, Throwable t) {
                Log.e("upcoming movie", "fail reason: code:"+t );
            }
        });

    }

    private void topRatedMovie(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MovieService.class);

        Call<TopRatedMovieResponse> call = service.getTopratedMovie(getString(R.string.API_KEY),LANGUAGE,PAGE);
        call.enqueue(new Callback<TopRatedMovieResponse>() {
            @Override
            public void onResponse(Call<TopRatedMovieResponse> call, Response<TopRatedMovieResponse> response) {
                if(response.code()==200){
                    TopRatedMovieResponse movieResponse = response.body();
                    topRatedMovieList = movieResponse.getResults();
                    topRatedMovieAdapter = new TopRatedMovieAdapter(getContext(),topRatedMovieList);
                    LinearLayoutManager llm = new LinearLayoutManager(getContext());
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    topRatedMovieRV.setLayoutManager(llm);
                    topRatedMovieRV.setAdapter(topRatedMovieAdapter);
                    topratedM = true;
                    checkVisiblity();
                }
            }

            @Override
            public void onFailure(Call<TopRatedMovieResponse> call, Throwable t) {
                Log.e("top Rated movie", "fail reason: code:"+t );
            }
        });
    }

    private void popularMovie(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MovieService.class);

        Call<PopularMovieResponse> call = service.getPopularMovie(getString(R.string.API_KEY),LANGUAGE,PAGE);
        call.enqueue(new Callback<PopularMovieResponse>() {
            @Override
            public void onResponse(Call<PopularMovieResponse> call, Response<PopularMovieResponse> response) {
                if(response.code()==200){
                    PopularMovieResponse movieResponse = response.body();
                    popularMovieList = movieResponse.getResults();
                    popularMovieAdapter = new PopularMovieAdapter(getContext(),popularMovieList);
                    LinearLayoutManager llm = new LinearLayoutManager(getContext());
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    popularMovieRV.setLayoutManager(llm);
                    popularMovieRV.setAdapter(popularMovieAdapter);
                    popularM = true;
                    checkVisiblity();
                }
            }

            @Override
            public void onFailure(Call<PopularMovieResponse> call, Throwable t) {
                Log.e("popular movie ", "fail reason: code:"+t );
            }
        });
    }

    private void nowPlayingMovie(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MovieService.class);

        Call<NowPlayingMovieResponse> call = service.getNowplayingMovie(getString(R.string.API_KEY),LANGUAGE,PAGE);
        call.enqueue(new Callback<NowPlayingMovieResponse>() {
            @Override
            public void onResponse(Call<NowPlayingMovieResponse> call, Response<NowPlayingMovieResponse> response) {
                if(response.code()==200){
                    NowPlayingMovieResponse movieResponse = response.body();
                    nowPlayingMovieList = movieResponse.getResults();
                    nowPlayingMovieAdapter = new NowPlayingMovieAdapter(getContext(),nowPlayingMovieList);
                    LinearLayoutManager llm = new LinearLayoutManager(getContext());
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    nowPlayingMovieRV.setLayoutManager(llm);
                    nowPlayingMovieRV.setAdapter(nowPlayingMovieAdapter);
                    nowplayingM= true;
                    checkVisiblity();
                }
            }

            @Override
            public void onFailure(Call<NowPlayingMovieResponse> call, Throwable t) {
                Log.e("now playing movie ", "fail reason: code:"+t );
            }
        });
    }

    private void checkVisiblity(){
        //check recyler view is loaded or not
        if(upcomingM == true && topratedM == true && popularM == true && nowplayingM == true){
            animation.stop();
            loading.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        }
        //check internet connection
        WelcomeActivity wc = new WelcomeActivity();
        wc.checkConnection(getContext());
    }

}
