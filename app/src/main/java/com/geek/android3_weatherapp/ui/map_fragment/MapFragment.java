package com.geek.android3_weatherapp.ui.map_fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.geek.android3_weatherapp.R;
import com.geek.android3_weatherapp.databinding.FragmentMapBinding;
import com.geek.android3_weatherapp.models.Weather;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MapFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    private FragmentMapBinding binding;
    private GoogleMap gMap;
    private NavController navController;
    private String[] perms = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    private LocationManager locationManager;

    public MapFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);

        ActivityCompat.requestPermissions(requireActivity(), perms, 100);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        NavHostFragment navHostFragment =
                (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        binding.btnShowWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Weather weather = new Weather();
                Bundle bundle = new Bundle();
                bundle.putSerializable("weather", weather);
                navController.navigate(R.id.weatherFragment, bundle);
            }
        });
    }


    private void getCurrentLocation(){
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        } else {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0, 0, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            { }

        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        getCurrentLocation();
        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                gMap.addMarker(new MarkerOptions()
                        .position(latLng));
                gMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder()
                        .zoom(11f)
                        .target(latLng)
                        .tilt(30f)
                        .build()), 3000, null
                );
                gMap.getUiSettings().isZoomControlsEnabled();
                binding.btnPolyline.setOnClickListener(v->{
                    gMap.addPolyline(new PolylineOptions().add(latLng));
                });
                binding.btnClear.setOnClickListener(v->{
                    gMap.clear();
                });
            }
        });


    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        gMap.addMarker(new MarkerOptions()
                .position(latLng));
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                CameraPosition.builder()
                        .zoom(11f)
                        .target(latLng)
                        .build()
                ), 3000, null
        );
    }
}