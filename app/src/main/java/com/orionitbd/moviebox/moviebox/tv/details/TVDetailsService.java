package com.orionitbd.moviebox.moviebox.tv.details;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TVDetailsService {

    @GET("tv/{tv_id}?")
    Call<TVDetailsResponse> getDetails(@Path("tv_id") Long id,
                                     @Query("api_key") String language,
                                     @Query("language") String API_KEY);
}
