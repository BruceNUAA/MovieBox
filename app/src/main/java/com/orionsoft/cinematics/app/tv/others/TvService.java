package com.orionsoft.cinematics.app.tv.others;

import com.orionsoft.cinematics.app.tv.response.OnAirTvResponse;
import com.orionsoft.cinematics.app.tv.response.PopularTvResponse;
import com.orionsoft.cinematics.app.tv.response.SimilerTvResponse;
import com.orionsoft.cinematics.app.tv.response.TVDetailsResponse;
import com.orionsoft.cinematics.app.tv.response.TVTrailerResponse;
import com.orionsoft.cinematics.app.tv.response.TodayTvResponse;
import com.orionsoft.cinematics.app.tv.response.TopRatedTvResponse;
import com.orionsoft.cinematics.app.tv.response.TvCastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvService {

    @GET("tv/on_the_air?")
    Call<OnAirTvResponse> getOnAirTvShow(@Query("api_key")String API_KEY,
                                     @Query("language") String lang,
                                     @Query("page") String page);

    @GET("tv/popular?")
    Call<PopularTvResponse> getPopularTvShow(@Query("api_key")String API_KEY,
                                               @Query("language") String lang,
                                               @Query("page") String page);

    @GET("tv/airing_today?")
    Call<TodayTvResponse> getTodayTvShow(@Query("api_key")String API_KEY,
                                           @Query("language") String lang,
                                           @Query("page") String page);

    @GET("tv/top_rated?")
    Call<TopRatedTvResponse> geTopRatedTvShow(@Query ("api_key") String API_KEY,
                                                @Query ("language") String lang,
                                                @Query ("page") String page);

    @GET("tv/{tv_id}?")
    Call<TVDetailsResponse> getTvDetails(@Path("tv_id") Long id,
                                         @Query("api_key") String language,
                                         @Query("language") String API_KEY);


    @GET("tv/{tv_id}/videos?")
    Call<TVTrailerResponse> getVideoDetails(@Path("tv_id") Long id,
                                            @Query("api_key") String API_KEY,
                                            @Query("language") String language);


    @GET("tv/{tv_id}/similar?")
    Call<SimilerTvResponse> getSimilerTvShow(@Path("tv_id") Long id,
                                             @Query("api_key")String API_KEY,
                                             @Query("language") String lang,
                                             @Query("page") String page);

    @GET("tv/{tv_id}/credits?")
    Call<TvCastResponse> getTvCastInfo( @Path("tv_id") Long id,
                                        @Query("api_key")String API_KEY,
                                        @Query("language") String lang);
}
