package com.orionitbd.moviebox.moviebox.movie.popular;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PopularMovieService {

    @GET("movie/popular?")
    Call<PopularMovieResponse> getPopularMovie(@Query("api_key") String API_KEY,
                                                @Query("language") String lang,
                                                @Query("page") String page);
}
