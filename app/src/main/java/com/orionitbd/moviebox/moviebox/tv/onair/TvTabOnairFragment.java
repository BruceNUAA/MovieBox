package com.orionitbd.moviebox.moviebox.tv.onair;


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


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvTabOnairFragment extends Fragment {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String LANGUAGE = "en-US";
    public static final String PAGE = "1";

    private OnAirTvService service;
    private List<OnAirTvResponse.Result> tvResponseList;
    private OnAirTvAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public TvTabOnairFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tv_tab_onair, container, false);
        recyclerView = v.findViewById(R.id.onAirTvRV);
        progressBar = v.findViewById(R.id.pb10);
        recyclerView.setVisibility(View.GONE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(OnAirTvService.class);

        Call<OnAirTvResponse> call = service.getOnAirTv (getString(R.string.API_KEY),LANGUAGE,PAGE);
        call.enqueue(new Callback<OnAirTvResponse> () {
            @Override
            public void onResponse(Call<OnAirTvResponse> call, Response<OnAirTvResponse> response) {
                if(response.code()==200){
                    OnAirTvResponse tvResponse = response.body();
                    tvResponseList = tvResponse.getResults();
                    adapter = new OnAirTvAdapter(getContext(),tvResponseList);
                    GridLayoutManager glm = new GridLayoutManager(getContext(),3);
                    recyclerView.setLayoutManager(glm);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<OnAirTvResponse> call, Throwable t) {
                Log.e("upcoming tv fragment", "fail reason: code:"+t );

            }
        });

        return v;
    }


}
