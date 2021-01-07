package com.cj.trainticks.src.page.home;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cj.trainticks.R;
import com.cj.trainticks.base.BaseActivity;
import com.cj.trainticks.cores.reponse.GetToaTauReponse;
import com.cj.trainticks.cores.reponse.TimChuyenTauReponse;
import com.cj.trainticks.cores.services.APIServices;
import com.cj.trainticks.cores.services.DataService;
import com.cj.trainticks.model.Toa;
import com.cj.trainticks.model.VeTau;
import com.cj.trainticks.src.adapter.home.ToaAdapter;
import com.cj.trainticks.src.adapter.home.VetauAdapter;
import com.cj.trainticks.utils.Constain;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChonVeTauActivity extends BaseActivity implements View.OnClickListener {
    private TimChuyenTauReponse mTauReponse;
    private Gson mGson = new Gson();
    private TextView mTxtTenChuyen,mTxtThongTinToa;
    private RecyclerView mRecyclerviewToa,mRecyclerviewGhe;
    private ToaAdapter mAdapterToa;

    @Override
    protected int getContentView() {
        return R.layout.chon_ve_tau_activtity;
    }

    @Override
    protected void onInitView() {
        mTxtTenChuyen = findViewById(R.id.txt_ten_chuyen);
        mRecyclerviewToa = findViewById(R.id.recyclerview_toa);
        mTxtThongTinToa = findViewById(R.id.txt_thong_tin_toa);
        mRecyclerviewGhe = findViewById(R.id.recyclerviewghe);
    }

    @Override
    protected void onListenerClicked() {
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    @Override
    protected void onInit() {
        initRecyclerView();
        getToaTau();
    }

    public void onItemClickAdapter(Toa toa){
        DataService dataService = APIServices.getService();
        Call<List<VeTau>>call = dataService.getVetau(toa.getId().toString());

        mTxtThongTinToa.setText(toa.getCHUTHICHTOA());
        showProgressDialog(getString(R.string.loading),"");
        call.enqueue(new Callback<List<VeTau>>() {
            @Override
            public void onResponse(Call<List<VeTau>> call, Response<List<VeTau>> response) {
                hideDialog();
                if(response.isSuccessful()){
                    VetauAdapter adapter = new VetauAdapter(response.body(),ChonVeTauActivity.this);
                    mRecyclerviewGhe.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                    showAlertDialog(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<VeTau>> call, Throwable t) {
                hideDialog();
                showAlertDialog(t.getMessage());
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerviewToa.setHasFixedSize(true);
        mRecyclerviewToa.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

        mRecyclerviewGhe.setHasFixedSize(true);
        mRecyclerviewGhe.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));
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
                        mAdapterToa = new ToaAdapter(response.body().getData(),ChonVeTauActivity.this);
                        mRecyclerviewToa.setAdapter(mAdapterToa);
                        mAdapterToa.notifyDataSetChanged();
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
