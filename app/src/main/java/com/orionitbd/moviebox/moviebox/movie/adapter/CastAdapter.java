package com.orionitbd.moviebox.moviebox.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.movie.others.CastViewHolder;
import com.orionitbd.moviebox.moviebox.movie.response.CastResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastViewHolder>{

    private Context context;
    private List<CastResponse.Cast>castList;

    public CastAdapter ( Context context, List<CastResponse.Cast> castList ) {
        this.context = context;
        this.castList = castList;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v= inflater.inflate(R.layout.cast_row,parent,false);
        return new CastViewHolder (v);
    }

    @Override
    public void onBindViewHolder ( @NonNull CastViewHolder holder, int position ) {
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
