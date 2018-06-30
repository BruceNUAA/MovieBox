package com.orionitbd.moviebox.moviebox.movie.others;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orionitbd.moviebox.moviebox.R;

public class MovieViewHolder  extends RecyclerView.ViewHolder{
    public TextView movieTitle,releaseDate;
    public ImageView moviePoster;
    public LinearLayout layout;

    public MovieViewHolder(View itemView) {
        super(itemView);
        movieTitle = itemView.findViewById(R.id.movieName);
        releaseDate = itemView.findViewById(R.id.movieDate);
        moviePoster = itemView.findViewById(R.id.moviePoster);
        layout = itemView.findViewById(R.id.posterLL);
    }
}
