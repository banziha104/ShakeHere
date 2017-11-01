package com.veryworks.iyeongjun.shakehere.domain;

import java.net.URLEncoder;

/**
 * Created by iyeongjun on 2017. 9. 27..
 */

public class Const {
    public static class Auth{
        public static final String GOOGLE_MAP_KEY ="AIzaSyChOAuUgCbADQ5vcgzOloV0J6-SloAASWk";
        public static final String KEY = "iMw%2F5Z0wNdwfRXJ4HVyIGeMk316OS1Wtsw8v7ItPa3L%2BcmRVKGv%2BBB0k1rit2uwBOuoRnoYOL7%2Bh6EIyblceCQ%3D%3D";
                }
    public static class Status{
        public static final String SCREEN_ON = "Screen on";
        public static final String SCREEN_OFF = "Screen off";
        public static final String BOOT_COMPLETE = "Boot complete";
    }
    public static class Lang{
        public static final String KOREAN = "KorService";
        public static final String ENGLISH = "EngService";
        public static final String CHINA_GAN = "ChsService";
        public static final String CHINA_BUN = "ChtService";
        public static final String GERMAN = "GerService";
        public static final String FRANCE = "FreService";
        public static final String SPAIN = "SpnService";
        public static final String RUSSIA = "RusService";
    }
    public static class Action{
        public static final String PAUSE_SENSOR = "";
        public static final String RESUME_SENSOR = "";
    }
    public static class GPS{
        public static final int GPS_MILE_SECOND = 100;
        public static final int GPS_MIN_LENGTH = 1;
        public static final int GPS_DEFAULT_RESULT = 1;

    }
    public static class DefaultSetting{
        public static final int DEFAULT_NUM_OF_ROWS = 50;
        public static final String DEFAULT_MOBILE_OS = "AND";
        public static final String APP_NAME = "ShakeHere";
        public static final int DEFAULT_RADIUS = 5000;
        public static final String DEFAULT_TYPE = "json";
    }
    public static class ContentType{
        public static final int TOUR = 76;
        public static final int COULTURE = 78;
        public static final int CANIVAL = 85;
        public static final int REPORTS = 75;
        public static final int INN = 80;
        public static final int SHOPPING = 79;
        public static final int ALL = 1000;
    }
}
