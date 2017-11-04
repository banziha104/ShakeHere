package com.veryworks.iyeongjun.shakehere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.veryworks.iyeongjun.shakehere.domain.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.veryworks.iyeongjun.shakehere.domain.StaticData.datas;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    Item item;
    int pos = 0;
    String pos2 = "";
    int curPos;
    @BindView(R.id.detxtTitle)
    TextView detxtTitle;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.firstImage)
    ImageView firstImage;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.detxtContents)
    TextView detxtContents;
    @BindView(R.id.deMap)
    MapView deMap;
    LatLng latlng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        pos = intent.getIntExtra("pos", 1000);
        pos2 = intent.getStringExtra("pos2");
        Toast.makeText(this, pos2+"/"+pos, Toast.LENGTH_SHORT).show();
        if(pos != 1000 && pos2 ==null){
            item = datas.get(pos);
        }else if(pos2 != null && pos == 1000){
            for(int i = 0 ; i < datas.size() ; i++){
                if(datas.get(i).getTitle() == pos2) curPos = i;
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
