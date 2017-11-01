package com.veryworks.iyeongjun.shakehere.domain;

import android.content.Context;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

    public void getTourData(String lang, int length , int contentType, double lat, double lon){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.visitkorea.or.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceForTourdata interfaceForTourdata
                = retrofit.create(InterfaceForTourdata.class);
        Call<TourData> result = interfaceForTourdata.getTourData(lang,
                Const.Auth.KEY,
                length,
                Const.DefaultSetting.DEFAULT_MOBILE_OS,
                Const.DefaultSetting.APP_NAME,
                contentType,
                lat,
                lon,
                Const.DefaultSetting.DEFAULT_RADIUS,
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
    public void getTourDataDefault(String lang, int length , double lat, double lon){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.visitkorea.or.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceForTourdataWithOutContentType interfaceForTourdataWithOutContentType
                = retrofit.create(InterfaceForTourdataWithOutContentType.class);
        Call<TourData> result = interfaceForTourdataWithOutContentType.getTourData(lang,
                URLencoding(Const.Auth.KEY),
                length,
                Const.DefaultSetting.DEFAULT_MOBILE_OS,
                Const.DefaultSetting.APP_NAME,
                lat,
                lon,
                Const.DefaultSetting.DEFAULT_RADIUS,
                Const.DefaultSetting.DEFAULT_TYPE);
        result.enqueue(new Callback<TourData>() {
            @Override
            public void onResponse(Call<TourData> call, Response<TourData> response) {
                Log.d("Data",response.body().toString());
                Log.d("Data",response.headers().toString());
                Log.d("Data",response.toString());

            }

            @Override
            public void onFailure(Call<TourData> call, Throwable t) {
                Log.d("Data",t.getMessage());
            }
        });
    }

    interface InterfaceForTourdata{
        @GET("/openapi/service/rest/{lang}/locationBasedList")
        Call<TourData> getTourData(@Path("lang") String Lang,
                                   @Query("serviceKey") String key,
                                   @Query("numOfRows") int numOfPage,
                                   @Query("MobileOS") String mobileOS,
                                   @Query("MobileApp") String mobileApp,
                                   @Query("contentTypeId") int contentType,
                                   @Query("mapX") double mapX,
                                   @Query("mapY") double mapY,
                                   @Query("radius") int radius,
                                   @Query("_type") String type
                                   );
    }
    interface InterfaceForTourdataWithOutContentType{
        @GET("/openapi/service/rest/{lang}/locationBasedList")
        Call<TourData> getTourData(@Path("lang") String Lang,
                                   @Query("serviceKey") String key,
                                   @Query("numOfRows") int numOfPage,
                                   @Query("MobileOS") String mobileOS,
                                   @Query("MobileApp") String mobileApp,
                                   @Query("mapX") double mapX,
                                   @Query("mapY") double mapY,
                                   @Query("radius") int radius,
                                   @Query("_type") String type
        );
    }
    private String URLencoding(String key){
        try {
            Log.d("Data",URLEncoder.encode(key,"UTF-8"));
            Log.d("Data",URLEncoder.encode(key));
            return URLEncoder.encode(key,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * http://api.visitkorea.or.kr/openapi/service/rest/KorService/
     * locationBasedList?numOfRows=40&MobileOS=AND&MobileApp=ShakeHere&
     * serviceKey=i9opnT0CNWj0dfjeUmoProOy3c%252BqZNdfztvalVl624EISpMpkXLDvVzwuuA8n8BnYnMqOjKlZIoBQLm%252FpX%252Fyqg%253D%253D
     * &mapX=37.515359&mapY=126.907623&radius=5000&_type=json
     *
     *http://api.visitkorea.or.kr/openapi/service/rest/EngService/
     * locationBasedList?
     * i9opnT0CNWj0dfjeUmoProOy3c%252BqZNdfztvalVl624EISpMpkXLDvVzwuuA8n8BnYnMqOjKlZIoBQLm%252FpX%252Fyqg%253D%253D
     * serviceKey=i9opnT0CNWj0dfjeUmoProOy3c%2BqZNdfztvalVl624EISpMpkXLDvVzwuuA8n8BnYnMqOjKlZIoBQLm%2FpX%2Fyqg%3D%3D&numOfRows=10&pageSize=10&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&listYN=Y&arrange=A&contentTypeId=82&mapX=126.981611&mapY=37.568477&radius=500
     * serviceKey=i9opnT0CNWj0dfjeUmoProOy3c%25252BqZNdfztvalVl624EISpMpkXLDvVzwuuA8n8BnYnMqOjKlZIoBQLm%25252FpX%25252Fyqg
     * http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList
     * ?serviceKey=i9opnT0CNWj0dfjeUmoProOy3c%25252BqZNdfztvalVl624EISpMpkXLDvVzwuuA8n8BnYnMqOjKlZIoBQLm%25252FpX%25252Fyqg%25253D%25253D&numOfRows=10&MobileOS=AND&MobileApp=ShakeHere&mapX=37.515359&mapY=126.907623&radius=5000&_type=json
     */

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
