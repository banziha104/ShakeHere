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

import static com.veryworks.iyeongjun.shakehere.domain.StaticData.datas;
import static com.veryworks.iyeongjun.shakehere.domain.StaticFields.currentPageNo;

/**
 * Created by iyeongjun on 2017. 10. 31..
 */

public class DataReceiver {
    static Retrofit retrofit;
    Context context;

    public DataReceiver(Context context) {
        this.context = context;
    }

    public void getTourData(String lang , double lat, double lon){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.visitkorea.or.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceForTourdata interfaceForTourdata
                = retrofit.create(InterfaceForTourdata.class);
        Call<TourData> result = interfaceForTourdata.getTourData(lang,
                Const.Auth.KEY,
                Const.DefaultSetting.DEFAULT_NUM_OF_ROWS,
                Const.DefaultSetting.DEFAULT_MOBILE_OS,
                Const.DefaultSetting.APP_NAME,
                lon,
                lat,
                Const.DefaultSetting.DEFAULT_RADIUS,
                Const.DefaultSetting.DEFAULT_TYPE,
                currentPageNo);
        result.enqueue(new Callback<TourData>() {
            @Override
            public void onResponse(Call<TourData> call, Response<TourData> response) {
                ifSeccess(response);
            }

            @Override
            public void onFailure(Call<TourData> call, Throwable t) {

            }
        });
    }

    public void getTourDataWithType(String lang, int contentType, double lat, double lon){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.visitkorea.or.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceForTourdataWithType interfaceForTourdata
                = retrofit.create(InterfaceForTourdataWithType.class);
        Call<TourData> result = interfaceForTourdata.getTourData(lang,
                Const.Auth.KEY,
                Const.DefaultSetting.DEFAULT_NUM_OF_ROWS,
                Const.DefaultSetting.DEFAULT_MOBILE_OS,
                Const.DefaultSetting.APP_NAME,
                contentType,
                lon,
                lat,
                Const.DefaultSetting.DEFAULT_RADIUS,
                Const.DefaultSetting.DEFAULT_TYPE,
                currentPageNo);
        result.enqueue(new Callback<TourData>() {
            @Override
            public void onResponse(Call<TourData> call, Response<TourData> response) {
                ifSeccess(response);
            }

            @Override
            public void onFailure(Call<TourData> call, Throwable t) {

            }
        });
    }

    public void getTourDataDefault(String lang, double lat, double lon){
        Log.d("LOCATION","getTourData");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.visitkorea.or.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceForTourdataWithOutContentType interfaceForTourdataWithOutContentType
                = retrofit.create(InterfaceForTourdataWithOutContentType.class);
        Call<TourData> result = interfaceForTourdataWithOutContentType.getTourData(lang,
                Const.Auth.KEY,
                Const.DefaultSetting.DEFAULT_NUM_OF_ROWS,
                Const.DefaultSetting.DEFAULT_MOBILE_OS,
                Const.DefaultSetting.APP_NAME,
                lon,
                lat,
                Const.DefaultSetting.DEFAULT_RADIUS,
                Const.DefaultSetting.DEFAULT_TYPE,
                currentPageNo);
        result.enqueue(new Callback<TourData>() {
            @Override
            public void onResponse(Call<TourData> call, Response<TourData> response) {
                ifSeccess(response);
            }

            @Override
            public void onFailure(Call<TourData> call, Throwable t) {
            }
        });
    }

    interface InterfaceForTourdata{
        @GET("/openapi/service/rest/{lang}/locationBasedList")
        Call<TourData> getTourData(@Path("lang") String Lang,
                                   @Query(value = "serviceKey",encoded = true) String key,
                                   @Query("numOfRows") int numOfPage,
                                   @Query("MobileOS") String mobileOS,
                                   @Query("MobileApp") String mobileApp,
                                   @Query("mapX") double mapX,
                                   @Query("mapY") double mapY,
                                   @Query("radius") int radius,
                                   @Query("_type") String type,
                                   @Query("pageNo") int Pageno
                                   );
    }
    interface InterfaceForTourdataWithType{
        @GET("/openapi/service/rest/{lang}/locationBasedList")
        Call<TourData> getTourData(@Path("lang") String Lang,
                                   @Query(value = "serviceKey",encoded = true) String key,
                                   @Query("numOfRows") int numOfPage,
                                   @Query("MobileOS") String mobileOS,
                                   @Query("MobileApp") String mobileApp,
                                   @Query("contentTypeId") int contentType,
                                   @Query("mapX") double mapX,
                                   @Query("mapY") double mapY,
                                   @Query("radius") int radius,
                                   @Query("_type") String type,
                                   @Query("pageNo") int Pageno
        );
    }
    interface InterfaceForTourdataWithOutContentType{
        @GET("/openapi/service/rest/{lang}/locationBasedList")
        Call<TourData> getTourData(@Path("lang") String Lang,
                                   @Query(value = "serviceKey", encoded = true) String key,
                                   @Query("numOfRows") int numOfPage,
                                   @Query("MobileOS") String mobileOS,
                                   @Query("MobileApp") String mobileApp,
                                   @Query("mapX") double mapX,
                                   @Query("mapY") double mapY,
                                   @Query("radius") int radius,
                                   @Query("_type") String type,
                                   @Query("pageNo") int Pageno
        );
    }

    private void ifSeccess(Response<TourData> response){
        Item[] items = response.body().getResponse().getBody().getItems().getItem();
        if(datas != null) datas.clear();
        for(int i = 0 ; i < items.length ; i ++){
            datas.add(items[i]);
            Log.d("Data",i+datas.get(i).getTitle()+"/"+
                    i+datas.get(i).getTel()+"/"+
                    i+datas.get(i).getSigungucode()+"/"+
                    i+datas.get(i).getDist()+"/"+
                    i+datas.get(i).getFirstimage()+"/"+
                    i+datas.get(i).getFirstimage2()+"/"+
                    i+datas.get(i).getAddr1()+"/"+
                    i+datas.get(i).getAddr2()+"/"+
                    i+datas.get(i).getTel()+"/"
                    +datas.get(i).getContenttypeid());
            Log.d("Image",datas.get(i).getFirstimage2()+"/"+datas.get(i).getContenttypeid()+"/");
        }
        if (context instanceof CompleteData){
            ((CompleteData)context).dataReceieveComplete();
            Log.d("Callback","callback");
        }else{
            Log.d("Callback","not callback");
        }
    }

    public interface CompleteData{
        void dataReceieveComplete();
    }

//    interface InterfaceForGetSeoulData{
//        @GET("/{API_KEY}/json/SearchConcertDetailService/{START}/{END}/")
//        Call<SeoulData> getSeoulData(@Path("API_KEY") String apikey,
//                                     @Path("START") int start,
//                                     @Path("END") int end);
//    }
    //http://api.visitkorea.or.kr/openapi/service/rest/EngService/areaBasedList
}
