package com.orionitbd.moviebox.moviebox.tv.toprated;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopRatedTvService {

    @GET("tv/top_rated?")
    Call<TopRatedTvResponse> geTopRatedResponse( @Query ("api_key") String API_KEY,
                                                 @Query ("language") String lang,
                                                 @Query ("page") String page);
}