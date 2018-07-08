package com.orionsoft.cinematics.app;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.orionsoft.cinematics.app.animation.ZoomOutTransformation;
import com.orionsoft.cinematics.app.movie.MovieTabFragment;
import com.orionsoft.cinematics.app.search.SearchActivity;
import com.orionsoft.cinematics.app.search_cat.SearchFragment;
import com.orionsoft.cinematics.app.tv.TvTabFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TabPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.applogo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        mViewPager = findViewById(R.id.mViewPager);
        mTabLayout = findViewById(R.id.mTabLayout);

        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.movie));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.tv));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.search));

        adapter = new TabPagerAdapter(getSupportFragmentManager(),mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                mViewPager.setPageTransformer(true, new ZoomOutTransformation());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        itemMenu();
        setCustomFont();

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
                case 2:
                    return new SearchFragment();
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
                Toast.makeText(this, "thanks for rate our app", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                String sub = "MobieBox";
                String title = "Download MovieBox apps from ..... ";
                share.putExtra(Intent.EXTRA_SUBJECT,sub);
                share.putExtra(Intent.EXTRA_TEXT,title);
                startActivity(Intent.createChooser(share,"Share using"));
                break;
            case R.id.about:
                Intent intent = new Intent(this,AboutActivity.class );
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void itemMenu(){
        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY) ;
            if(query != null){
                Intent search = new Intent(this, SearchActivity.class);
                search.putExtra("query",query);
                startActivity(search);
            }

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
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getAssets(), "custom_font.ttf"));
                }
            }
        }
    }
}
