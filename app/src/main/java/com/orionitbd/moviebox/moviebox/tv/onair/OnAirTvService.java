package com.orionitbd.moviebox.moviebox.tv.onair;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OnAirTvService {

    @GET("tv/on_the_air?")
    Call<OnAirTvResponse> getOnAirTv( @Query("api_key")String API_KEY,
                                                  @Query("language") String lang,
                                                  @Query("page") String page);
}
