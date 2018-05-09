package com.orionitbd.moviebox.moviebox.movie.toprated;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopRatedMovieService {

    @GET("movie/top_rated?")
    Call<TopRatedMovieResponse> getTopratedMovie(@Query("api_key") String API_KEY,
                                                 @Query("language") String lang,
                                                 @Query("page") String page);
}
