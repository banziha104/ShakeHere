package com.veryworks.iyeongjun.shakehere;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.veryworks.iyeongjun.shakehere.domain.MarkerItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.veryworks.iyeongjun.shakehere.Util.UserLocation.currentUserLocation;


public class MapFragment extends Fragment implements OnMapReadyCallback{

    MapView googleMap;
    LatLng latLng;
    ArrayList<MarkerItem> point = new ArrayList();
    public MapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        googleMap = (MapView)view.findViewById(R.id.googleMap);
        latLng = new LatLng(currentUserLocation.getLatitude(),currentUserLocation.getLongitude());
        googleMap.getMapAsync(this);
        return view;
    }
    @Override
    public void onMapReady(GoogleMap map) {
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.zoomTo(2));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
        googleMap.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        googleMap.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        googleMap.onSaveInstanceState(outState);
    }
    @Override
    public void onResume() {
        super.onResume();
        googleMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        googleMap.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        googleMap.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        googleMap.onLowMemory();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if (googleMap != null) {
            googleMap.onCreate(savedInstanceState);
        }
    }
}
