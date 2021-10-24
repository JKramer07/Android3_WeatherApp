package com.geek.android3_weatherapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.geek.android3_weatherapp.R;
import com.geek.android3_weatherapp.databinding.ActivityMainBinding;
import com.geek.android3_weatherapp.ui.weather_fragment.MainViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    private GoogleMap gMap;
    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//        viewModel = new ViewModelProvider(this).get(MainViewModel.class);


    }


//    @Override
//    public void onMapReady(@NonNull GoogleMap googleMap) {
//        gMap = googleMap;

//        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(@NonNull LatLng latLng) {
//                viewModel.getCurrentWeather(String.valueOf(latLng.latitude), String.valueOf(latLng.longitude))
//                        .observe(MainActivity.this, new Observer<Resource<Weather>>() {
//                            @Override
//                            public void onChanged(Resource<Weather> weatherResource) {
//                                Log.e("TAG", "onResponse " + latLng.toString());
//                            }
//                        });
//
//                gMap.clear();
//                gMap.addMarker(new MarkerOptions().position(latLng));
//                gMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder()
//                        .zoom(11f)
//                        .target(latLng)
//                        .tilt(30f)
//                        .build()), 3000, null
//                );
//
//                WeatherFragment weatherFragment = new WeatherFragment();
//                getSupportFragmentManager().beginTransaction().add(R.id.container, weatherFragment).commit();
//            }
//        });

//        LatLng bishkek = new LatLng(42.87, 74.59);
//        gMap.addMarker(new MarkerOptions()
//                .position(bishkek));
//        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(
//                CameraPosition.builder()
//                        .zoom(11f)
//                        .target(bishkek)
//                        .build()
//        ), 3000, null
//        );
//    }

}