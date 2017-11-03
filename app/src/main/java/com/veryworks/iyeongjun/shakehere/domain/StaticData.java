package com.veryworks.iyeongjun.shakehere.domain;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import com.veryworks.iyeongjun.shakehere.R;

import java.util.ArrayList;

/**
 * Created by iyeongjun on 2017. 10. 31..
 */

public class StaticData {
    public static ArrayList<Item> datas = new ArrayList<>();
    public static ArrayList<Drawable> drawables = new ArrayList<>();
    public static int[] pasColor = {
            R.color.pRd,
            R.color.pJu,
            R.color.pNo,
            R.color.pGr,
            R.color.pBl,
            R.color.pNM,
            R.color.pBO,
            R.color.pGC
    };
    public static int[] pasGrColor = {
            R.color.pgRd,
            R.color.pgJu,
            R.color.pgNo,
            R.color.pgGr,
            R.color.pgBl,
            R.color.pgNM,
            R.color.pgBO,
            R.color.pgGC
    };
    public static int[] myIcon = {
            R.drawable.tour_icon,
            R.drawable.culture_icon,
            R.drawable.food_icon,
            R.drawable.reports_icon,
            R.drawable.inn_icon,
            R.drawable.shopping_icon,
            R.drawable.canival_icon,
            R.drawable.course_icon
    };
    public static int[] myPin = {
            R.drawable.tour_pin,
            R.drawable.culture_pin,
            R.drawable.food_pin,
            R.drawable.reports_pin,
            R.drawable.inn_pin,
            R.drawable.shopping_pin,
            R.drawable.canival_pin,
            R.drawable.course_pin
    };
}
