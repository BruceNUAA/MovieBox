package com.orionitbd.moviebox.moviebox.movie.latest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LatestMovieService {

    @GET("movie/upcoming?")
    Call<LatestMovieResponse> getUpcomingMovie(@Query("api_key") String API_KEY,
                                               @Query("language") String lang,
                                               @Query("page") String page);
}
