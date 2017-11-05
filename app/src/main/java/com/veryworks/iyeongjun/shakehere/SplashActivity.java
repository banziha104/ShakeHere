package com.veryworks.iyeongjun.shakehere;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;
import com.veryworks.iyeongjun.shakehere.Util.PermissionControl;
import com.veryworks.iyeongjun.shakehere.Util.UserLocation;
import com.veryworks.iyeongjun.shakehere.domain.DataReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.veryworks.iyeongjun.shakehere.Util.UserSetting.userLanguage;
import static com.veryworks.iyeongjun.shakehere.Util.UserSetting.usercontentType;
import static com.veryworks.iyeongjun.shakehere.domain.StaticData.pasColor;
public class SplashActivity extends AppCompatActivity implements DataReceiver.CompleteData
        , PermissionControl.CallBack {
    boolean isOnce = false;
    boolean isInit = false;
    DataReceiver dataReceiver;
    @BindView(R.id.splashImg)
    ImageView splashImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        PermissionControl.checkVersion(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            checkVersion(this);
//        } else {
//            init();
//        }
        ButterKnife.bind(this);
        Typekit.getInstance().addNormal(Typekit.createFromAsset(this, "myfont.otf"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent.getBooleanExtra("isChange", false)) {
            splashImg.setImageResource(R.drawable.reload);
            Toast.makeText(this, "Data Change", Toast.LENGTH_SHORT).show();
            String lang = intent.getStringExtra("lang");
            String type = intent.getStringExtra("type");
            changeData(lang, type);
        }
    }

    private void changeData(String lang, String type) {
        if (lang != null) {
            userLanguage = lang;
        } else if (type != null) {
            usercontentType = type;
        }

    }

    @Override
    public void dataReceieveComplete() {
        Intent intent = new Intent(SplashActivity.this, PagerActivity.class);
        startActivity(intent);
        isOnce = true;
    }

    @Override
    public void init() {
        Log.d("PerMission", "init");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        Toast.makeText(this, "위치를 수신중입니다.", Toast.LENGTH_SHORT).show();
        UserLocation userLocation = new UserLocation(this);
        userLocation.getLocation();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionControl.onResult(this, requestCode, grantResults);
    }

    private void getColor() {
        TypedArray colors;
        colors = getResources().obtainTypedArray(R.array.colors);
        pasColor = new int[]{

        };
    }
}
