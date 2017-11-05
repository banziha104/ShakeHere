package com.veryworks.iyeongjun.shakehere.Util;

import android.util.Log;

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
        if(type == TOUR || type == OTOUR) result = "관광";
        else if(type == COULTURE || type == OCOULTURE) result = "문화시설";
        else if(type == CANIVAL || type == OCANIVAL) result = "행사/공연/축제";
        else if(type == REPORTS || type == OREPORTS) result = "레포츠";
        else if(type == INN || type ==OINN) result = "숙박";
        else if(type == SHOPPING || type ==OSHOPPING) result = "쇼핑";
        else if(type == FOOD || type ==OFOOD) result = "음식점";
        else if(type == COURSE || type == OTANS) result = "여행코스";
        else if(type == ALL) result = "문화";
        else result = "기타";
        return result;
    }
    public static int convertIcon(String strType){
        int result = 0;
        int type = Integer.parseInt(strType);
        if(type == TOUR || type == OTOUR) result = myIcon[0];
        else if(type == COULTURE || type ==OCOULTURE) result = myIcon[1];
        else if(type == FOOD || type == OFOOD) result = myIcon[2];
        else if(type == REPORTS || type == OREPORTS) result = myIcon[3];
        else if(type == INN || type ==OINN) result = myIcon[4];
        else if(type == SHOPPING || type ==OSHOPPING) result = myIcon[5];
        else if(type == CANIVAL || type ==OCANIVAL) result = myIcon[6];
        else if(type == COURSE || type ==OTANS ) result = myIcon[7];
        return result;
    }
    public static int convertPin(String strType){
        int result = 0;
        int type = Integer.parseInt(strType);
        if(type == TOUR || type ==OTOUR) result = myPin[0];
        else if(type == COULTURE || type == OCOULTURE) result = myPin[1];
        else if(type == FOOD || type ==OFOOD) result = myPin[2];
        else if(type == REPORTS || type == OREPORTS) result = myPin[3];
        else if(type == INN || type ==OINN) result = myPin[4];
        else if(type == SHOPPING || type ==OSHOPPING) result = myPin[5];
        else if(type == CANIVAL || type ==OCANIVAL) result = myPin[6];
        else if(type == COURSE || type ==OTANS) result = myPin[7];
        return result;
    }
    public static int convertColor(String strType){
        int result = 0;
        int type = Integer.parseInt(strType);
        if(type == TOUR || type ==OTOUR) result = pasColor[0];
        else if(type == COULTURE || type ==OCOULTURE) result = pasColor[1];
        else if(type == FOOD || type == OFOOD) result = pasColor[2];
        else if(type == REPORTS || type == OREPORTS) result = pasColor[3];
        else if(type == INN || type ==OINN) result = pasColor[4];
        else if(type == SHOPPING || type == OSHOPPING) result = pasColor[5];
        else if(type == CANIVAL || type == OCANIVAL) result = pasColor[6];
        else if(type == COURSE || type ==OTANS) result = pasColor[7];
        return result;
    }
    public static int convertGra(String strType){
        int result = 0;
        int type = Integer.parseInt(strType);
        if(type == TOUR || type ==OTOUR) result = pasGrColor[0];
        else if(type == COULTURE || type ==OCOULTURE) result = pasGrColor[1];
        else if(type == FOOD || type ==OFOOD) result = pasGrColor[2];
        else if(type == REPORTS || type ==OREPORTS) result = pasGrColor[3];
        else if(type == INN || type ==OINN) result = pasGrColor[4];
        else if(type == SHOPPING || type ==OSHOPPING) result = pasGrColor[5];
        else if(type == CANIVAL || type ==OCANIVAL) result = pasGrColor[6];
        else if(type == COURSE || type == OTANS) result = pasGrColor[7];
        return result;
    }
}
