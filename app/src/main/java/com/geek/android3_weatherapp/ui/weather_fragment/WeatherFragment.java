package com.geek.android3_weatherapp.ui.weather_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.geek.android3_weatherapp.R;
import com.geek.android3_weatherapp.common.Resource;
import com.geek.android3_weatherapp.databinding.FragmentWeatherBinding;
import com.geek.android3_weatherapp.models.Weather;

public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;
    private NavController navController;
    private WeatherViewModel viewModel;
    private Weather city = new Weather();

    public WeatherFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(getLayoutInflater());
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        city = (Weather) getArguments().getSerializable("weather");


        NavHostFragment navHostFragment =
                (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        binding.btnCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.mapFragment);
            }
        });



        viewModel.getCurrentWeather(String.valueOf("")).observe(getViewLifecycleOwner(),
                new Observer<Resource<Weather>>() {
                    @Override
                    public void onChanged(Resource<Weather> weatherResource) {
                        switch (weatherResource.status){
                            case SUCCESS:{

                                binding.btnCity.setText(weatherResource.data.getName());
                                binding.tvMainTemp.setText(weatherResource.data.getMain().getTemp().toString());
                                break;
                            }
                            case LOADING:{
                                binding.progressBar.setVisibility(View.VISIBLE);
                                break;
                            }
                        }
                    }
                });

    }
}