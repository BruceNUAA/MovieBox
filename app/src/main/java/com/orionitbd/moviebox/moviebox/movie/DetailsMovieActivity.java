package com.orionitbd.moviebox.moviebox.movie;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.orionitbd.moviebox.moviebox.R;
import com.squareup.picasso.Picasso;

public class DetailsMovieActivity extends AppCompatActivity {

    private ImageView bannerIV ;
    private TextView titleTV;
    private TextView tagTV;
    private TextView overviewTV;
    private TextView statusTV;
    private TextView dateTV;
    private TextView budgetTV;
    private TextView runtimeTV;
    private TextView ratingTV;
    private TextView langTV;
    private TextView genersTV;
    private TextView productionTV;
    private TextView homepageTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        String title = getIntent().getStringExtra("title");
        String tagline = getIntent().getStringExtra("tagline");
        String overview = getIntent().getStringExtra("overview");
        String date = getIntent().getStringExtra("date");
        Long runtime = getIntent().getLongExtra("runtime",0);
        String poster = getIntent().getStringExtra("poster");
        String homepage = getIntent().getStringExtra("homepage");
        String status = getIntent().getStringExtra("status");
        double rating = getIntent().getDoubleExtra("rating",0);
        Long budget = getIntent().getLongExtra("budget",0);
        Long id = getIntent().getLongExtra("id",0);
        String genere = getIntent().getStringExtra("genere");
        String language = getIntent().getStringExtra("language");
        String production = getIntent().getStringExtra("production");

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bannerIV = findViewById(R.id.bigbanner);
        titleTV = findViewById(R.id.detailsTitle);
        tagTV = findViewById(R.id.detailsTag);
        overviewTV = findViewById(R.id.detailsOverview);
        statusTV = findViewById(R.id.detailsStatus);
        dateTV = findViewById(R.id.detailsDate);
        budgetTV = findViewById(R.id.detailsBudget);
        runtimeTV = findViewById(R.id.detailsRuntime);
        ratingTV = findViewById(R.id.detailsRating);
        langTV = findViewById(R.id.detailslang);
        genersTV = findViewById(R.id.detailsGenres);
        productionTV = findViewById(R.id.detailsProduction);
        homepageTV = findViewById(R.id.detailsHomepage);

        Uri posterUri = Uri.parse("http://image.tmdb.org/t/p/w342/"+poster);
        Picasso.get()
                .load(posterUri)
                .into(bannerIV);
        titleTV.setText(title);
        tagTV.setText(tagline);
        overviewTV.setText(overview);
        statusTV.setText(status);
        dateTV.setText(date);
        budgetTV.setText(budget.toString());
        runtimeTV.setText(runtime.toString());
        ratingTV.setText(String.valueOf(rating));
        langTV.setText(language);
        genersTV.setText(genere);
        productionTV.setText(production);
        homepageTV.setText(homepage);





    }
}
