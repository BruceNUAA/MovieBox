package com.orionitbd.moviebox.moviebox.movie.credit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CreditService {
    @GET("movie/{movie_id}/credits?")
    Call<CreditResponse> getDails( @Path("movie_id") Long id,
                                         @Query("api_key") String API_KEY);

}
