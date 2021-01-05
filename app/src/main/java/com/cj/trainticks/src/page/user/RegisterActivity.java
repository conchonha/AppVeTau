package com.cj.trainticks.src.page.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.widget.AppCompatEditText;

import com.cj.trainticks.R;
import com.cj.trainticks.base.BaseActivity;
import com.cj.trainticks.cores.body.RegisterBody;
import com.cj.trainticks.cores.services.APIServices;
import com.cj.trainticks.cores.services.DataService;
import com.cj.trainticks.src.page.main.MainActivity;
import com.cj.trainticks.utils.Constain;
import com.cj.trainticks.utils.SharePrefs;
import com.cj.trainticks.utils.Validations;
import com.google.gson.Gson;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private AppCompatEditText mEDTName, mEDTCMND, mEDTEmail, mEDTPhone;
    private Spinner mSpiner;
    private String mObjectCode = "1";
    private SharePrefs mSharePrefs;
    private String TAG = "RegisterActivity";
    private Gson mGson = new Gson();

    @Override
    protected int getContentView() {
        return R.layout.activity_register;
    }

    //anh xa view
    @Override
    protected void onInitView() {
        mEDTName = findViewById(R.id.mEDTName);
        mEDTCMND = findViewById(R.id.mEDTCMND);
        mEDTEmail = findViewById(R.id.mEDTEmail);
        mEDTPhone = findViewById(R.id.mEDTPhone);
        mSpiner = findViewById(R.id.mSpiner);
    }

    //lang nghe su kien onclicked view
    @Override
    protected void onListenerClicked() {
        findViewById(R.id.mBTNRegister).setOnClickListener(this);
        mSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mObjectCode = Constain.object[position].substring(0, 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onInit() {
        mSharePrefs = new SharePrefs(getApplicationContext());
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Constain.object);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpiner.setAdapter(adapter);
    }


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBTNRegister:
                if (checkValidation()) {
                    showProgressDialog(getString(R.string.loading), "");
                    RegisterBody body = new RegisterBody(mEDTName.getText().toString(), mEDTCMND.getText().toString(), mEDTEmail.getText().toString(), mEDTPhone.getText().toString(), mObjectCode);
                    //mUserViewModel.register(body);
                    DataService dataService = APIServices.getService();
                    Call<Map> call = dataService.register(body);

                    call.enqueue(new Callback<Map>() {
                        @Override
                        public void onResponse(Call<Map> call, Response<Map> response) {
                            Log.d(TAG, "onResponse: " + response.toString());
                            hideDialog();
                            if (response.isSuccessful()) {
                                if (response.body().get("message") != null) {
                                    showAlertDialog(mGson.toJson(response.body().get("message")));
                                } else {
                                    showToast(getString(R.string.success));
                                    String strUser = mGson.toJson(response.body().get("data"));
                                    mSharePrefs.saveUser(strUser);
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                                }
                            } else {
                                showAlertDialog(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<Map> call, Throwable t) {
                            hideDialog();
                            Log.d(TAG, "onFailure: " + t.toString());
                            showAlertDialog(t.getMessage());
                        }
                    });
                }
                break;
        }
    }

    public boolean checkValidation() {
        if (Validations.isValidName(mEDTName.getText().toString())) {
            mEDTName.setError(getString(R.string.name_invalid));
            return false;
        }
        mEDTName.setError(null);

        if (mEDTCMND.getText().toString().length() != 9) {
            mEDTCMND.setError(getString(R.string.cmnd_invalid));
            return false;
        }
        mEDTCMND.setError(null);

        if (Validations.isEmailValid(mEDTEmail.getText().toString())) {
            mEDTEmail.setError(getString(R.string.email_invalid));
            return false;
        }
        mEDTEmail.setError(null);

        if (!Validations.isValidPhoneNumber(mEDTPhone.getText().toString())) {
            mEDTPhone.setError(getString(R.string.phone_invalid));
            return false;
        }
        mEDTPhone.setError(null);
        return true;
    }
}