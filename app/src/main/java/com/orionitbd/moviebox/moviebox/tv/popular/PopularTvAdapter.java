package com.orionitbd.moviebox.moviebox.tv.popular;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.animation.AnimationUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularTvAdapter extends RecyclerView.Adapter<PopularTvAdapter.PopularTvViewHolder>{

    private Context context;
    private List<PopularTvResponse.Result> tvList;
    int previousposition =0;


    public PopularTvAdapter ( Context context, List<PopularTvResponse.Result> tvList ) {
        this.context = context;
        this.tvList = tvList;
    }

    @NonNull
    @Override
    public PopularTvViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v= inflater.inflate(R.layout.poster_row,parent,false);
        return new PopularTvViewHolder (v);
    }

    @Override
    public void onBindViewHolder ( @NonNull PopularTvAdapter.PopularTvViewHolder holder, int position ) {

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
    }

    @Override
    public int getItemCount () {
        return tvList.size ();
    }

    public class PopularTvViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,FirstAirDate;
        ImageView tvPoster;

        public PopularTvViewHolder ( final View itemView ) {
            super (itemView);

            tvTitle = itemView.findViewById(R.id.movieName);
            FirstAirDate = itemView.findViewById(R.id.movieDate);
            tvPoster = itemView.findViewById(R.id.moviePoster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Toast.makeText(context, "Popular Tv Name :"+tvList.get(position).getName (), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}