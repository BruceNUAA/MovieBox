package com.orionsoft.cinematics.app.movie.others;

import com.orionsoft.cinematics.app.movie.response.CastResponse;
import com.orionsoft.cinematics.app.movie.response.SimilerMovieResponse;
import com.orionsoft.cinematics.app.movie.response.MovieDetailsResponse;
import com.orionsoft.cinematics.app.movie.response.NowPlayingMovieResponse;
import com.orionsoft.cinematics.app.movie.response.PopularMovieResponse;
import com.orionsoft.cinematics.app.movie.response.TopRatedMovieResponse;
import com.orionsoft.cinematics.app.movie.response.UpcomingMovieResponse;
import com.orionsoft.cinematics.app.movie.response.MovieTrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/upcoming?")
    Call<UpcomingMovieResponse> getUpcomingMovie(@Query("api_key")String API_KEY,
                                                 @Query("language") String lang,
                                                 @Query("page") String page);

    @GET("movie/top_rated?")
    Call<TopRatedMovieResponse> getTopratedMovie(@Query("api_key") String API_KEY,
                                                 @Query("language") String lang,
                                                 @Query("page") String page);

    @GET("movie/popular?")
    Call<PopularMovieResponse> getPopularMovie(@Query("api_key") String API_KEY,
                                               @Query("language") String lang,
                                               @Query("page") String page);

    @GET("movie/now_playing?")
    Call<NowPlayingMovieResponse> getNowplayingMovie(@Query("api_key") String API_KEY,
                                                     @Query("language") String lang,
                                                     @Query("page") String page);

    @GET("movie/{movie_id}?")
    Call<MovieDetailsResponse> getMovieDetails(@Path("movie_id") Long id,
                                               @Query("language") String language,
                                               @Query("api_key") String API_KEY);

    @GET("movie/{movie_id}/videos?")
    Call<MovieTrailerResponse> getVideoDetails(@Path("movie_id") Long id,
                                               @Query("api_key") String API_KEY,
                                               @Query("language") String language);

    @GET("movie/{movie_id}/similar?")
    Call<SimilerMovieResponse> getSimilerMovie(@Path("movie_id") Long id,
                                               @Query("api_key")String API_KEY,
                                               @Query("language") String lang,
                                               @Query("page") String page);

    @GET("movie/{movie_id}/credits?")
    Call<CastResponse> getCastInfo( @Path("movie_id") Long id,
                                        @Query("api_key")String API_KEY);
}
