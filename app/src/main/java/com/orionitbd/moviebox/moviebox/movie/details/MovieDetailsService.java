package com.orionitbd.moviebox.moviebox.movie.details;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDetailsService {

    @GET("movie/{movie_id}?")
    Call<MovieDetailsResponse> getDails(@Path("movie_id") Long id,
                                        @Query("language") String language,
                                        @Query("api_key") String API_KEY);
}
