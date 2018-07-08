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
import com.orionsoft.cinematics.app.tv.response.SimilerTvResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilerTvAdapter extends RecyclerView.Adapter<TvViewHolder> {

    private Context context;
    private List<SimilerTvResponse.Result> showList;
    int previousposition =0;

    public SimilerTvAdapter(Context context, List<SimilerTvResponse.Result> showList) {
        this.context = context;
        this.showList = showList;
    }


    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v= inflater.inflate(R.layout.poster_row,parent,false);
        return new TvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, final int position) {
        holder.tvTitle.setText(showList.get(position).getName ());
        holder.FirstAirDate.setText(showList.get(position).getFirstAirDate ());

        String posterPath = showList.get(position).getPosterPath ();
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
                Long id = showList.get(position).getId();
                TVDetails.getDetails(context,id);

            }
        });
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }
}
