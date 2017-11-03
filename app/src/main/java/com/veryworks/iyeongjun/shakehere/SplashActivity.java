package com.veryworks.iyeongjun.shakehere;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.veryworks.iyeongjun.shakehere.Util.PermissionControl;
import com.veryworks.iyeongjun.shakehere.Util.UserLocation;
import com.veryworks.iyeongjun.shakehere.domain.Const;
import com.veryworks.iyeongjun.shakehere.domain.DataReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.Subject;

import static com.veryworks.iyeongjun.shakehere.Util.PermissionControl.checkVersion;
import static com.veryworks.iyeongjun.shakehere.Util.UserLocation.currentUserLocation;
import static com.veryworks.iyeongjun.shakehere.domain.StaticData.datas;

public class SplashActivity extends AppCompatActivity implements DataReceiver.CompleteData
        , PermissionControl.CallBack{

    @BindView(R.id.tempImage) ImageView tempImage;
    UserLocation userLocation = new UserLocation(this);
    DataReceiver dataReceiver = new DataReceiver(this);
    boolean isInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        UserLocation userLocation = new UserLocation(this);
        userLocation.getLocation();
    }

    @Override
    public void dataReceieveComplete() {
        Intent intent = new Intent(SplashActivity.this,PagerActivity.class);
        startActivity(intent);
    }
    @Override
    public Drawable setImage(String url) {
        Picasso.with(this).load(url).into(tempImage);
        return tempImage.getDrawable();
    }


    @Override
    public void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkVersion(this);
        } else {
            init();
        }

        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}
