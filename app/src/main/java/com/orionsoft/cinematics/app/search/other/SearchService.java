package com.orionsoft.cinematics.app.search.other;

import com.orionsoft.cinematics.app.search.response.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("search/movie?")
    Call<SearchResponse> getSearchMovie(@Query("api_key") String API_KEY,
                                            @Query("language") String lang,
                                            @Query("page") String page,
                                            @Query("include_adult") String adult,
                                            @Query("query") String query);

}
