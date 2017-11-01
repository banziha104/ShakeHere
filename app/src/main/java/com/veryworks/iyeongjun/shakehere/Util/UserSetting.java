package com.veryworks.iyeongjun.shakehere.Util;

import com.veryworks.iyeongjun.shakehere.domain.Const;

/**
 * Created by iyeongjun on 2017. 10. 31..
 */

public class UserSetting {
    private boolean isCanUseSensor = true;
    private String userLanguage = Const.Lang.KOREAN;


    public boolean isCanUseSensor() {
        return isCanUseSensor;
    }

    public void setCanUseSensor(boolean canUseSensor) {
        isCanUseSensor = canUseSensor;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }
}
