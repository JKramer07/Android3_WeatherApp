package com.geek.android3_weatherapp.di;

import com.geek.android3_weatherapp.data.remote.RetrofitClient;
import com.geek.android3_weatherapp.data.remote.WeatherApi;
import com.geek.android3_weatherapp.data.repositories.WeatherRepImpl;
import com.geek.android3_weatherapp.domain.repositories.WeatherRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    public static WeatherRepository provideWeatherRep(WeatherApi weatherApi){
        return new WeatherRepImpl(weatherApi);
    }

    @Provides
    public static RetrofitClient provideRetrofitClient(){
        return new RetrofitClient();
    }

    @Provides
    public static WeatherApi provideWeatherApi(RetrofitClient retrofitClient){
        return retrofitClient.provideWeatherApi();
    }
}
