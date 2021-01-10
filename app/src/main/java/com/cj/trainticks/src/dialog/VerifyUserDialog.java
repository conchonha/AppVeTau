package com.cj.trainticks.src.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaCasException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cj.trainticks.R;
import com.cj.trainticks.src.page.main.MainActivity;
import com.cj.trainticks.utils.Constain;
import com.cj.trainticks.utils.SharePrefs;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class VerifyUserDialog extends DialogFragment implements View.OnClickListener {
    private View mView;
    private TextView mTxtEmail;
    private EditText mEdtCode;
    private String mEmail;
    private int mNumber = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.verify_user_dialog, container, false);
        initView();
        init();
        listenerOnclicked();
        return mView;
    }

    private void listenerOnclicked() {
        mView.findViewById(R.id.btnSend).setOnClickListener(this);
    }

    private void init() {
        mEmail = getArguments().getString(Constain.keyEmail);
        if (mEmail != null) {
            mTxtEmail.setText(mEmail);
            sendCodeEmail();
        }
    }

    private void sendCodeEmail() {
        Random random = new Random();
        mNumber = random.nextInt(100000);
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        //Initialize session
        javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constain.EMAIL,Constain.PASS);
            }
        });

        try {
            //Initialize email content
            Message message = new MimeMessage(session);
            //Sender email
            message.setFrom(new InternetAddress(Constain.EMAIL));
            //Recipient email
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mEmail));
            //Email subject
            message.setSubject("verify account");
            //Email message
            message.setText(mNumber+"");
            //send mail
            new SendMail().execute(message);
        }catch (MessagingException e1){
            e1.printStackTrace();
        }
    }

    private void initView() {
        mTxtEmail = mView.findViewById(R.id.txtEmail);
        mEdtCode = mView.findViewById(R.id.editTextCode);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.colorPickerStyle);
    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSend:
                if(mEdtCode.getText().toString().equals(mNumber+"")){
                    SharePrefs sharePrefs = new SharePrefs(getContext());
                    sharePrefs.saveUser(getArguments().getString(Constain.keyUser));

                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();
                }else{
                    Toast.makeText(getContext(), getString(R.string.verify_user_error), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private class SendMail extends AsyncTask<Message,String,String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //create and show progess dialog
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle(getString(R.string.please_wait));
            progressDialog.setMessage(getString(R.string.sending_mail));
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(s.equals("Success")){
                //when success

                //Inittialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                builder.setMessage(getString(R.string.send_mail_success));
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }else {
                //when error 
                Toast.makeText(getActivity(), getString(R.string.send_mail_error), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
