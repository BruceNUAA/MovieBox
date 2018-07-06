package com.orionitbd.moviebox.moviebox.tv;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.TrailerActivity;
import com.orionitbd.moviebox.moviebox.key.Key;
import com.orionitbd.moviebox.moviebox.movie.adapter.CastAdapter;
import com.orionitbd.moviebox.moviebox.movie.others.MovieService;
import com.orionitbd.moviebox.moviebox.movie.response.CastResponse;
import com.orionitbd.moviebox.moviebox.movie.response.MovieTrailerResponse;
import com.orionitbd.moviebox.moviebox.tv.adapter.SimilerTvAdapter;
import com.orionitbd.moviebox.moviebox.tv.adapter.TvCastAdapter;
import com.orionitbd.moviebox.moviebox.tv.others.TvService;
import com.orionitbd.moviebox.moviebox.tv.response.SimilerTvResponse;
import com.orionitbd.moviebox.moviebox.tv.response.TVTrailerResponse;
import com.orionitbd.moviebox.moviebox.tv.response.TvCastResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvDetailsActivity extends AppCompatActivity {

    private ImageView bannerIV ;
    private TextView titleTV;
    private TextView overviewTV;
    private TextView firstDateTV;
    private TextView lastDateTV;
    private TextView statusTV;
    private TextView langTV;
    private TextView epesodeTV;
    private TextView seasonTV;
    private TextView homeTV;
    private TextView ratingTV;


    //similer movie
    private RecyclerView recyclerView;
    private SimilerTvAdapter adapter;
    private List<SimilerTvResponse.Result> movieResponseList;

    //service class
    public TvService service;

    //cast info....
    private RecyclerView castRV;
    private List<TvCastResponse.Cast>castList;
    private TvCastAdapter tvCastAdapter;

    private String BASE_URL = Key.BASE_URL;
    private String LANGUAGE = Key.LANGUAGE;

    public List<TVTrailerResponse.Result> videoResponse = new ArrayList<>();
    public String videoKey;
    private Long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_tv);

        //get tv details
        final String title = getIntent().getStringExtra("title");
        String overview = getIntent().getStringExtra("overview");
        String first_date = getIntent().getStringExtra("first_date");
        String last_date = getIntent().getStringExtra("last_date");
        String status = getIntent().getStringExtra("status");
        String poster = getIntent().getStringExtra("poster");
        String original_language = getIntent().getStringExtra("original_language");
        Long number_of_episodes = getIntent().getLongExtra("number_of_episodes",0);
        Long number_of_seasons = getIntent().getLongExtra("number_of_seasons",0);
        String homepage = getIntent().getStringExtra("homepage");
        Double rating = getIntent().getDoubleExtra("rating",0);
        id = getIntent().getLongExtra("id",0);

        //set action bar tite
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //find view by id
       bannerIV = findViewById(R.id.bigbannertv) ;
       titleTV = findViewById(R.id.detailsTitletv) ;
       overviewTV = findViewById(R.id.detailsOverviewtv) ;
       firstDateTV = findViewById(R.id.detailFirstAirtv) ;
       lastDateTV = findViewById(R.id.detailLastAirtv) ;
       statusTV = findViewById(R.id.detailstatustv) ;
       langTV = findViewById(R.id.detailslangtv) ;
       epesodeTV = findViewById(R.id.detailepisodetv) ;
       seasonTV = findViewById(R.id.detailseasontv) ;
       homeTV = findViewById(R.id.detailhometv) ;
       ratingTV = findViewById(R.id.detailsratingtv) ;
       recyclerView = findViewById(R.id.similerTVRV);
       castRV=findViewById (R.id.castRVTV);


       //set tv details
        Uri posterUri = Uri.parse("http://image.tmdb.org/t/p/w342/"+poster);
        Picasso.get()
                .load(posterUri)
                .into(bannerIV);
        titleTV.setText(title);
        overviewTV.setText(overview);
        firstDateTV.setText(first_date);
        lastDateTV.setText(last_date);
        statusTV.setText(status);
        langTV.setText(original_language);
        epesodeTV.setText(number_of_episodes.toString());
        seasonTV.setText(number_of_seasons.toString());
        homeTV.setText(homepage);
        ratingTV.setText("rating "+String.valueOf(rating));

        // get movie trailer url key
        getVideoKey();

        //get cast info....

        getTvCastInfo();

        //get similer movie
        getSimilerTvShow();


        // goto trailer activity
        Button btn = findViewById(R.id.playVideotv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(videoKey != null){
                    Intent intent = new Intent(TvDetailsActivity.this, TrailerActivity.class);
                    intent.putExtra("key",videoKey);
                    intent.putExtra("movietitle",title);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(TvDetailsActivity.this, "video not avaliable", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void getTvCastInfo () {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(TvService.class);
        Call<TvCastResponse> call = service.getTvCastInfo (id,getString(R.string.API_KEY),LANGUAGE);
        call.enqueue (new Callback<TvCastResponse> ( ) {
            @Override
            public void onResponse ( Call<TvCastResponse> call, Response<TvCastResponse> response ) {
                //Toast.makeText (TvDetailsActivity.this,"Response" + response.code (),Toast.LENGTH_SHORT).show ();
                if(response.code()==200){
                    TvCastResponse tvCastResponse=response.body ();
                    castList=tvCastResponse.getCast ();
                    tvCastAdapter=new TvCastAdapter (getApplicationContext (),castList);
                    LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    castRV.setLayoutManager(llm);
                    castRV.setAdapter (tvCastAdapter);
                    castRV.setVisibility (View.VISIBLE);
                }
            }

            @Override
            public void onFailure ( Call<TvCastResponse> call, Throwable t ) {

            }
        });
    }


    private void getSimilerTvShow() {
        Retrofit retrofitMovie = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofitMovie.create(TvService.class);

        long key = id ;

        String p = "1";

        Call<SimilerTvResponse> call = service.getSimilerTvShow(key,getString(R.string.API_KEY),LANGUAGE,p);

        call.enqueue(new Callback<SimilerTvResponse>() {
            @Override
            public void onResponse(Call<SimilerTvResponse> call, Response<SimilerTvResponse> response) {
                if(response.code()==200){
                    SimilerTvResponse movieResponse = response.body();
                    movieResponseList = movieResponse.getResults();
                    adapter = new SimilerTvAdapter(getApplicationContext(),movieResponseList);
                    LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerView.setLayoutManager(llm);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);

                }

                }

            @Override
            public void onFailure(Call<SimilerTvResponse> call, Throwable t) {

            }
        });

    }

    private void getVideoKey() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(TvService.class);
        Call<TVTrailerResponse> call = service.getVideoDetails(id,getString(R.string.API_KEY),LANGUAGE);
        call.enqueue(new Callback<TVTrailerResponse>() {
            @Override
            public void onResponse(Call<TVTrailerResponse> call, Response<TVTrailerResponse> response) {
                if(response.code() ==200){
                    videoResponse = response.body().getResults();
                    if(videoResponse.size() == 0){
                        videoKey = null;
                    }
                    else{
                        videoKey= videoResponse.get(0).getKey();
                    }

                }
            }

            @Override
            public void onFailure(Call<TVTrailerResponse> call, Throwable t) {

            }
        });
    }


}
