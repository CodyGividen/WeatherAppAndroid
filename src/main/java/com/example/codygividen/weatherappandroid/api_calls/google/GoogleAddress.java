package com.example.codygividen.weatherappandroid.api_calls.google;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoogleAddress {
    @SerializedName("results")
    private List<Results> resultsList;

    public List<Results> getResultsList() {
        return resultsList;
    }

    class Results{

        @SerializedName("formatted_address")
        private String formattedAddress;

        public String getFormattedAddress() {
            return formattedAddress;


        }
        @SerializedName("geometry")
        private Geometry geometry;

        public Geometry getGeometry() {
            return geometry;
        }

        class Geometry{
            @SerializedName("location")
            private Location location;

            public Location getLocation() {
                return location;
            }
        }
        class Location{
            @SerializedName("lng")
            private Double longitude;

            public Double getLongitude() {
                return longitude;
            }

            @SerializedName("lat")
            private Double latitude;

            public Double getLatitude() {
                return latitude;
            }
        }
    }
}
