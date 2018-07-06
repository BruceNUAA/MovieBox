package com.orionitbd.moviebox.moviebox.movie;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.TrailerActivity;
import com.orionitbd.moviebox.moviebox.key.Key;

import com.orionitbd.moviebox.moviebox.movie.adapter.CastAdapter;
import com.orionitbd.moviebox.moviebox.movie.adapter.SimilerMovieAdapter;
import com.orionitbd.moviebox.moviebox.movie.others.MovieService;
import com.orionitbd.moviebox.moviebox.movie.response.CastResponse;
import com.orionitbd.moviebox.moviebox.movie.response.MovieTrailerResponse;
import com.orionitbd.moviebox.moviebox.movie.response.SimilerMovieResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailsActivity extends  AppCompatActivity {

    private ImageView bannerIV ;
    private TextView titleTV;
    private TextView tagTV;
    private TextView overviewTV;
    private TextView statusTV;
    private TextView dateTV;
    private TextView budgetTV;
    private TextView runtimeTV;
    private TextView ratingTV;
    private TextView langTV;
    private TextView genersTV;
    private TextView productionTV;
    private TextView homepageTV;

    //similer movie
    private RecyclerView recyclerView;
    private SimilerMovieAdapter adapter;
    private List<SimilerMovieResponse.Result> movieResponseList;

    //service class
    public MovieService service;
    //cast info....
    private RecyclerView castRV;
    private List<CastResponse.Cast>castList;
    private CastAdapter castAdapter;

    private String BASE_URL = Key.BASE_URL;
    private String LANGUAGE = Key.LANGUAGE;


    public List<MovieTrailerResponse.Result>videoResponse = new ArrayList<>();
    public String videoKey;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);

        // get movie details
        final String title = getIntent().getStringExtra("title");
        String tagline = getIntent().getStringExtra("tagline");
        String overview = getIntent().getStringExtra("overview");
        String date = getIntent().getStringExtra("date");
        Long runtime = getIntent().getLongExtra("runtime",0);
        String poster = getIntent().getStringExtra("poster");
        String homepage = getIntent().getStringExtra("homepage");
        String status = getIntent().getStringExtra("status");
        double rating = getIntent().getDoubleExtra("rating",0);
        Long budget = getIntent().getLongExtra("budget",0);
        id = getIntent().getLongExtra("id",0);
        String genere = getIntent().getStringExtra("genere");
        String language = getIntent().getStringExtra("language");
        String production = getIntent().getStringExtra("production");

        // set title on actionbar
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // find view by id
        bannerIV = findViewById(R.id.bigbanner);
        titleTV = findViewById(R.id.detailsTitle);
        tagTV = findViewById(R.id.detailsTag);
        overviewTV = findViewById(R.id.detailsOverview);
        statusTV = findViewById(R.id.detailsStatus);
        dateTV = findViewById(R.id.detailsDate);
        budgetTV = findViewById(R.id.detailsBudget);
        runtimeTV = findViewById(R.id.detailsRuntime);
        ratingTV = findViewById(R.id.detailsRating);
        langTV = findViewById(R.id.detailslang);
        genersTV = findViewById(R.id.detailsGenres);
        productionTV = findViewById(R.id.detailsProduction);
        homepageTV = findViewById(R.id.detailsHomepage);
        recyclerView = findViewById(R.id.similerMovieRV);
        castRV=findViewById (R.id.castRV);

        // set movie details
        Uri posterUri = Uri.parse("http://image.tmdb.org/t/p/w342/"+poster);
        Picasso.get()
                .load(posterUri)
                .into(bannerIV);
        titleTV.setText(title);
        tagTV.setText(tagline);
        overviewTV.setText(overview);
        statusTV.setText(status);
        dateTV.setText(date);
        budgetTV.setText(budget.toString());
        long h = runtime / 60;
        long m = runtime % 60;
        String t = h+"h "+m+"m";
        runtimeTV.setText(t.toString());
        ratingTV.setText(String.valueOf(rating));
        langTV.setText(language);
        genersTV.setText(genere);
        productionTV.setText(production);
        homepageTV.setText(homepage);

        // get movie trailer url key
        getVideoKey();

        //get cast info....
        getCastInfo();
        //get similer movie
        getSimilerMovie();

        // goto trailer activity
        Button btn = findViewById(R.id.playVideo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MovieDetailsActivity.this, TrailerActivity.class);
                intent.putExtra("key",videoKey);
                intent.putExtra("movietitle",title);
                startActivity(intent);
            }
        });

    }

    private void getCastInfo () {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MovieService.class);
        Call<CastResponse> call = service.getCastInfo (id,getString(R.string.API_KEY));
        call.enqueue (new Callback<CastResponse> ( ) {
            @Override
            public void onResponse ( Call<CastResponse> call, Response<CastResponse> response ) {

                if(response.code()==200){
                    CastResponse castResponse = response.body();
                    castList = castResponse.getCast ();
                    castAdapter = new CastAdapter (getApplicationContext(),castList);
                    LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    castRV.setLayoutManager(llm);
                    castRV.setAdapter(castAdapter);
                    castRV.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure ( Call<CastResponse> call, Throwable t ) {

            }
        });

    }


    private void getVideoKey(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MovieService.class);

        Call<MovieTrailerResponse> call = service.getVideoDetails(id,getString(R.string.API_KEY),LANGUAGE);
        call.enqueue(new Callback<MovieTrailerResponse>() {
            @Override
            public void onResponse(Call<MovieTrailerResponse> call, Response<MovieTrailerResponse> response) {
                if(response.code() ==200){
                    videoResponse = response.body().getResults();
                    videoKey= videoResponse.get(0).getKey();
                }
            }

            @Override
            public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {

            }
        });
    }


    private void getSimilerMovie(){

        Retrofit retrofitMovie = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofitMovie.create(MovieService.class);

        long key = id ;

        String p = "1";
        Call<SimilerMovieResponse> callMovie = service.getSimilerMovie(key,getString(R.string.API_KEY),LANGUAGE,p);
        callMovie.enqueue(new Callback<SimilerMovieResponse>() {
            @Override
            public void onResponse(Call<SimilerMovieResponse> call, Response<SimilerMovieResponse> response) {
                if(response.code()==200){
                    SimilerMovieResponse movieResponse = response.body();
                    movieResponseList = movieResponse.getResults();
                    adapter = new SimilerMovieAdapter(getApplicationContext(),movieResponseList);
                    LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerView.setLayoutManager(llm);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<SimilerMovieResponse> call, Throwable t) {

            }
        });
    }






}
