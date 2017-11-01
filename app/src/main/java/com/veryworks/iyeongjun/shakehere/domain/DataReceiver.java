package com.veryworks.iyeongjun.shakehere.domain;

import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by iyeongjun on 2017. 10. 31..
 */

public class DataReceiver {
    Retrofit retrofit;
    Context context;

    public DataReceiver(Context context) {
        this.context = context;
    }
    public void getTourData(String lang, int length , String contentType,double lat, double lon, int radius){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.visitkorea.or.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceForTourdata interfaceForTourdata
                = retrofit.create(InterfaceForTourdata.class);
        Call<TourData> result = interfaceForTourdata.getTourData(lang,
                length,
                Const.DefaultSetting.DEFAULT_MOBILE_OS,
                Const.DefaultSetting.APP_NAME,
                Const.Auth.KEY,
                contentType,
                lat,
                lon,
                radius,
                Const.DefaultSetting.DEFAULT_TYPE);
        result.enqueue(new Callback<TourData>() {
            @Override
            public void onResponse(Call<TourData> call, Response<TourData> response) {
                Log.d("Data",response.body().toString());
            }

            @Override
            public void onFailure(Call<TourData> call, Throwable t) {

            }
        });
    }
    interface InterfaceForTourdata{
        @GET("/openapi/service/rest/{lang}/locationBasedList/")
        Call<TourData> getTourData(@Path("lang") String Lang,
                                   @Query("numOfRows") int numOfPage,
                                   @Query("MobileOS") String mobileOS,
                                   @Query("MobileApp") String mobileApp,
                                   @Query("ServiceKey") String key,
                                   @Query("contentTypeId") String contentType,
                                   @Query("mapX") double mapX,
                                   @Query("mapY") double mapY,
                                   @Query("radius") int radius,
                                   @Query("_type") String type
                                   );
    }
    interface AdapterCallback{
        void AdapterCallback();
    }

//    interface InterfaceForGetSeoulData{
//        @GET("/{API_KEY}/json/SearchConcertDetailService/{START}/{END}/")
//        Call<SeoulData> getSeoulData(@Path("API_KEY") String apikey,
//                                     @Path("START") int start,
//                                     @Path("END") int end);
//    }
    //http://api.visitkorea.or.kr/openapi/service/rest/EngService/areaBasedList
}
