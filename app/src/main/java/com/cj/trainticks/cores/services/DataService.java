package com.cj.trainticks.cores.services;

import com.cj.trainticks.cores.body.RegisterBody;
import com.cj.trainticks.model.Tuyen;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    //-----------------------ACCOUNT---------------------------------
    @POST("Customer/register")
    Call<Map>register(@Body() RegisterBody registerBody);

    @FormUrlEncoded
    @POST("Customer/checkEmail")
    Call<Map>checkEmail(@Field("email") String email);

    //-----------------------Tuyen---------------------------------
    @GET("Tuyen/getAllTuyen")
    Call<List<Tuyen>>getAllTuyen();
}
