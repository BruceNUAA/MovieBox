package com.orionitbd.moviebox.moviebox.movie.upcoming;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orionitbd.moviebox.moviebox.DetailsActivity;
import com.orionitbd.moviebox.moviebox.MainActivity;
import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.about.AboutActivity;
import com.orionitbd.moviebox.moviebox.animation.AnimationUtil;
import com.orionitbd.moviebox.moviebox.key.Key;
import com.orionitbd.moviebox.moviebox.movie.details.MovieDetails;
import com.orionitbd.moviebox.moviebox.movie.details.MovieDetailsResponse;
import com.orionitbd.moviebox.moviebox.movie.details.MovieDetailsService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpcomingMovieAdapter extends RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingMovieViewHolder>{

    private Context context;
    private List<UpcomingMovieResponse.Result> movieList;
    int previousposition =0;

    public UpcomingMovieAdapter(Context context, List<UpcomingMovieResponse.Result> movieList) {
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

                    Long id = movieList.get(position).getId();
                    MovieDetails.getDetails(context,id);

                }
            });
        }
    }
}
