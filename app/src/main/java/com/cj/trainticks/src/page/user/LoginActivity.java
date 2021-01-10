package com.cj.trainticks.src.page.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import com.cj.trainticks.base.BaseActivity;
import com.cj.trainticks.R;
import com.cj.trainticks.cores.services.APIServices;
import com.cj.trainticks.cores.services.DataService;
import com.cj.trainticks.src.dialog.VerifyUserDialog;
import com.cj.trainticks.src.page.main.MainActivity;
import com.cj.trainticks.utils.Constain;
import com.cj.trainticks.utils.SharePrefs;
import com.google.gson.Gson;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private SharePrefs mSharePrefs;
    private String TAG = "LoginActivity";
    private Gson mGson = new Gson();
    private AppCompatEditText mEdtEmail;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitView() {
        mEdtEmail = findViewById(R.id.mEDTUser);
    }

    @Override
    protected void onListenerClicked() {
        findViewById(R.id.mBTNLogin).setOnClickListener(this);
        findViewById(R.id.mBTNRegister).setOnClickListener(this);
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mBTNLogin:
                if(mEdtEmail.getText().toString().equals("")){
                    showToast(getString(R.string.null_email));
                }else{
                    showProgressDialog(getString(R.string.loading),"");
                    DataService dataService = APIServices.getService();
                    Call<Map>call = dataService.checkEmail(mEdtEmail.getText().toString());

                    call.enqueue(new Callback<Map>() {
                        @Override
                        public void onResponse(Call<Map> call, Response<Map> response) {
                            hideDialog();
                            if(response.isSuccessful()){
                                if(response.body().get("message").equals("Successfully")){
                                    Bundle bundle = new Bundle();
                                    bundle.putString(Constain.keyEmail,mEdtEmail.getText().toString());
                                    bundle.putString(Constain.keyUser,mGson.toJson(response.body().get("data")));
                                    VerifyUserDialog dialog = new VerifyUserDialog();
                                    dialog.setArguments(bundle);
                                    dialog.show(getSupportFragmentManager(),Constain.keyVerifyDialog);
                                }else{
                                    showAlertDialog(response.body().get("message").toString());
                                }
                            }else{
                                showAlertDialog(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<Map> call, Throwable t) {
                            hideDialog();
                            showAlertDialog(t.getMessage());
                        }
                    });

                }
                break;
            case R.id.mBTNRegister:
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
        }
    }
}