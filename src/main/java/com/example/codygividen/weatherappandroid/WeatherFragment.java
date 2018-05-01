package com.example.codygividen.weatherappandroid;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codygividen.weatherappandroid.api_calls.dark_sky.Weather;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.codygividen.weatherappandroid.MainActivity.PLACE;
import static com.example.codygividen.weatherappandroid.MainActivity.WEATHER;

public class WeatherFragment extends Fragment {

    private Weather weather;
    private String place;

    @BindView(R.id.temp_display_textview_placeholder)
    protected TextView currentTempPlaceHolder;
    @BindView(R.id.temp_hi_placeholder)
    protected TextView tempHighPlaceHolder;
    @BindView(R.id.temp_low_placeholder)
    protected TextView temoLowPlaceHolder;
    @BindView(R.id.precip_number_placeholder)
    protected TextView precipPlaceHolder;
    @BindView(R.id.weather_icon)
    protected ImageView weatherIcon;
    @BindView(R.id.location_textview)
    protected TextView location;
    @BindView(R.id.summary_textview)
    protected TextView summary;
    @BindView(R.id.layout_background)
    protected ConstraintLayout colorLayout;


    @Override
    public void onStart() {
        super.onStart();
        location.setText(place);

        currentTempPlaceHolder.setText(getString(R.string.temp_format
                , (int)Math
                        .ceil(weather
                                .getmCurrentlyProperties()
                        .getTemperature())));
        summary.setText(weather
                .getmCurrentlyProperties()
                .getSummary());

        tempHighPlaceHolder.setText(getString(R.string.temp_format
                , (int)Math
                        .ceil((weather
                        .getmDailyProperties()
                        .getDailyDataProperties()
                        .get(0)
                        .getTemperatureHigh()))));

        temoLowPlaceHolder.setText(getString(R.string.low_temp_format
                , (int)Math
                        .ceil((weather
                        .getmDailyProperties()
                        .getDailyDataProperties()
                        .get(0)
                        .getTemperatureLow()))));
        precipPlaceHolder.setText(getString(R.string.precip_chance_format,
                100 * (int)Math
                .ceil((weather
                        .getmDailyProperties()
                        .getDailyDataProperties()
                        .get(0)
                        .getPrecipProbability()))));
        setWeatherIcon();
    }

    private void setWeatherIcon() {
        switch(weather.getmCurrentlyProperties().getWeatherIcon()){
            case "clear-day":
                weatherIcon.setImageResource(R.drawable.clear_day);
                colorLayout.setBackgroundResource(R.color.sunColor);
                break;
            case "clear-night":
                weatherIcon.setImageResource(R.drawable.clear_night);
                colorLayout.setBackgroundResource(R.color.nightColor);
                break;
            case "rain":
                weatherIcon.setImageResource(R.drawable.rain);
                colorLayout.setBackgroundResource(R.color.rainColor);
                break;
            case "snow":
                weatherIcon.setImageResource(R.drawable.snow);
                colorLayout.setBackgroundResource(R.color.snowColor);
                break;
            case "sleet":
                weatherIcon.setImageResource(R.drawable.sleet);
                colorLayout.setBackgroundResource(R.color.sleetColor);
                break;
            case "wind":
                weatherIcon.setImageResource(R.drawable.wind);
                colorLayout.setBackgroundResource(R.color.windColor);
                break;
            case "fog":
                weatherIcon.setImageResource(R.drawable.fog);
                colorLayout.setBackgroundResource(R.color.fogColor);
                break;
            case "cloudy":
                weatherIcon.setImageResource(R.drawable.cloudy);
                colorLayout.setBackgroundResource(R.color.cloudyColor);
                break;
            case "partly-cloudy-day":
                weatherIcon.setImageResource(R.drawable.partly_cloudy_day);
                colorLayout.setBackgroundResource(R.color.partlyCloudyDayColor);
                break;
            case "partly-cloudy-night":
                weatherIcon.setImageResource(R.drawable.partly_cloudy_night);
                colorLayout.setBackgroundResource(R.color.partlyCloudyNightColor);
                break;


        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather,container,false);
        ButterKnife.bind(this,view);
        weather = getArguments().getParcelable(WEATHER);
        place = getArguments().getString(PLACE);
        return view;
    }

    public static WeatherFragment newInstance() {

        Bundle args = new Bundle();

        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
