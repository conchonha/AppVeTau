package com.cj.trainticks.src.page.home;

import android.content.Intent;
import android.view.View;

import com.cj.trainticks.R;
import com.cj.trainticks.base.BaseActivity;
import com.cj.trainticks.utils.Constain;
import com.google.gson.JsonArray;

import org.json.JSONArray;

public class ShowListChuyenTauActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getContentView() {
        return R.layout.show_list_chuyen_tau_activity;
    }

    @Override
    protected void onInitView() {

    }

    @Override
    protected void onListenerClicked() {
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    @Override
    protected void onInit() {
//        Intent intent = getIntent();
//        String reponse = intent.getStringExtra(Constain.keyListChuyenTau);
//        JSONArray jsonArray 
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
