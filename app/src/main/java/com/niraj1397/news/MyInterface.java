package com.niraj1397.news;

import com.niraj1397.news.parameter.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyInterface {

    @GET("top-headlines")
    Call<Headlines> getHaedline(
        @Query("country") String country,
                @Query("apikey") String apikey);
}
