package com.orionitbd.moviebox.moviebox.tv.onair;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orionitbd.moviebox.moviebox.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvTabOnairFragment extends Fragment {


    public TvTabOnairFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_tab_onair, container, false);
    }

}
