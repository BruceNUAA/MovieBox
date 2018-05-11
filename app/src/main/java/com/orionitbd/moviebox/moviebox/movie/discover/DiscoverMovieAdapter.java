package com.orionitbd.moviebox.moviebox.movie.discover;

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

public class DiscoverMovieAdapter extends RecyclerView.Adapter<DiscoverMovieAdapter.UpcomingMovieViewHolder>{

    private Context context;
    private List<DiscoverMovieResponse.Result> movieList;
    int previousposition =0;

    public DiscoverMovieAdapter(Context context, List<DiscoverMovieResponse.Result> movieList) {
        this.context = context;
        this.movieList =movieList;
    }

    @NonNull
    @Override
    public UpcomingMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v= inflater.inflate(R.layout.poster_row,parent,false);
        return new UpcomingMovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingMovieViewHolder holder, int position) {
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
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class UpcomingMovieViewHolder extends RecyclerView.ViewHolder{

        TextView movieTitle,releaseDate;
        ImageView moviePoster;
        public UpcomingMovieViewHolder(final View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieName);
            releaseDate = itemView.findViewById(R.id.movieDate);
            moviePoster = itemView.findViewById(R.id.moviePoster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Toast.makeText(context, "Movie Name :"+movieList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
