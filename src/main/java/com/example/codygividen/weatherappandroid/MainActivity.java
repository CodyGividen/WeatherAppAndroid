package com.example.codygividen.weatherappandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.codygividen.weatherappandroid.api_calls.dark_sky.DarkSkyApi;
import com.example.codygividen.weatherappandroid.api_calls.google.GoogleGeoApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @BindView()


    private GoogleGeoApi googleGeoApi;
    private String googleBaseUrl = getString(R.string.google_base_url);
    private String googleApiKey = getString(R.string.google_api_key);
    private Retrofit googleRetrofit;

    private DarkSkyApi darkSkyApi;
    private String darkSkyBaseUrl = getString(R.string.dark_sky_base_url);
    private String darkSkyApiKey = getString(R.string.dark_sky_api_key);
    private Retrofit darkSkyRetrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
