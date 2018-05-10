package com.orionitbd.moviebox.moviebox.tv.popular;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PopularTvService {

    @GET("tv/popular?")
    Call<PopularTvResponse> getPopularResponse( @Query("api_key")String API_KEY,
                                                @Query("language") String lang,
                                                @Query("page") String page);
}