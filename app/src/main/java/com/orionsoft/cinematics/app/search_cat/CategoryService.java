package com.orionsoft.cinematics.app.search_cat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryService {

    @GET("genre/movie/list?")
    Call<CategoryResponse> getCategorySearch( @Query("api_key")String API_KEY,
                                          @Query("language") String lang);
}
