package com.orionitbd.moviebox.moviebox.movie.toprated;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orionitbd.moviebox.moviebox.R;


public class MovieTabTopratedFragment extends Fragment {


    public MovieTabTopratedFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_tab_toprated, container, false);
    }




}
