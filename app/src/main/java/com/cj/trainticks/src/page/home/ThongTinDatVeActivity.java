package com.cj.trainticks.src.page.home;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cj.trainticks.R;
import com.cj.trainticks.base.BaseActivity;
import com.cj.trainticks.cores.reponse.TimChuyenTauReponse;
import com.cj.trainticks.cores.services.APIServices;
import com.cj.trainticks.cores.services.DataService;
import com.cj.trainticks.model.User;
import com.cj.trainticks.model.VeTau;
import com.cj.trainticks.src.adapter.home.ThongTinDatVeAdapter;
import com.cj.trainticks.src.adapter.home.VeDuocChonAdapter;
import com.cj.trainticks.utils.Constain;
import com.cj.trainticks.utils.SharePrefs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTinDatVeActivity extends BaseActivity implements View.OnClickListener {
    private Gson mGson = new Gson();
    private List<VeTau>mListVeTau = new ArrayList<>();
    private TimChuyenTauReponse mTauReponse;
    private User mUser;
    private String mReponse;
    private DecimalFormat mSimpleDateFortmat = new DecimalFormat("#,###,###");
    private TextView mTxtTentau,mTxtTgBatDau,mTxtTgKetThuc,mTxtTenLoaiGhe,mTxtHoTen,mTxtSoCmnd,mTxtEmail,mTxtLoaiDoiTuong,mTxtTongTien,
    mTxtTongTien1,mTxtTongVe,mTxtTienHanhDatVe;
    private RecyclerView mRecyclerView;
    private CheckBox mCheckBox;

    @Override
    protected int getContentView() {
        return R.layout.thong_tin_dat_ve_activity;
    }

    @Override
    protected void onInitView() {
        mTxtTentau = findViewById(R.id.txtTenTau);
        mTxtTgBatDau = findViewById(R.id.txt_bat_dau);
        mTxtTgKetThuc = findViewById(R.id.txt_ket_thuc);
        mTxtTenLoaiGhe = findViewById(R.id.txt_ten_loai_ghe);
        mTxtHoTen = findViewById(R.id.txt_ho_ten);
        mTxtSoCmnd = findViewById(R.id.txt_so_cmnd);
        mTxtEmail = findViewById(R.id.txt_email);
        mTxtLoaiDoiTuong = findViewById(R.id.txt_loai_doi_tuong);
        mTxtTongTien = findViewById(R.id.txt_tong_tien);
        mTxtTongTien1 = findViewById(R.id.txt_tong_tien_1);
        mTxtTongVe = findViewById(R.id.txt_tong_so_ve);
        mTxtTienHanhDatVe = findViewById(R.id.txt_tien_hanh_dat_ve);
        mRecyclerView = findViewById(R.id.recyclerview_tien_hanh_dat_ve);
        mCheckBox = findViewById(R.id.ck_check);
    }

    @Override
    protected void onListenerClicked() {
        mTxtTienHanhDatVe.setOnClickListener(this);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTxtTienHanhDatVe.setBackgroundResource(R.color.purple_700);
                    mTxtTienHanhDatVe.setEnabled(true);
                }else{
                    mTxtTienHanhDatVe.setBackgroundResource(R.color.xam);
                    mTxtTienHanhDatVe.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void onInit() {
        getDataIntent();
        setNoiDung();
        setDataRecyclerview();
    }

    private void setDataRecyclerview() {
        ThongTinDatVeAdapter adapter = new ThongTinDatVeAdapter(mListVeTau,getApplicationContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setNoiDung() {
        mTxtTienHanhDatVe.setEnabled(false);
        mTxtTienHanhDatVe.setBackgroundResource(R.color.xam);
        mTxtTentau.setText(mTauReponse.getTENTAU());
        mTxtTgBatDau.setText(mTauReponse.getGADI()+"-"+mTauReponse.getNGAYDI()+"-"+mTauReponse.getGIODI());
        mTxtTgKetThuc.setText(mTauReponse.getGADEN()+"-"+mTauReponse.getNGAYDEN()+"-"+mTauReponse.getGADEN());
        mTxtTenLoaiGhe.setText(mListVeTau.get(0).getTENLOAIGHE());
        mTxtHoTen.setText(mUser.getTENKH());
        mTxtSoCmnd.setText(mUser.getSOCMND());
        mTxtEmail.setText(mUser.getEMAIL());
        //set tổng tiền
        int tongtien = 0;
        for (VeTau veTau : mListVeTau){
            tongtien += veTau.getGIAVE();
        }
        mTxtTongTien.setText(mSimpleDateFortmat.format(tongtien)+" Vnd");
        mTxtTongTien1.setText(mSimpleDateFortmat.format(tongtien)+" Vnd");
        mTxtTongVe.setText("Tổng "+mListVeTau.size()+" vé");
        //set loai doi tuong
        for (String s : Constain.object){
            if(s.substring(0,1).equals(mUser.getMADOITUONG())){
                mTxtLoaiDoiTuong.setText(s.substring(1,s.length()));
            }
        }
    }

    private void getDataIntent() {
        Intent intent = getIntent();

        mReponse = intent.getStringExtra(Constain.keyVeTau);
        TypeToken<List<VeTau>> token = new TypeToken<List<VeTau>>() {};
        mListVeTau = mGson.fromJson(mReponse,token.getType());

        mUser = mGson.fromJson(new SharePrefs(getApplicationContext()).getUser(),User.class);

        mTauReponse = mGson.fromJson(intent.getStringExtra(Constain.keyMaChuyen),TimChuyenTauReponse.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_tien_hanh_dat_ve:
                    DataService dataService = APIServices.getService();
                    Call<Map> call = dataService.datVeTau(mUser.getId().toString(), mReponse);
                    call.enqueue(new Callback<Map>() {
                        @Override
                        public void onResponse(Call<Map> call, Response<Map> response) {
                            if (response.isSuccessful()) {
                                showAlertDialog(mGson.toJson(response.body().get("message")));
                                try {
                                    Thread.sleep(3000);
                                    finish();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                showAlertDialog(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<Map> call, Throwable t) {
                            showAlertDialog(t.getMessage());
                        }
                    });

                break;
        }
    }
}
