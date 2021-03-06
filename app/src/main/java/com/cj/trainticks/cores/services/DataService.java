package com.cj.trainticks.cores.services;

import com.cj.trainticks.cores.body.RegisterBody;
import com.cj.trainticks.cores.reponse.GetToaTauReponse;
import com.cj.trainticks.model.Tuyen;
import com.cj.trainticks.model.VeTau;

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

    @FormUrlEncoded
    @POST("Tuyen/timChuyenTau")
    Call<Map>timChuyenTau(@Field("gadi") String gadi,@Field("gaden") String gaden,@Field("thoigian") String thoigian);

    @FormUrlEncoded
    @POST("Tuyen/getToaTauTheoChuyen")
    Call<GetToaTauReponse>getToaTauTheoChuyen(@Field("tentau") String tentau);

    @FormUrlEncoded
    @POST("Tuyen/getVetau")
    Call<List<VeTau>>getVetau(@Field("toaid") String toaid);

    @FormUrlEncoded
    @POST("Tuyen/datve")
    Call<Map>datVeTau(@Field("maChuyen") String maChuyen,@Field("arrayVe") String ve);
}
