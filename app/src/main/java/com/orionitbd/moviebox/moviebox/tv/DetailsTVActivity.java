package com.orionitbd.moviebox.moviebox.tv;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.orionitbd.moviebox.moviebox.R;
import com.squareup.picasso.Picasso;

public class DetailsTVActivity extends AppCompatActivity {

    private ImageView bannerIV ;
    private TextView titleTV;
    private TextView overviewTV;
    private TextView firstDateTV;
    private TextView lastDateTV;
    private TextView statusTV;
    private TextView langTV;
    private TextView epesodeTV;
    private TextView seasonTV;
    private TextView homeTV;
    private TextView ratingTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_tv);

        String title = getIntent().getStringExtra("title");
        String overview = getIntent().getStringExtra("overview");
        String first_date = getIntent().getStringExtra("first_date");
        String last_date = getIntent().getStringExtra("last_date");
        String status = getIntent().getStringExtra("status");
        String poster = getIntent().getStringExtra("poster");
        String original_language = getIntent().getStringExtra("original_language");
        Long number_of_episodes = getIntent().getLongExtra("number_of_episodes",0);
        Long number_of_seasons = getIntent().getLongExtra("number_of_seasons",0);
        String homepage = getIntent().getStringExtra("homepage");
        Double rating = getIntent().getDoubleExtra("rating",0);
        Long id = getIntent().getLongExtra("id",0);


        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       bannerIV = findViewById(R.id.bigbannertv) ;
       titleTV = findViewById(R.id.detailsTitletv) ;
       overviewTV = findViewById(R.id.detailsOverviewtv) ;
       firstDateTV = findViewById(R.id.detailFirstAirtv) ;
       lastDateTV = findViewById(R.id.detailLastAirtv) ;
       statusTV = findViewById(R.id.detailstatustv) ;
       langTV = findViewById(R.id.detailslangtv) ;
       epesodeTV = findViewById(R.id.detailepisodetv) ;
       seasonTV = findViewById(R.id.detailseasontv) ;
       homeTV = findViewById(R.id.detailhometv) ;
       ratingTV = findViewById(R.id.detailsratingtv) ;


        Uri posterUri = Uri.parse("http://image.tmdb.org/t/p/w342/"+poster);
        Picasso.get()
                .load(posterUri)
                .into(bannerIV);

        titleTV.setText(title);
        overviewTV.setText(overview);
       firstDateTV.setText(first_date);
       lastDateTV.setText(last_date);
       statusTV.setText(status);
       langTV.setText(original_language);
       epesodeTV.setText(number_of_episodes.toString());
       seasonTV.setText(number_of_seasons.toString());
       homeTV.setText(homepage);
       ratingTV.setText(String.valueOf(rating));

    }
}
