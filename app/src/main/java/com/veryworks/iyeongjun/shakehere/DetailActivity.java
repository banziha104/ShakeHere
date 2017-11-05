package com.veryworks.iyeongjun.shakehere;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.tsengvn.typekit.TypekitContextWrapper;
import com.veryworks.iyeongjun.shakehere.domain.ARPoint;
import com.veryworks.iyeongjun.shakehere.domain.Item;
import com.veryworks.iyeongjun.shakehere.domain.MarkerItem;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.veryworks.iyeongjun.shakehere.Util.MyUtil.convertPin;
import static com.veryworks.iyeongjun.shakehere.Util.MyUtil.convertType;
import static com.veryworks.iyeongjun.shakehere.Util.UserLocation.currentUserLocation;
import static com.veryworks.iyeongjun.shakehere.domain.StaticData.datas;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    Item item;
    int pos = 0;
    String pos2 = "";
    int curPos;
    LatLng latlng;
    boolean isHaveImage;
    MarkerItem markerItem;
    /*이미지가 있을 때*/
    @BindView(R.id.detxtTitle) TextView detxtTitle;
    @BindView(R.id.firstImage) ImageView firstImage;
    @BindView(R.id.detxtContents) TextView detxtContents;
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.deMap);
        ButterKnife.bind(this);
        getData();
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap map) {
        map.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        map.animateCamera(CameraUpdateFactory.zoomTo(14));
        map.addMarker(setMarker()).showInfoWindow();
    }
    private void setPoint(){
        markerItem = new MarkerItem(
                    Double.parseDouble(item.getMapy()),
                    Double.parseDouble(item.getMapx()),
                    item.getTitle(),
                    Integer.parseInt(item.getContenttypeid()),
                    convertPin(item.getContenttypeid())
        );
    }
    private MarkerOptions setMarker(){
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(markerItem.getLat(), markerItem.getLon()))
                .title(markerItem.getTitle())
                .icon(BitmapDescriptorFactory.fromResource(markerItem.getDrawble()));
        return markerOptions;
    }

    public void getData() {
        Intent intent = getIntent();
        pos = intent.getIntExtra("pos", 1000);
        pos2 = intent.getStringExtra("pos2");
        if (pos != 1000 && pos2 == null) {
            curPos = pos;
        } else if (pos2 != null && pos == 1000) {
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i).getTitle().equals(pos2)) curPos = i;
            }
        }
        setData(curPos);
    }

    private void setData(int i) {
        item = datas.get(i);
        detxtTitle.setText(item.getTitle());
        detxtTitle.setTextSize(20f);
        detxtTitle.setTextColor(Color.BLACK);
        detxtContents.setText(setContents(item));
        detxtContents.setTextColor(Color.BLACK);
        detxtContents.setTextSize(15f);
        latlng = new LatLng(Double.parseDouble(item.getMapy()),Double.parseDouble(item.getMapx()));
        if(item.getFirstimage() == null){
            Picasso.with(this).load(R.drawable.default_image).into(firstImage);
        }else{
            Picasso.with(this).load(item.getFirstimage()).into(firstImage);
        }
        setPoint();
    }
    private String getDistance(Item item){
        Random random = new Random();
        ARPoint arPoint = new ARPoint(
                item.getTitle(),
                Double.parseDouble(item.getMapy()),
                Double.parseDouble(item.getMapx()),
                ((double)(random.nextInt()%150))
        );
        return (((int)(currentUserLocation.distanceTo(arPoint.getLocation())))/(1000.0))+"km";
    }
    private String setContents(Item item){
        String result;
        result = "관광 타입 :" +
                convertType(item.getContenttypeid())+"\n" +
                "주소 :" +
                item.getAddr1() +"\n" +
                "거리 :" +
                getDistance(item);
        if(item.getTel()!=null) result += "\n"+"전화 및 문의: " + item.getTel();


        return result;
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        googleMap.onStart();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        googleMap.onStop();
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        googleMap.onSaveInstanceState(outState);
//    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        googleMap.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        googleMap.onPause();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        googleMap.onLowMemory();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        googleMap.onLowMemory();
//    }
}
