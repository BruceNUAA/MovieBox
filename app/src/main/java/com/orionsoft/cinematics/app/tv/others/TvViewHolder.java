package com.orionsoft.cinematics.app.tv.others;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orionsoft.cinematics.app.R;

public class TvViewHolder extends RecyclerView.ViewHolder{

    public TextView tvTitle,FirstAirDate;
    public ImageView tvPoster;
    public LinearLayout layout;

    public TvViewHolder(View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.movieName);
        FirstAirDate = itemView.findViewById(R.id.movieDate);
        tvPoster = itemView.findViewById(R.id.moviePoster);
        layout = itemView.findViewById(R.id.posterLL);

    }
}
