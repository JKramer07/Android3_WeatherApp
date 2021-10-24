package com.geek.android3_weatherapp.domain.repositories;

import androidx.lifecycle.MutableLiveData;

import com.geek.android3_weatherapp.common.Resource;
import com.geek.android3_weatherapp.models.Weather;

public interface WeatherRepository {

    MutableLiveData<Resource<Weather>> getCurrentWeather(String city);
}
