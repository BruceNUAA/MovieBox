package com.orionitbd.moviebox.moviebox.movie.popular;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orionitbd.moviebox.moviebox.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieTabPopularFragment extends Fragment {


    public MovieTabPopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_tab_popular, container, false);
    }

}
