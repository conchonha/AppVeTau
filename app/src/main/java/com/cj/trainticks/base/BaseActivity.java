package com.cj.trainticks.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.cj.trainticks.R;
import com.cj.trainticks.utils.Validations;


public abstract class BaseActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 100;
    private ProgressDialog mProgressDialog;
    private static final String[] PEMISSITION_REQUESTION = {
        "android.permission.WRITE_EXTERNAL_STORAGE",
        "android.permission.READ_EXTERNAL_STORAGE"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        if(!Validations.checkInternet(this)){
            showAlertDialog(getString(R.string.internet));
        }else{
            onViewReady(savedInstanceState,getIntent());
            onInitView();
            onListenerClicked();
            onInit();
        }
    }

    private void init(){
        requestPermisition();
    }

    public boolean isPermisitionGrant(){
        for (String permisition : PEMISSITION_REQUESTION){
            if(checkSelfPermission(permisition) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    public void requestPermisition(){
        ActivityCompat.requestPermissions(this,PEMISSITION_REQUESTION,REQUEST_CODE);
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        //To be used by child activities
    }

    protected void hideKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (getCurrentFocus() != null)
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("MultiBackStack", "Failed to add fragment to back stack", e);
        }
    }

    public void showProgressDialog(String title, @NonNull String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            if (title != null)
                mProgressDialog.setTitle(title);
            mProgressDialog.setIcon(R.mipmap.ic_launcher);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }


    public void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void showAlertDialog(String msg) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(getString(R.string.dialog_box));
        dialogBuilder.setIcon(R.mipmap.ic_launcher);
        dialogBuilder.setMessage(msg);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialogBuilder.setCancelable(false);
        dialogBuilder.show();
    }

    protected void showToast(String mToastMsg) {
        Toast.makeText(this, mToastMsg, Toast.LENGTH_LONG).show();
    }

    protected abstract int getContentView();

    protected abstract void onInitView();

    protected abstract void onListenerClicked();

    protected abstract void onInit();
}