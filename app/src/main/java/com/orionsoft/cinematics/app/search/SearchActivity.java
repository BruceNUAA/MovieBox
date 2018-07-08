package com.orionsoft.cinematics.app.search;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.orionsoft.cinematics.app.R;
import com.orionsoft.cinematics.app.key.Key;
import com.orionsoft.cinematics.app.search.adapter.SearchAdapter;
import com.orionsoft.cinematics.app.search.other.SearchService;
import com.orionsoft.cinematics.app.search.response.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    //search movie
    public SearchService service;
    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private List<SearchResponse.Result> movieResponseList = new ArrayList<>();;


    private String BASE_URL = Key.BASE_URL;
    private String LANGUAGE = Key.LANGUAGE;

    private String query;

    //loading animation
    private AnimationDrawable animation;
    private ImageView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        query = getIntent().getStringExtra("query");

        // set title on actionbar
        getSupportActionBar().setTitle(query);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.searchMovieRV);


        loading = findViewById(R.id.loading);

        // loading animation start
        animation = (AnimationDrawable) loading.getDrawable();
        animation.start();


        //get search movie
        getSearchMovie();
    }

    private void getSearchMovie() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(SearchService.class);
        Call<SearchResponse> call = service.getSearchMovie (getString(R.string.API_KEY),LANGUAGE,"1","true",query);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if(response.code()==200 && response.body().getResults().size() != 0 ){
                    movieResponseList = response.body().getResults();
                    adapter = new SearchAdapter (getApplicationContext(),movieResponseList);
                    GridLayoutManager llm = new GridLayoutManager(getApplicationContext(),3);
                    recyclerView.setLayoutManager(llm);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    animation.stop();
                    loading.setVisibility(View.GONE);

                }
                else {
                    Toast.makeText(SearchActivity.this, "No Result Found ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });
    }


}
