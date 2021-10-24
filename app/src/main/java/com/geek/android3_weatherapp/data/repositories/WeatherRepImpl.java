package com.geek.android3_weatherapp.data.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.geek.android3_weatherapp.common.Resource;
import com.geek.android3_weatherapp.data.remote.WeatherApi;
import com.geek.android3_weatherapp.domain.repositories.WeatherRepository;
import com.geek.android3_weatherapp.models.Weather;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepImpl implements WeatherRepository {

    private WeatherApi api;
    private MutableLiveData<Resource<Weather>> weatherMutableLiveData;

    @Inject
    public WeatherRepImpl(WeatherApi weatherApi) {
        api = weatherApi;
    }

    @Override
    public MutableLiveData<Resource<Weather>> getCurrentWeather(String city) {
        weatherMutableLiveData = new MutableLiveData<>();
        weatherMutableLiveData.setValue(Resource.loading(null));
        api.getCurrentWeather(city, "65084394eb1fdc5691dadb26a460ebc7")
                .enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        if (response.isSuccessful() && response.body() != null){
                            weatherMutableLiveData.setValue(Resource.success(response.body()));
                        } else {
                            weatherMutableLiveData.setValue(Resource.error(response.errorBody().toString(), null));
                        }
                    }

                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {
                        weatherMutableLiveData.setValue(Resource.error(t.getLocalizedMessage(), null));
                    }
                });
        return weatherMutableLiveData;
    }
}
