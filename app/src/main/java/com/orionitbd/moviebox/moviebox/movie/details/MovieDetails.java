package com.orionitbd.moviebox.moviebox.movie.details;

import android.content.Context;

import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import com.orionitbd.moviebox.moviebox.DetailsActivity;
import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.key.Key;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetails {

    public static void getDetails(final Context context , Long id ){
        String BASE_URL = Key.BASE_URL;
        String LANGUAGE = Key.LANGUAGE;
        String key = context.getResources().getString(R.string.API_KEY);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieDetailsService service = retrofit.create(MovieDetailsService.class);
        Call<MovieDetailsResponse> call = service.getDails(id,LANGUAGE, key);
        call.enqueue(new Callback<MovieDetailsResponse>() {
            @Override
            public void onResponse(Call<MovieDetailsResponse> call, Response<MovieDetailsResponse> response) {
                if(response.code()==200){
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("title",response.body().getOriginalTitle());
                    intent.putExtra("tagline",response.body().getTagline());
                    intent.putExtra("overview",response.body().getOverview());
                    intent.putExtra("date",response.body().getReleaseDate());
                    intent.putExtra("runtime",response.body().getRuntime());
                    intent.putExtra("poster",response.body().getBackdropPath());
                    intent.putExtra("homepage",response.body().getHomepage());
                    intent.putExtra("status",response.body().getStatus());
                    intent.putExtra("rating",response.body().getVoteAverage());
                    intent.putExtra("budget",response.body().getBudget());
                    intent.putExtra("id",response.body().getId());
                    String generelist="";
                    String language="";
                    String production="";
                    for(int i = 0; i<response.body().getGenres().size();i++){
                        generelist =generelist+response.body().getGenres().get(i).getName()+", ";
                    }
                    intent.putExtra("genere",generelist);
                    for(int i = 0; i<response.body().getSpokenLanguages().size();i++){
                        language =language+response.body().getSpokenLanguages().get(i).getName()+", ";
                    }
                    intent.putExtra("language",language);
                    for(int i = 0; i<response.body().getProductionCompanies().size();i++){
                        production =production+response.body().getProductionCompanies().get(i).getName()+", ";
                    }
                    intent.putExtra("production",production);

                    context.startActivity(intent);
                }

                }

            @Override
            public void onFailure(Call<MovieDetailsResponse> call, Throwable t) {
            }
        });


    }
}
