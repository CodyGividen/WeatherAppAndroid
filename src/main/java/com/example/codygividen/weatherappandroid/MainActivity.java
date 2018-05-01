package com.example.codygividen.weatherappandroid;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.codygividen.weatherappandroid.api_calls.dark_sky.DarkSkyApi;
import com.example.codygividen.weatherappandroid.api_calls.dark_sky.Weather;
import com.example.codygividen.weatherappandroid.api_calls.google.GoogleAddress;
import com.example.codygividen.weatherappandroid.api_calls.google.GoogleGeoApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.location_edittext)
    protected TextInputEditText location;


    private GoogleGeoApi googleGeoApi;
    private String googleBaseUrl;
    private String googleApiKey;
    private Retrofit googleRetrofit;

    private DarkSkyApi darkSkyApi;
    private String darkSkyBaseUrl;
    private String darkSkyApiKey;
    private Retrofit darkSkyRetrofit;

    private Bundle bundle;
    public static final String PLACE = "place";
    public static final String WEATHER = "weather";

    private WeatherFragment weatherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        weatherFragment = WeatherFragment.newInstance();
        bundle = new Bundle();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleBaseUrl = getString(R.string.google_base_url);
        googleApiKey = getString(R.string.google_api_key);
        darkSkyBaseUrl = getString(R.string.dark_sky_base_url);
        darkSkyApiKey = getString(R.string.dark_sky_api_key);
        googleGeoApi = getGoogleRetrofit().create(GoogleGeoApi.class);
        darkSkyApi = getDarkSkyRetrofit().create(DarkSkyApi.class);
    }

    private Retrofit getGoogleRetrofit() {
        if (googleRetrofit == null) {
            googleRetrofit = new Retrofit.Builder()
                    .baseUrl(googleBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return googleRetrofit;
    }

    private Retrofit getDarkSkyRetrofit() {
        if (darkSkyRetrofit == null) {
            darkSkyRetrofit = new Retrofit.Builder()
                    .baseUrl(darkSkyBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return darkSkyRetrofit;
    }

    @OnClick(R.id.submit_button)
    protected void searchClicked(){
        getLocation(location.getText().toString());
        location.setText("");
    }

    private void getLocation(String address) {
        googleGeoApi.getAddress(address, googleApiKey).enqueue(new Callback<GoogleAddress>() {
            @Override
            public void onResponse(Call<GoogleAddress> call, Response<GoogleAddress> response) {
                try {
                    if (response.isSuccessful()) {
                        bundle.putString(PLACE, response.body().getResultsList().get(0).getFormattedAddress());
                        getWeather(response.body().getResultsList()
                                        .get(0)
                                        .getGeometry()
                                        .getLocation()
                                        .getLatitude(),
                                response.body().getResultsList()
                                        .get(0)
                                        .getGeometry()
                                        .getLocation()
                                        .getLongitude());
                    } else {
                        Toast.makeText(MainActivity.this, "Call was made, unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e) {
                    Toast.makeText(MainActivity.this, "google call failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GoogleAddress> call, Throwable t) {

            }
        });

    }
    private void getWeather(double lat,double lng){
        darkSkyApi.getAddress(darkSkyApiKey,lat,lng).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                try {
                    if (response.isSuccessful()) {

                        bundle.putParcelable(WEATHER, response.body());
                        weatherFragment.setArguments(bundle);

                        transitionToWeatherFragment();
                    } else {
                        Toast.makeText(MainActivity.this, "Weather call made, unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "weather call failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Weather call unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void transitionToWeatherFragment() {
        
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, weatherFragment).commit();
    }
}
