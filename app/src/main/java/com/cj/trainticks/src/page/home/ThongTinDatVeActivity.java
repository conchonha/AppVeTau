package com.cj.trainticks.src.page.home;

import android.content.Intent;

import com.cj.trainticks.R;
import com.cj.trainticks.base.BaseActivity;
import com.cj.trainticks.cores.services.APIServices;
import com.cj.trainticks.cores.services.DataService;
import com.cj.trainticks.model.User;
import com.cj.trainticks.model.VeTau;
import com.cj.trainticks.utils.Constain;
import com.cj.trainticks.utils.SharePrefs;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTinDatVeActivity extends BaseActivity {
    private Gson mGson = new Gson();

    @Override
    protected int getContentView() {
        return R.layout.thong_tin_dat_ve_activity;
    }

    @Override
    protected void onInitView() {

    }

    @Override
    protected void onListenerClicked() {

    }

    @Override
    protected void onInit() {
        Intent intent = getIntent();
        String reponse = intent.getStringExtra(Constain.keyVeTau);
        User user = mGson.fromJson(new SharePrefs(getApplicationContext()).getUser(),User.class);
        DataService dataService = APIServices.getService();
        Call<Map> call = dataService.datVeTau(user.getId().toString(), reponse);
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if (response.isSuccessful()) {
                    showAlertDialog(mGson.toJson(response.body().get("message")));
                } else {
                    showAlertDialog(response.message());
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                showAlertDialog(t.getMessage());
            }
        });
    }
}
