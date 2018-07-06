package com.orionitbd.moviebox.moviebox.tv.others;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orionitbd.moviebox.moviebox.R;

public class TvCastViewHolder extends RecyclerView.ViewHolder {

    public TextView castName;
    public ImageView castPic;

    public TvCastViewHolder ( View itemView ) {
        super (itemView);

        castName=itemView.findViewById (R.id.castName);
        castPic=itemView.findViewById (R.id.castPic);
    }
}
