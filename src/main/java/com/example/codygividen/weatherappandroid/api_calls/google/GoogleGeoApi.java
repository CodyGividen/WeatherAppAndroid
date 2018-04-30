package com.example.codygividen.weatherappandroid.api_calls.google;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleGeoApi {

    @GET("json")
    Call<GoogleAddress> getAddress(@Query("address") String address, @Query("api_key") String apiKey);


}
