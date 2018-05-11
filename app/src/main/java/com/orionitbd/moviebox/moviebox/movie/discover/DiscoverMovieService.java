package com.orionitbd.moviebox.moviebox.movie.discover;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DiscoverMovieService {

    @GET("movie/upcoming?")
    Call<DiscoverMovieResponse> getUpcomingMovie(@Query("api_key") String API_KEY,
                                                 @Query("language") String lang,
                                                 @Query("page") String page);


}
