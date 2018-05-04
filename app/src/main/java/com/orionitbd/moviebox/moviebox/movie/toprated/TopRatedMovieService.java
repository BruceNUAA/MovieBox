package com.orionitbd.moviebox.moviebox.movie.toprated;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopRatedMovieService {

    @GET("movie/upcoming?")
    Call<TopRatedMovieResponse> getUpcomingMovie(@Query("api_key") String API_KEY,
                                                 @Query("language") String lang,
                                                 @Query("page") String page);
}
