package com.cj.trainticks.src.page.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
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
import com.cj.trainticks.src.adapter.home.VeDuocChonAdapter;
import com.cj.trainticks.src.adapter.home.VetauAdapter;
import com.cj.trainticks.src.page.user.LoginActivity;
import com.cj.trainticks.utils.Constain;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChonVeTauActivity extends BaseActivity implements View.OnClickListener {
    private TimChuyenTauReponse mTauReponse;
    private Gson mGson = new Gson();
    private TextView mTxtTenChuyen,mTxtThongTinToa,mTxtTongTien;
    private RecyclerView mRecyclerviewToa,mRecyclerviewGhe,mRecyclerviewLuaChon;
    private ToaAdapter mAdapterToa;
    private List<VeTau>mListVeTau = new ArrayList<>();
    private VetauAdapter mAdapterVeTau;
    private List<VeTau> mListVeTauDaChon = new ArrayList<>();
    private VeDuocChonAdapter mVeDuocChonAdapter;
    private DecimalFormat mSimpleDateFortmat = new DecimalFormat("#,###,###");
    private String TAG = "ChonVeTauActivity";
    private final int REQUESTCODE = 1111;

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
        mRecyclerviewLuaChon= findViewById(R.id.recyclerview_lua_chon_ve);
        mTxtTongTien = findViewById(R.id.txt_tong_tien);
    }

    @Override
    protected void onListenerClicked() {
        findViewById(R.id.img_back).setOnClickListener(this);
        findViewById(R.id.txt_tiep_tuc).setOnClickListener(this);
    }

    @Override
    protected void onInit() {
        initRecyclerView();
        getToaTau();
    }

    public void onItemClickAdapter(Toa toa){
        mListVeTauDaChon.clear();
        settongtien();
        DataService dataService = APIServices.getService();
        Call<List<VeTau>>call = dataService.getVetau(toa.getId().toString());

        mTxtThongTinToa.setText(toa.getCHUTHICHTOA());
        showProgressDialog(getString(R.string.loading),"");
        call.enqueue(new Callback<List<VeTau>>() {
            @Override
            public void onResponse(Call<List<VeTau>> call, Response<List<VeTau>> response) {
                hideDialog();
                if(response.isSuccessful()){
                    mListVeTau =response.body();
                    mAdapterVeTau = new VetauAdapter(mListVeTau,ChonVeTauActivity.this);
                    mRecyclerviewGhe.setAdapter(mAdapterVeTau);
                    mAdapterVeTau.notifyDataSetChanged();
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

    public void onclicedItemVeTauAdapter(VeTau veTau){
        for (int i = 0; i < mListVeTau.size() ; i++){
            if(mListVeTau.get(i).getId().equals(veTau.getId())){
                mListVeTau.get(i).setmCheck(true);
                mAdapterVeTau.notifyDataSetChanged();
            }
        }

        mVeDuocChonAdapter = new VeDuocChonAdapter(mListVeTauDaChon,ChonVeTauActivity.this);
        mRecyclerviewLuaChon.setAdapter(mVeDuocChonAdapter);
        mVeDuocChonAdapter.notifyDataSetChanged();

        if(mListVeTauDaChon.size() < 4){
            mListVeTauDaChon.add(veTau);
            mVeDuocChonAdapter.notifyDataSetChanged();
        }else{
            showAlertDialog(getString(R.string.chon_toi_da_ve_tau));
        }
        settongtien();
    }

    private void settongtien(){
        int tongtien = 0;
        for (VeTau veTau1 : mListVeTauDaChon){
            tongtien += veTau1.getGIAVE();
        }
        mTxtTongTien.setText(mSimpleDateFortmat.format(tongtien));
    }
    private void initRecyclerView() {
        mRecyclerviewToa.setHasFixedSize(true);
        mRecyclerviewToa.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

        mRecyclerviewGhe.setHasFixedSize(true);
        mRecyclerviewGhe.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));

        mRecyclerviewLuaChon.setHasFixedSize(true);
        mRecyclerviewLuaChon.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));
    }

    public void xoaVeDuocChon(VeTau veTau){
        for (int i = 0; i < mListVeTau.size() ; i++){
            if(mListVeTau.get(i).getId().equals(veTau.getId())){
                mListVeTau.get(i).setmCheck(false);
                mAdapterVeTau.notifyDataSetChanged();
            }
        }
        mListVeTauDaChon.remove(veTau);
        mVeDuocChonAdapter.notifyDataSetChanged();
        settongtien();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUESTCODE && resultCode == RESULT_OK){
            Intent intent = new Intent(getApplicationContext(), ThongTinDatVeActivity.class);
            intent.putExtra(Constain.keyVeTau,mGson.toJson(mListVeTauDaChon));
            intent.putExtra(Constain.keyMaChuyen,mGson.toJson(mTauReponse));
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_tiep_tuc:
                if(mListVeTauDaChon.size() == 0){
                    showAlertDialog("Vui lòng chọn ve tàu");
                }else{
                    startActivityForResult(new Intent(getApplicationContext(), LoginActivity.class),REQUESTCODE);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                }
                break;
        }
    }
}
