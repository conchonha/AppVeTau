package com.cj.trainticks.src.page.home;

import android.view.View;

import com.cj.trainticks.R;
import com.cj.trainticks.base.BaseActivity;

public class ChonVeTauActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getContentView() {
        return R.layout.chon_ve_tau_activtity;
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
