package com.geek.android3_weatherapp.data.remote;

import com.geek.android3_weatherapp.models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather")
    Call<Weather> getCurrentWeather(
            @Query("q") String city,
            @Query("appId") String apiKey);

//    @GET("data/2.5/weather")
//    Call<Weather> getCurrentWeather(
//            @Query("lat") String lat,
//            @Query("lng") String lng,
//            @Query("appId") String apiKey
//    );

}
