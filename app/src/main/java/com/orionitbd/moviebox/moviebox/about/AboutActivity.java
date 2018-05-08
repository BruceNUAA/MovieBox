package com.orionitbd.moviebox.moviebox.about;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orionitbd.moviebox.moviebox.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setTitle("About");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
