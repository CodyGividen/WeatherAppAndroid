package com.example.codygividen.weatherappandroid.api_calls.dark_sky;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather implements Parcelable{


    protected Weather(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }


    @SerializedName("latitude")
    private double latitude;

    public double getLatitude() {
        return latitude;
    }






    @SerializedName("longitude")
    private double longitude;

    public double getLongitude() {
        return longitude;
    }






    @SerializedName("currently")
    private CurrentProperties mCurrentlyProperties;

    public CurrentProperties getmCurrentlyProperties() {
        return mCurrentlyProperties;
    }
    class CurrentProperties{
        @SerializedName("temperature")
        private double temperature;

        public double getTemprature() {
            return temperature;
        }

        @SerializedName("summary")
        private String summary;

        public String getSummary() {
            return summary;
        }

        @SerializedName("icon")
        private String weatherIcon;

        public String getWeatherIcon() {
            return weatherIcon;
        }
    }





    @SerializedName("daily")
    private DailyProperties mDailyProperties;

    public DailyProperties getmDailyProperties() {
        return mDailyProperties;
    }
    class DailyProperties{
        @SerializedName("data")
        private List<DailyDataProperties> dailyDataProperties;

        public List<DailyDataProperties> getDailyDataProperties() {
            return dailyDataProperties;
        }
    }class DailyDataProperties{
        @SerializedName("temperatureHigh")
        private double temperatureHigh;

        public double getTemperatureHigh() {
            return temperatureHigh;
        }

        @SerializedName("temperatureLow")
        private double temperatureLow;

        public double getTemperatureLow() {
            return temperatureLow;
        }

        @SerializedName("precipProbability")
        private double precipProbability;

        public double getPrecipProbability() {
            return precipProbability;
        }
    }
}

