package com.cj.trainticks.src.page.home;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.cj.trainticks.R;
import com.cj.trainticks.base.BaseActivity;
import com.cj.trainticks.cores.reponse.GetToaTauReponse;
import com.cj.trainticks.cores.reponse.TimChuyenTauReponse;
import com.cj.trainticks.cores.services.APIServices;
import com.cj.trainticks.cores.services.DataService;
import com.cj.trainticks.utils.Constain;
import com.google.gson.Gson;
import com.uits.baseproject.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChonVeTauActivity extends BaseActivity implements View.OnClickListener {
    private TimChuyenTauReponse mTauReponse;
    private Gson mGson = new Gson();
    private TextView mTxtTenChuyen;

    @Override
    protected int getContentView() {
        return R.layout.chon_ve_tau_activtity;
    }

    @Override
    protected void onInitView() {
        mTxtTenChuyen = findViewById(R.id.txt_ten_chuyen);
    }

    @Override
    protected void onListenerClicked() {
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    @Override
    protected void onInit() {
        getToaTau();

    }

    private void getToaTau() {
        Intent intent  = getIntent();
        String reponse = intent.getStringExtra(Constain.keyMaChuyen);
        mTauReponse = mGson.fromJson(reponse,TimChuyenTauReponse.class);

        mTxtTenChuyen.setText(mTauReponse.getGADI()+" - "+mTauReponse.getGADEN()+" "+mTauReponse.getGIODI()+" - "+mTauReponse.getNGAYDI());

        DataService dataService = APIServices.getService();
        Call<GetToaTauReponse>call = dataService.getToaTauTheoChuyen(mTauReponse.getTENTAU());
        call.enqueue(new Callback<GetToaTauReponse>() {
            @Override
            public void onResponse(Call<GetToaTauReponse> call, Response<GetToaTauReponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatuscode().toString().equals("200")){
                        showAlertDialog(mGson.toJson(response.body()));
                    }else{
                        showAlertDialog(response.body().getMessage());
                    }
                }else{
                    showAlertDialog(response.message());
                }
            }

            @Override
            public void onFailure(Call<GetToaTauReponse> call, Throwable t) {
                showAlertDialog(t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }
}
