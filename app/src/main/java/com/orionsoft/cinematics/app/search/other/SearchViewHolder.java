package com.orionsoft.cinematics.app.search.other;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orionsoft.cinematics.app.R;

public class SearchViewHolder  extends RecyclerView.ViewHolder{
    public TextView movieTitle,releaseDate;
    public ImageView moviePoster;
    public LinearLayout layout;

    public SearchViewHolder(View itemView) {
        super(itemView);
        movieTitle = itemView.findViewById(R.id.movieName);
        releaseDate = itemView.findViewById(R.id.movieDate);
        moviePoster = itemView.findViewById(R.id.moviePoster);
        layout = itemView.findViewById(R.id.posterLL);
    }
}
