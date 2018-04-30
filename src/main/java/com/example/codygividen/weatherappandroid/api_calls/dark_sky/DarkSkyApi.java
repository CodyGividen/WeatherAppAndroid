package com.example.codygividen.weatherappandroid.api_calls.dark_sky;

import com.example.codygividen.weatherappandroid.api_calls.google.GoogleAddress;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DarkSkyApi {
    @GET("{api_key}/{latitude},{longitude}")
    Call<Weather> getAddress(@Path("api_key") String apiKey, @Path("latitude") double latitude, @Path("longitude") double longitude);
}
