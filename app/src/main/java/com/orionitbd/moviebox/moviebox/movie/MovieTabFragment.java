package com.orionitbd.moviebox.moviebox.movie;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.animation.FanTransformation;
import com.orionitbd.moviebox.moviebox.movie.latest.MovieTabLatestFragment;
import com.orionitbd.moviebox.moviebox.movie.nowplaying.MovieTabNowplayingFragment;
import com.orionitbd.moviebox.moviebox.movie.popular.MovieTabPopularFragment;
import com.orionitbd.moviebox.moviebox.movie.toprated.MovieTabTopratedFragment;
import com.orionitbd.moviebox.moviebox.movie.upcoming.MovieTabUpcomingFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieTabFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TabPagerAdapter tabPagerAdapter;

    public MovieTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie_tab, container, false);

        mViewPager = v.findViewById(R.id.movieViewPager);
        mTabLayout = v.findViewById(R.id.movieTabLayout);

        mTabLayout.addTab(mTabLayout.newTab().setText("UPCOMING"));
        mTabLayout.addTab(mTabLayout.newTab().setText("TOP RATED"));
        mTabLayout.addTab(mTabLayout.newTab().setText("POPULAR"));
        mTabLayout.addTab(mTabLayout.newTab().setText("NOW PLAYING"));
        mTabLayout.addTab(mTabLayout.newTab().setText("LATEST"));

        tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager(),mTabLayout.getTabCount());
        mViewPager.setAdapter(tabPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                mViewPager.setPageTransformer(true, new FanTransformation());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setCustomFont();
        return v;
    }
    private class TabPagerAdapter extends FragmentPagerAdapter {

        private int tabCount;
        public TabPagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount= tabCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new MovieTabUpcomingFragment();
                case 1:
                    return new MovieTabTopratedFragment();
                case 2:
                    return new MovieTabPopularFragment();
                case 3:
                    return new MovieTabNowplayingFragment();
                case 4:
                    return new MovieTabLatestFragment();


            }
            return null;
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }

    private void setCustomFont(){

        ViewGroup vg = (ViewGroup) mTabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "custom_font.ttf"));
                }
            }
        }




    }


}
