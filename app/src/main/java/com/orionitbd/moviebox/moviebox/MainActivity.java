package com.orionitbd.moviebox.moviebox;

import android.app.SearchManager;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.SearchView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.orionitbd.moviebox.moviebox.movie.MovieTabFragment;
import com.orionitbd.moviebox.moviebox.tv.TvTabFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TabPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.mViewPager);
        mTabLayout = findViewById(R.id.mTabLayout);

        mTabLayout.addTab(mTabLayout.newTab().setText("MOVIE"));
        mTabLayout.addTab(mTabLayout.newTab().setText("TV"));

        adapter = new TabPagerAdapter(getSupportFragmentManager(),mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        itemMenu();

    }
    public class TabPagerAdapter extends FragmentPagerAdapter{

        private int tabCount;
        public TabPagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount= tabCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new MovieTabFragment();
                case 1:
                    return new TvTabFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        SearchManager manager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setIconified(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.rate:
                Toast.makeText(this, "rate our app", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Toast.makeText(this, "share our app", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(this, "about our app", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void itemMenu(){
        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY) ;
            Toast.makeText(this, "Search "+query, Toast.LENGTH_SHORT).show();
        }
    }
}
