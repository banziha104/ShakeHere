package com.veryworks.iyeongjun.shakehere.Util;

import static com.veryworks.iyeongjun.shakehere.domain.Const.ContentType.*;
import static com.veryworks.iyeongjun.shakehere.domain.StaticData.myIcon;
import static com.veryworks.iyeongjun.shakehere.domain.StaticData.myPin;
import static com.veryworks.iyeongjun.shakehere.domain.StaticData.pasColor;
import static com.veryworks.iyeongjun.shakehere.domain.StaticData.pasGrColor;

/**
 * Created by iyeongjun on 2017. 11. 3..
 */

public class MyUtil {
    public static String convertType(String strType){
        String result = "";
        int type = Integer.parseInt(strType);
        if(type == TOUR) result = "관광";
        else if(type == COULTURE) result = "문화시설";
        else if(type == CANIVAL) result = "행사/공연/축제";
        else if(type == REPORTS) result = "레포츠";
        else if(type == INN) result = "숙박";
        else if(type == SHOPPING) result = "쇼핑";
        else if(type == FOOD) result = "음식점";
        else if(type == ALL) result = "문화";
        else result = "기타";
        return result;
    }
    public static int convertIcon(String strType){
        int result = 0;
        int type = Integer.parseInt(strType);
        if(type == TOUR) result = myIcon[0];
        else if(type == COULTURE) result = myIcon[1];
        else if(type == FOOD) result = myIcon[2];
        else if(type == REPORTS) result = myIcon[3];
        else if(type == INN) result = myIcon[4];
        else if(type == SHOPPING) result = myIcon[5];
        else if(type == CANIVAL) result = myIcon[6];
        return result;
    }
    public static int convertPin(String strType){
        int result = 0;
        int type = Integer.parseInt(strType);
        if(type == TOUR) result = myPin[0];
        else if(type == COULTURE) result = myPin[1];
        else if(type == FOOD) result = myPin[2];
        else if(type == REPORTS) result = myPin[3];
        else if(type == INN) result = myPin[4];
        else if(type == SHOPPING) result = myPin[5];
        else if(type == CANIVAL) result = myPin[6];
        return result;
    }
    public static int convertColor(String strType){
        int result = 0;
        int type = Integer.parseInt(strType);
        if(type == TOUR) result = pasColor[0];
        else if(type == COULTURE) result = pasColor[1];
        else if(type == FOOD) result = pasColor[2];
        else if(type == REPORTS) result = pasColor[3];
        else if(type == INN) result = pasColor[4];
        else if(type == SHOPPING) result = pasColor[5];
        else if(type == CANIVAL) result = pasColor[6];
        return result;
    }
    public static int convertGra(String strType){
        int result = 0;
        int type = Integer.parseInt(strType);
        if(type == TOUR) result = pasGrColor[0];
        else if(type == COULTURE) result = pasGrColor[1];
        else if(type == FOOD) result = pasGrColor[2];
        else if(type == REPORTS) result = pasGrColor[3];
        else if(type == INN) result = pasGrColor[4];
        else if(type == SHOPPING) result = pasGrColor[5];
        else if(type == CANIVAL) result = pasGrColor[6];
        return result;
    }
}
