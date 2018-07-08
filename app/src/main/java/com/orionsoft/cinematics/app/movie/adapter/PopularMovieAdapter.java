package com.orionsoft.cinematics.app.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orionsoft.cinematics.app.R;
import com.orionsoft.cinematics.app.animation.AnimationUtil;
import com.orionsoft.cinematics.app.movie.others.MovieViewHolder;
import com.orionsoft.cinematics.app.movie.others.MovieDetails;
import com.orionsoft.cinematics.app.movie.response.PopularMovieResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularMovieAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    private Context context;
    private List<PopularMovieResponse.Result> movieList;
    int previousposition =0;

    public PopularMovieAdapter(Context context, List<PopularMovieResponse.Result> movieList) {
        this.context = context;
        this.movieList =movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v= inflater.inflate(R.layout.poster_row,parent,false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movieList.get(position).getTitle());
        holder.releaseDate.setText(movieList.get(position).getReleaseDate());

        String posterPath = movieList.get(position).getPosterPath();
        Uri posterUri = Uri.parse("http://image.tmdb.org/t/p/w342/"+posterPath);
        Picasso.get()
                .load(posterUri)
                .into(holder.moviePoster);
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
                Long id = movieList.get(position).getId();
                MovieDetails.getDetails(context,id);

            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

}
