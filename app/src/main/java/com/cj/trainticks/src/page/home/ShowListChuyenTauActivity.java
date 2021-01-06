package com.cj.trainticks.src.page.home;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cj.trainticks.R;
import com.cj.trainticks.base.BaseActivity;
import com.cj.trainticks.cores.reponse.TimChuyenTauReponse;
import com.cj.trainticks.src.adapter.home.ShowListChuyenTauAdapter;
import com.cj.trainticks.utils.Constain;
import com.cj.trainticks.utils.Helpers;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ShowListChuyenTauActivity extends BaseActivity implements View.OnClickListener {
    private Gson mGson = new Gson();
    private List<TimChuyenTauReponse> mList = new ArrayList<>();
    private ShowListChuyenTauAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected int getContentView() {
        return R.layout.show_list_chuyen_tau_activity;
    }

    @Override
    protected void onInitView() {
        mRecyclerView = findViewById(R.id.recyclerview_chon_chuyen_tau);
    }

    @Override
    protected void onListenerClicked() {
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    @Override
    protected void onInit() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        getListChuyenTau();
    }

    private void getListChuyenTau() {
        Intent intent = getIntent();
        String reponse = intent.getStringExtra(Constain.keyListChuyenTau);

        try {
            JSONArray jsonArray = new JSONArray(reponse);
            for (int i = 0; i < jsonArray.length(); i++){
                String jsonObject = jsonArray.getJSONObject(i).toString();
                TimChuyenTauReponse tauReponse = mGson.fromJson(jsonObject,TimChuyenTauReponse.class);
                mList.add(tauReponse);
            }
            mAdapter = new ShowListChuyenTauAdapter(mList,ShowListChuyenTauActivity.this);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void adapterItemClicked(int maChuyen){
        startActivity(new Intent(getApplicationContext(),ChonVeTauActivity.class).putExtra(Constain.keyMaChuyen,maChuyen));
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
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
