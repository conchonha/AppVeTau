package com.cj.trainticks.cores.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimChuyenTauReponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("GADI")
    @Expose
    private String gADI;
    @SerializedName("GADEN")
    @Expose
    private String gADEN;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("MATUYEN")
    @Expose
    private String mATUYEN;
    @SerializedName("MATAU")
    @Expose
    private String mATAU;
    @SerializedName("GIODI")
    @Expose
    private String gIODI;
    @SerializedName("GIODEN")
    @Expose
    private String gIODEN;
    @SerializedName("NGAYDEN")
    @Expose
    private String nGAYDEN;
    @SerializedName("NGAYDI")
    @Expose
    private String nGAYDI;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGADI() {
        return gADI;
    }

    public void setGADI(String gADI) {
        this.gADI = gADI;
    }

    public String getGADEN() {
        return gADEN;
    }

    public void setGADEN(String gADEN) {
        this.gADEN = gADEN;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getMATUYEN() {
        return mATUYEN;
    }

    public void setMATUYEN(String mATUYEN) {
        this.mATUYEN = mATUYEN;
    }

    public String getMATAU() {
        return mATAU;
    }

    public void setMATAU(String mATAU) {
        this.mATAU = mATAU;
    }

    public String getGIODI() {
        return gIODI;
    }

    public void setGIODI(String gIODI) {
        this.gIODI = gIODI;
    }

    public String getGIODEN() {
        return gIODEN;
    }

    public void setGIODEN(String gIODEN) {
        this.gIODEN = gIODEN;
    }

    public String getNGAYDEN() {
        return nGAYDEN;
    }

    public void setNGAYDEN(String nGAYDEN) {
        this.nGAYDEN = nGAYDEN;
    }

    public String getNGAYDI() {
        return nGAYDI;
    }

    public void setNGAYDI(String nGAYDI) {
        this.nGAYDI = nGAYDI;
    }

}