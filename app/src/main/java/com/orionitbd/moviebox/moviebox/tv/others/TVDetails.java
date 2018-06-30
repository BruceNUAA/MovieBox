package com.orionitbd.moviebox.moviebox.tv.others;

import android.content.Context;
import android.content.Intent;


import com.orionitbd.moviebox.moviebox.R;
import com.orionitbd.moviebox.moviebox.key.Key;
import com.orionitbd.moviebox.moviebox.tv.TvDetailsActivity;
import com.orionitbd.moviebox.moviebox.tv.response.TVDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TVDetails {

    public static void getDetails(final Context context , Long id ){
        String BASE_URL = Key.BASE_URL;
        String LANGUAGE = Key.LANGUAGE;
        String key = context.getResources().getString(R.string.API_KEY);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TvService service = retrofit.create(TvService.class);

        Call<TVDetailsResponse> call = service.getTvDetails(id,key, LANGUAGE);
        call.enqueue(new Callback<TVDetailsResponse>() {
            @Override
            public void onResponse(Call<TVDetailsResponse> call, Response<TVDetailsResponse> response) {
                if(response.code()==200){
                    Intent intent = new Intent(context, TvDetailsActivity.class);
                    intent.putExtra("title",response.body().getName());
                    intent.putExtra("overview",response.body().getOverview());
                    intent.putExtra("first_date",response.body().getFirstAirDate());
                    intent.putExtra("last_date",response.body().getLastAirDate());
                    intent.putExtra("status",response.body().getStatus());
                    intent.putExtra("poster",response.body().getBackdropPath());
                    intent.putExtra("original_language",response.body().getOriginalLanguage());
                    intent.putExtra("number_of_episodes",response.body().getNumberOfEpisodes());
                    intent.putExtra("number_of_seasons",response.body().getNumberOfSeasons());
                    intent.putExtra("homepage",response.body().getHomepage());
                    intent.putExtra("rating",response.body().getVoteAverage());
                    intent.putExtra("id",response.body().getId());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }

                }

            @Override
            public void onFailure(Call<TVDetailsResponse> call, Throwable t) {
            }
        });

    }
}
