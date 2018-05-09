package com.orionitbd.moviebox.moviebox.movie.nowplaying;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NowPlayingMovieService {

    @GET("movie/now_playing?")
    Call<NowPlayingMovieResponse> getNowplayingMovie(@Query("api_key") String API_KEY,
                                                   @Query("language") String lang,
                                                   @Query("page") String page);
}
