package com.orionitbd.moviebox.moviebox.movie.upcoming;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UpcomingMovieService {

    @GET("movie/upcoming?")
    Call<UpcomingMovieResponse> getUpcomingMovie(@Query("api_key")String API_KEY,
                                                 @Query("language") String lang,
                                                 @Query("page") String page);
}
