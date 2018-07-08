package com.orionsoft.cinematics.app.tv.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orionsoft.cinematics.app.R;
import com.orionsoft.cinematics.app.animation.AnimationUtil;
import com.orionsoft.cinematics.app.tv.others.TVDetails;
import com.orionsoft.cinematics.app.tv.others.TvViewHolder;
import com.orionsoft.cinematics.app.tv.response.PopularTvResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularTvAdapter extends RecyclerView.Adapter<TvViewHolder>{

    private Context context;
    private List<PopularTvResponse.Result> tvList;
    int previousposition =0;


    public PopularTvAdapter ( Context context, List<PopularTvResponse.Result> tvList ) {
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