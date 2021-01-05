package com.cj.trainticks.cores.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterBody {
    @SerializedName("fullName")
    @Expose
    private String mFullName;

    @SerializedName("identityCard")
    @Expose
    private String SOCMND;

    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("phoneNumber")
    @Expose
    private String mPhoneNumber;

    @SerializedName("objectCode")
    @Expose
    private String mObjectCode;

    public RegisterBody(String mFullName, String SOCMND, String mEmail, String mPhoneNumber, String mObjectCode) {
        this.mFullName = mFullName;
        this.SOCMND = SOCMND;
        this.mEmail = mEmail;
        this.mPhoneNumber = mPhoneNumber;
        this.mObjectCode = mObjectCode;
    }
}
