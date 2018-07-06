package com.orionitbd.moviebox.moviebox.search;

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

import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.key.Key;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private String BASE_URL = Key.BASE_URL;
    private String LANGUAGE = Key.LANGUAGE;
    private String PAGE = Key.PAGE;

    private CategoryService service;
    private CategoryAdapter categoryAdapter;


    private List<CategoryResponse.Genre> listItems;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView=(RecyclerView)v.findViewById (R.id.recyclerView);
        recyclerView.setHasFixedSize (true);
        recyclerView.setLayoutManager (new LinearLayoutManager (getContext ()));

        getJsonData();

        return v;
    }

    private void getJsonData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create (CategoryService.class);

        Call<CategoryResponse> call = service.getCategorySearch(getString(R.string.API_KEY),LANGUAGE);
        call.enqueue(new Callback<CategoryResponse> () {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.code()==200){
                    CategoryResponse categoryresponse = response.body();
                    listItems=categoryresponse.getGenres ();
                    categoryAdapter =new CategoryAdapter (getContext (),listItems);
                    LinearLayoutManager llm = new LinearLayoutManager(getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(llm);
                    recyclerView.setAdapter(categoryAdapter);
                    isVisible ();


                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.e("upcoming tv fragment", "fail reason: code:"+t );

            }
        });
    }

}
