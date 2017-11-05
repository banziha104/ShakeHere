package com.veryworks.iyeongjun.shakehere;

import android.content.Intent;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.veryworks.iyeongjun.shakehere.domain.MarkerItem;

import java.util.ArrayList;

import static com.veryworks.iyeongjun.shakehere.Util.MyUtil.convertPin;
import static com.veryworks.iyeongjun.shakehere.Util.UserLocation.currentUserLocation;
import static com.veryworks.iyeongjun.shakehere.domain.StaticData.datas;


public class MapFragment extends Fragment implements OnMapReadyCallback{

    MapView googleMap;
    LatLng latLng;
    ArrayList<MarkerItem> point = new ArrayList();
    View marker_root_view;
    public MapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        googleMap = (MapView)view.findViewById(R.id.googleMap);
        latLng = new LatLng(currentUserLocation.getLatitude(),currentUserLocation.getLongitude());
        setPoint();
        googleMap.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.zoomTo(14));
        for(int i = 0 ; i < point.size()-1; i++) map.addMarker(setMarker(i)).showInfoWindow();
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                intent.putExtra("pos2",marker.getTitle());
                startActivity(intent);
            }
        });
    }
    private void setPoint(){
        for(int i = 0 ; i < datas.size() ; i++){
            MarkerItem markerItem = new MarkerItem(
                    Double.parseDouble(datas.get(i).getMapy()),
                    Double.parseDouble(datas.get(i).getMapx()),
                    datas.get(i).getTitle(),
                    Integer.parseInt(datas.get(i).getContenttypeid()),
                    convertPin(datas.get(i).getContenttypeid())
            );
            point.add(markerItem);
        }
    }
    private MarkerOptions setMarker(int i){
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(point.get(i).getLat(), point.get(i).getLon()))
                .title(point.get(i).getTitle())
                .icon(BitmapDescriptorFactory.fromResource(point.get(i).getDrawble()));
        return markerOptions;
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
