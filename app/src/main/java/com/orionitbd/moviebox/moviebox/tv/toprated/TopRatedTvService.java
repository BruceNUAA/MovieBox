package com.orionitbd.moviebox.moviebox.tv.toprated;

import com.orionitbd.moviebox.moviebox.movie.upcoming.UpcomingMovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopRatedTvService {

    @GET("movie/upcoming?")
    Call<UpcomingMovieResponse> getUpcomingMovie( @Query ("api_key") String API_KEY,
                                                  @Query ("language") String lang,
                                                  @Query ("page") String page);
}
