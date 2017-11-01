package com.veryworks.iyeongjun.shakehere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.veryworks.iyeongjun.shakehere.domain.Const;
import com.veryworks.iyeongjun.shakehere.domain.DataReceiver;

public class SplashActivity extends AppCompatActivity implements DataReceiver.CompleteData{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DataReceiver dataReceiver = new DataReceiver(this);
        dataReceiver.getTourDataDefault(Const.Lang.KOREAN, 37.515359, 126.907623);
    }

    @Override
    public void dataReceieveComplete() {
        Intent intent = new Intent(SplashActivity.this,PagerActivity.class);
        startActivity(intent);
    }
}
