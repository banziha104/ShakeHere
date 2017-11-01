package com.veryworks.iyeongjun.shakehere;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.veryworks.iyeongjun.shakehere.Util.PermissionControl;
import com.veryworks.iyeongjun.shakehere.domain.Const;
import com.veryworks.iyeongjun.shakehere.domain.DataReceiver;

import butterknife.ButterKnife;

import static com.veryworks.iyeongjun.shakehere.Util.PermissionControl.checkVersion;


public class MainActivity extends AppCompatActivity implements PermissionControl.CallBack{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataReceiver dataReceiver = new DataReceiver(this);
        dataReceiver.getTourDataDefault(Const.Lang.KOREAN,10, 37.515359, 126.907623);
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
