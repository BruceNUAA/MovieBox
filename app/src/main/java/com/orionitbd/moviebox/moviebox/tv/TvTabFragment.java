package com.orionitbd.moviebox.moviebox.tv;


import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.WelcomeActivity;
import com.orionitbd.moviebox.moviebox.animation.FanTransformation;
import com.orionitbd.moviebox.moviebox.key.Key;
import com.orionitbd.moviebox.moviebox.tv.adapter.OnAirTvAdapter;
import com.orionitbd.moviebox.moviebox.tv.adapter.PopularTvAdapter;
import com.orionitbd.moviebox.moviebox.tv.adapter.TodayTvAdpater;
import com.orionitbd.moviebox.moviebox.tv.adapter.TopRatedTvAdapter;
import com.orionitbd.moviebox.moviebox.tv.others.TvService;
import com.orionitbd.moviebox.moviebox.tv.response.OnAirTvResponse;
import com.orionitbd.moviebox.moviebox.tv.response.PopularTvResponse;
import com.orionitbd.moviebox.moviebox.tv.response.TodayTvResponse;
import com.orionitbd.moviebox.moviebox.tv.response.TopRatedTvResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvTabFragment extends Fragment {


    private String BASE_URL = Key.BASE_URL;
    private String LANGUAGE = Key.LANGUAGE;
    private String PAGE = Key.PAGE;

    //service class
    private TvService service;

    // on air tv show
    private RecyclerView onairShowRV;
    private List<OnAirTvResponse.Result> onairShowList;
    private OnAirTvAdapter onairShowAdapter;

    // today tv show
    private RecyclerView todayShowRV;
    private List<TodayTvResponse.Result> todayShowList;
    private TodayTvAdpater todayShowAdapter;

    // top rated tv show
    private RecyclerView topratedShowRV;
    private List<TopRatedTvResponse.Result> topratedShowList;
    private TopRatedTvAdapter topratedShowAdapter;

    // popular tv show
    private RecyclerView popularShowRV;
    private List<PopularTvResponse.Result> popularShowList;
    private PopularTvAdapter popularShowAdapter;

    //loading animation
    private AnimationDrawable animation;
    private ImageView loading;
    private boolean onairT, todayT , topratedT, popularT;
    private LinearLayout layout;


    public TvTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_tv_tab, container, false);

        //find view by id
        onairShowRV = v.findViewById(R.id.onAirTvRV);
        todayShowRV = v.findViewById(R.id.todayTvRV);
        topratedShowRV = v.findViewById(R.id.topRatedTvRV);
        popularShowRV = v.findViewById(R.id.popularTvRV);

        //recycler view loaded
        getOnairTvShow();
        getTodayTvShow();
        getTopradedTvShow();
        getPopularShow();

        loading = v.findViewById(R.id.loadingtv);
        layout = v.findViewById(R.id.tvLL);

        // loading animation start
        animation = (AnimationDrawable) loading.getDrawable();
        animation.start();

        return v;
    }

    private void getOnairTvShow() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(TvService.class);

        Call<OnAirTvResponse> call = service.getOnAirTvShow (getString(R.string.API_KEY),LANGUAGE,PAGE);
        call.enqueue(new Callback<OnAirTvResponse>() {
            @Override
            public void onResponse(Call<OnAirTvResponse> call, Response<OnAirTvResponse> response) {
                if(response.code()==200){
                    OnAirTvResponse tvResponse = response.body();
                    onairShowList = tvResponse.getResults();
                    onairShowAdapter = new OnAirTvAdapter(getContext(),onairShowList);
                    LinearLayoutManager llm = new LinearLayoutManager(getContext());
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    onairShowRV.setLayoutManager(llm);
                    onairShowRV.setAdapter(onairShowAdapter);
                    onairT=true;
                    checkVisiblity();

                }
            }

            @Override
            public void onFailure(Call<OnAirTvResponse> call, Throwable t) {
                Log.e("upcoming tv fragment", "fail reason: code:"+t );

            }
        });
    }

    private void getTodayTvShow() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(TvService.class);

        Call<TodayTvResponse> call = service.getTodayTvShow (getString(R.string.API_KEY),LANGUAGE,PAGE);
        call.enqueue(new Callback<TodayTvResponse> () {
            @Override
            public void onResponse(Call<TodayTvResponse> call, Response<TodayTvResponse> response) {
                if(response.code()==200){
                    TodayTvResponse tvResponse = response.body();
                    todayShowList = tvResponse.getResults();
                    todayShowAdapter = new TodayTvAdpater (getContext(),todayShowList);
                    LinearLayoutManager llm = new LinearLayoutManager(getContext());
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    todayShowRV.setLayoutManager(llm);
                    todayShowRV.setAdapter(todayShowAdapter);
                    todayT =true;
                    checkVisiblity();
                }
            }

            @Override
            public void onFailure(Call<TodayTvResponse> call, Throwable t) {
                Log.e("upcoming tv fragment", "fail reason: code:"+t );

            }
        });
    }

    private void getTopradedTvShow() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(TvService.class);

        Call<TopRatedTvResponse> call = service.geTopRatedTvShow (getString(R.string.API_KEY),LANGUAGE,PAGE);
        call.enqueue(new Callback<TopRatedTvResponse> () {
            @Override
            public void onResponse(Call<TopRatedTvResponse> call, Response<TopRatedTvResponse> response) {
                if(response.code()==200){
                    TopRatedTvResponse tvResponse = response.body();
                    topratedShowList = tvResponse.getResults();
                    topratedShowAdapter = new TopRatedTvAdapter (getContext(),topratedShowList);
                    LinearLayoutManager llm = new LinearLayoutManager(getContext());
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    topratedShowRV.setLayoutManager(llm);
                    topratedShowRV.setAdapter(topratedShowAdapter);
                    topratedT = true;
                    checkVisiblity();
                }
            }

            @Override
            public void onFailure(Call<TopRatedTvResponse> call, Throwable t) {
                Log.e("upcoming tv fragment", "fail reason: code:"+t );

            }
        });
    }

    private void getPopularShow() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(TvService.class);

        Call<PopularTvResponse> call = service.getPopularTvShow (getString(R.string.API_KEY),LANGUAGE,PAGE);
        call.enqueue(new Callback<PopularTvResponse> () {
            @Override
            public void onResponse(Call<PopularTvResponse> call, Response<PopularTvResponse> response) {
                if(response.code()==200){
                    PopularTvResponse tvResponse = response.body();
                    popularShowList = tvResponse.getResults();
                    popularShowAdapter = new PopularTvAdapter (getContext(),popularShowList);
                    LinearLayoutManager llm = new LinearLayoutManager(getContext());
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    popularShowRV.setLayoutManager(llm);
                    popularShowRV.setAdapter(popularShowAdapter);
                    popularT=true;
                    checkVisiblity();

                }
            }

            @Override
            public void onFailure(Call<PopularTvResponse> call, Throwable t) {
                Log.e("upcoming tv fragment", "fail reason: code:"+t );

            }
        });
    }

    private void checkVisiblity(){
        //check recyler view is loaded or not
        if(onairT == true && todayT == true && topratedT == true && popularT == true){
            animation.stop();
            loading.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        }
        //check internet connection
        WelcomeActivity wc = new WelcomeActivity();
        wc.checkConnection(getContext());
    }





}
