package com.orionitbd.moviebox.moviebox.tv.today;

import com.orionitbd.moviebox.moviebox.tv.popular.PopularTvResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TodayTvService {

    @GET("tv/airing_today?")
    Call<TodayTvResponse> getTodayResponse( @Query("api_key")String API_KEY,
                                            @Query("language") String lang,
                                            @Query("page") String page);
}