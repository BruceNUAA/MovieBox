package com.orionitbd.moviebox.moviebox.movie;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.key.Key;

import com.orionitbd.moviebox.moviebox.movie.upcoming.UpcomingMovieResponse;
import com.orionitbd.moviebox.moviebox.movie.video.VideoActivity;
import com.orionitbd.moviebox.moviebox.movie.video.VideoResponse;
import com.orionitbd.moviebox.moviebox.movie.video.VideoService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsMovieActivity extends  AppCompatActivity {
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

    private String BASE_URL = Key.BASE_URL;
    private String LANGUAGE = Key.LANGUAGE;

    public VideoService service;
    public List<VideoResponse.Result>videoResponse = new ArrayList<>();
    public String videoKey;


    private Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        String title = getIntent().getStringExtra("title");
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

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        runtimeTV.setText(runtime.toString());
        ratingTV.setText(String.valueOf(rating));
        langTV.setText(language);
        genersTV.setText(genere);
        productionTV.setText(production);
        homepageTV.setText(homepage);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(VideoService.class);

        Call<VideoResponse> call = service.getDetails(id,getString(R.string.API_KEY),LANGUAGE);
        call.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if(response.code() ==200){
                    videoResponse = response.body().getResults();
                    videoKey= videoResponse.get(0).getKey();
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {

            }
        });
        Button btn = findViewById(R.id.playVideo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsMovieActivity.this,VideoActivity.class);
                intent.putExtra("video_key",videoKey);
                startActivity(intent);
            }
        });



    }






}
