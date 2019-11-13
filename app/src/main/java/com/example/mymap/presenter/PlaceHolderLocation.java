package com.example.mymap.presenter;

import com.example.mymap.location.Example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceHolderLocation {
    @GET("search")
    Call<Example> getKey(@Query("apikey") String apikey, @Query("q") String q, @Query("language") String language, @Query("details") boolean details);
}
