package com.geek.android3_weatherapp.ui.weather_fragment;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geek.android3_weatherapp.common.Resource;
import com.geek.android3_weatherapp.data.repositories.WeatherRepImpl;
import com.geek.android3_weatherapp.models.Weather;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private WeatherRepImpl weatherRep;

    @Inject
    public WeatherViewModel(WeatherRepImpl weatherRep) {
        this.weatherRep = weatherRep;
    }

    LiveData<Resource<Weather>> getCurrentWeather(String city){
        return weatherRep.getCurrentWeather(city);
    }
}
