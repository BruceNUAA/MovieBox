package com.orionitbd.moviebox.moviebox.tv.toprated;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orionitbd.moviebox.moviebox.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvTabTopratedFragment extends Fragment {


    public TvTabTopratedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tv_tab_toprated_tv, container, false);

        return v;
    }

}
