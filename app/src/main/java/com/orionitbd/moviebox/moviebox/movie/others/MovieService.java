package com.orionitbd.moviebox.moviebox.movie.others;

import com.orionitbd.moviebox.moviebox.movie.response.SimilerMovieResponse;
import com.orionitbd.moviebox.moviebox.movie.response.MovieDetailsResponse;
import com.orionitbd.moviebox.moviebox.movie.response.NowPlayingMovieResponse;
import com.orionitbd.moviebox.moviebox.movie.response.PopularMovieResponse;
import com.orionitbd.moviebox.moviebox.movie.response.TopRatedMovieResponse;
import com.orionitbd.moviebox.moviebox.movie.response.UpcomingMovieResponse;
import com.orionitbd.moviebox.moviebox.movie.response.MovieTrailerResponse;

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
}
