package com.orionitbd.moviebox.moviebox.tv.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.animation.AnimationUtil;
import com.orionitbd.moviebox.moviebox.tv.others.TVDetails;
import com.orionitbd.moviebox.moviebox.tv.others.TvViewHolder;
import com.orionitbd.moviebox.moviebox.tv.response.TodayTvResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TodayTvAdpater  extends RecyclerView.Adapter<TvViewHolder>{

    private Context context;
    private List<TodayTvResponse.Result> tvList;
    int previousposition =0;


    public TodayTvAdpater ( Context context, List<TodayTvResponse.Result> tvList ) {
        this.context = context;
        this.tvList = tvList;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v= inflater.inflate(R.layout.poster_row,parent,false);
        return new TvViewHolder (v);
    }


    @Override
    public void onBindViewHolder (@NonNull TvViewHolder holder, final int position ) {

        holder.tvTitle.setText(tvList.get(position).getName ());
        holder.FirstAirDate.setText(tvList.get(position).getFirstAirDate ());

        String posterPath = tvList.get(position).getPosterPath ();
        Uri posterUri = Uri.parse("http://image.tmdb.org/t/p/w342/"+posterPath);
        Picasso.get()
                .load(posterUri)
                .into(holder.tvPoster);
        if(position > previousposition){
            AnimationUtil.animate(holder,true);
        }
        else{
            AnimationUtil.animate(holder,false);
        }
        previousposition = position;
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long id = tvList.get(position).getId();
                TVDetails.getDetails(context,id);

            }
        });
    }

    @Override
    public int getItemCount () {
        return tvList.size ();
    }


}