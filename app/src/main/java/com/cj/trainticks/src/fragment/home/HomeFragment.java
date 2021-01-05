package com.cj.trainticks.src.fragment.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cj.trainticks.R;
import com.cj.trainticks.cores.services.APIServices;
import com.cj.trainticks.cores.services.DataService;
import com.cj.trainticks.model.Tuyen;
import com.cj.trainticks.src.page.home.TuyenTauActivity;
import com.cj.trainticks.utils.Constain;
import com.cj.trainticks.utils.Helpers;
import com.google.gson.Gson;
import com.uits.baseproject.base.BaseFragment;
import com.uits.baseproject.service.ApiService;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private TextView mTxtGadi,mDaysToGo,mTxtGaDen;
    private Calendar mGetDate;
    private DatePickerDialog mDateDialog;
    private View mView;
    private SimpleDateFormat mSDF = new SimpleDateFormat("dd/MM/yyyy");
    private final int REQUEST_CODE_1 = 1111;
    private final int REQUEST_CODE_2 = 2222;
    private final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_fragment,container,false);
        initView();
        listenerOnclicked();
        return mView;
    }

    private void listenerOnclicked() {
        mDaysToGo.setOnClickListener(this);
        mTxtGadi.setOnClickListener(this);
        mTxtGaDen.setOnClickListener(this);
        mView.findViewById(R.id.btnTimChuyenTau).setOnClickListener(this);
    }

    private void initView() {
        mTxtGadi = mView.findViewById(R.id.txtGadi);
        mTxtGaDen = mView.findViewById(R.id.txtGaDen);
        mDaysToGo = mView.findViewById(R.id.mDaysToGo);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mDaysToGo:
                mGetDate = Calendar.getInstance();
                mDateDialog = new DatePickerDialog(getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, (view, year, month, dayOfMonth) -> {
                    Calendar selectDate = Calendar.getInstance();
                    selectDate.set(Calendar.YEAR, year);
                    selectDate.set(Calendar.MONTH, month);
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    mDaysToGo.setText(mSDF.format(selectDate.getTime()));
                },
                        mGetDate.get(Calendar.YEAR),
                        mGetDate.get(Calendar.MONTH),
                        mGetDate.get(Calendar.DAY_OF_MONTH));
                mDateDialog.show();
                break;
            case R.id.txtGadi:
                startActivityForResult(new Intent(getActivity(), TuyenTauActivity.class).putExtra(Constain.type_tuyen,1),REQUEST_CODE_1);
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
            case R.id.txtGaDen:
                if(mTxtGadi.getText().toString().equals(getString(R.string.ch_n_ga_i))){
                    Toast.makeText(getContext(), getString(R.string.please_choice_ga_di), Toast.LENGTH_SHORT).show();
                }else{
                    startActivityForResult(new Intent(getActivity(), TuyenTauActivity.class).
                            putExtra(Constain.type_tuyen,2).putExtra(Constain.keyGaDi,mTxtGadi.getText().toString()),REQUEST_CODE_2);
                    getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                }
                break;
            case R.id.btnTimChuyenTau:
                if(mTxtGadi.getText().toString().equals(getString(R.string.ch_n_ga_i))){
                    Toast.makeText(getContext(), getString(R.string.please_choice_ga_di), Toast.LENGTH_SHORT).show();
                }else if(mTxtGaDen.getText().toString().equals(getString(R.string.ch_n_ga_n))){
                    Toast.makeText(getContext(), getString(R.string.please_choice_ga_den), Toast.LENGTH_SHORT).show();
                }else if(mDaysToGo.getText().toString().equals(getString(R.string.type_ga))){
                    Toast.makeText(getContext(), getString(R.string.please_choice_time), Toast.LENGTH_SHORT).show();
                }else{

                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE_1:
                    mTxtGadi.setText(data.getStringExtra(Constain.Tuyen));
                    break;
                case REQUEST_CODE_2:
                    mTxtGaDen.setText(data.getStringExtra(Constain.Tuyen));
                    break;
            }
        }
    }
}