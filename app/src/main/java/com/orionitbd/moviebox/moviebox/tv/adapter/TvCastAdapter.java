package com.orionitbd.moviebox.moviebox.tv.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.tv.others.TvCastViewHolder;
import com.orionitbd.moviebox.moviebox.tv.response.TvCastResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TvCastAdapter extends RecyclerView.Adapter<TvCastViewHolder>{

    private Context context;
    private List<TvCastResponse.Cast>castList;

    public TvCastAdapter ( Context context, List<TvCastResponse.Cast> castList ) {
        this.context = context;
        this.castList = castList;
    }

    @NonNull
    @Override
    public TvCastViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v= inflater.inflate(R.layout.cast_row,parent,false);
        return new TvCastViewHolder (v);
    }

    @Override
    public void onBindViewHolder ( @NonNull TvCastViewHolder holder, int position ) {

        String name=castList.get (position).getName ();

        if (name!=null){
            holder.castName.setText (name);
        }
        else {
            holder.castName.setText ("Not Found");
        }
        String posterPath = castList.get(position).getProfilePath ();

        if (posterPath!=null){
            Uri posterUri = Uri.parse("http://image.tmdb.org/t/p/w342/"+posterPath);
            Picasso.get()
                    .load(posterUri)
                    .into(holder.castPic);
        }
        else {

        }
    }

    @Override
    public int getItemCount () {
        return castList.size ();
    }
}
