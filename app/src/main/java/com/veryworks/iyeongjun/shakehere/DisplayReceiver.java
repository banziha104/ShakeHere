package com.veryworks.iyeongjun.shakehere;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.veryworks.iyeongjun.shakehere.domain.Const;
import com.veryworks.iyeongjun.shakehere.domain.Const;

import static com.veryworks.iyeongjun.shakehere.StaticStatus.isServiceRan;
import static com.veryworks.iyeongjun.shakehere.StaticStatus.ScreenStatus;
import static com.veryworks.iyeongjun.shakehere.StaticStatus.isServiceRan;


public class DisplayReceiver extends BroadcastReceiver {
    private final String TAG = "DisplayReceiver";
    ShakeController shakeController;
    Intent shakeServiceIntent;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"Start Display Receiver");
        if(shakeServiceIntent == null){
            shakeServiceIntent = new Intent(context,ShakeDetectService.class);
        }
        switch (intent.getAction()){
            case Intent.ACTION_SCREEN_ON:
                setStatus(Const.Status.SCREEN_ON);
                shakeController.onShakeDetector();
                break;
            case Intent.ACTION_SCREEN_OFF:
                setStatus(Const.Status.SCREEN_OFF);
                shakeController.offShakeDetector();
                break;
            case Intent.ACTION_BOOT_COMPLETED:
                setStatus(Const.Status.BOOT_COMPLETE);
                if(isServiceRan == false)context.startService(shakeServiceIntent);
                break;
        }
    }
    private void setStatus(String status){
        ScreenStatus = status;
        Log.d(TAG,status);
    }
    public void setShakeServiceContext(Context context){
        shakeController = (ShakeController)context;
    }
    interface ShakeController{
        void onShakeDetector();
        void offShakeDetector();
    }
}
