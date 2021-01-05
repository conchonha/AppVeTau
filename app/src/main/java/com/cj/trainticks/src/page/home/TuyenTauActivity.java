package com.cj.trainticks.src.page.home;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cj.trainticks.R;
import com.cj.trainticks.base.BaseActivity;
import com.cj.trainticks.cores.services.APIServices;
import com.cj.trainticks.cores.services.DataService;
import com.cj.trainticks.model.Tuyen;
import com.cj.trainticks.src.adapter.home.TuyenAdapter;
import com.cj.trainticks.utils.Constain;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.nfc.NfcAdapter.EXTRA_DATA;

public class TuyenTauActivity extends BaseActivity implements View.OnClickListener {
    private String TAG = "TuyenTauActivity";
    private TextView mTxtTuyenTau;
    private RecyclerView mRecyclerView;
    private TuyenAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.tuyen_tau_activity;
    }

    @Override
    protected void onInitView() {
        mTxtTuyenTau = findViewById(R.id.mTxtTuyenTau);
        mRecyclerView = findViewById(R.id.recyclerview_tuyen);
    }

    @Override
    protected void onListenerClicked() {
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    @Override
    protected void onInit() {
        getAllTuyen();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
    }

    private void getAllTuyen() {
        showProgressDialog(getString(R.string.loading), "");
        DataService dataService = APIServices.getService();
        Call<List<Tuyen>> call = dataService.getAllTuyen();
        call.enqueue(new Callback<List<Tuyen>>() {
            @Override
            public void onResponse(Call<List<Tuyen>> call, Response<List<Tuyen>> response) {
                hideDialog();
                Log.d(TAG, "onResponse: " + response.toString());
                if (response.isSuccessful()) {
                    Intent intent = getIntent();
                    if (intent.hasExtra(Constain.type_tuyen)) {
                        int type = intent.getIntExtra(Constain.type_tuyen, 0);
                        switch (type) {
                            case 1:
                                mTxtTuyenTau.setText(getString(R.string.ga_di));
                                mAdapter = new TuyenAdapter(response.body(),TuyenTauActivity.this,1);
                                mRecyclerView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();
                                break;
                            case 2:
                                mTxtTuyenTau.setText(getString(R.string.ga_den));
                                Intent intent1 = getIntent();
                                String gadi = intent1.getStringExtra(Constain.keyGaDi);
                                List<Tuyen>list = new ArrayList<>();
                                for (Tuyen tuyen : response.body()){
                                    if(tuyen.getGADI().equals(gadi)){
                                        list.add(tuyen);
                                    }
                                }
                                mAdapter = new TuyenAdapter(list,TuyenTauActivity.this,2);
                                mRecyclerView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();
                                break;
                        }
                    }
                } else {
                    showAlertDialog(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Tuyen>> call, Throwable t) {
                hideDialog();
                showAlertDialog(t.getMessage());
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void onclickItemAdapter1(String gadi){
        final Intent data = new Intent();
        data.putExtra(EXTRA_DATA, "success");
        data.putExtra(Constain.Tuyen, gadi);
        setResult(Activity.RESULT_OK, data);
        finish();
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
