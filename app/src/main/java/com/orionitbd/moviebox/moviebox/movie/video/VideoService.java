package com.orionitbd.moviebox.moviebox.movie.video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VideoService {
    @GET("movie/{movie_id}/videos?")
    Call<VideoResponse> getDetails(@Path("movie_id") Long id,
                                   @Query("api_key") String API_KEY,
                                   @Query("language") String language);
}
