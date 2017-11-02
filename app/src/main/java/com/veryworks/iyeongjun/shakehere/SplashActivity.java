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
import com.veryworks.iyeongjun.shakehere.domain.Const;
import com.veryworks.iyeongjun.shakehere.domain.DataReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.veryworks.iyeongjun.shakehere.Util.PermissionControl.checkVersion;
import static com.veryworks.iyeongjun.shakehere.domain.StaticData.datas;

public class SplashActivity extends AppCompatActivity implements DataReceiver.CompleteData, PermissionControl.CallBack {

    @BindView(R.id.tempImage) ImageView tempImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        DataReceiver dataReceiver = new DataReceiver(this);
        dataReceiver.getTourDataDefault(Const.Lang.KOREAN, 37.515359, 126.907623);
    }

    @Override
    public void dataReceieveComplete() {
        Intent intent = new Intent(SplashActivity.this, PagerActivity.class);
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
