package com.cj.trainticks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tuyen {

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
    @SerializedName("TENGA")
    @Expose
    private String tENGA;
    @SerializedName("DIACHI")
    @Expose
    private String dIACHI;
    @SerializedName("MATINH")
    @Expose
    private String mATINH;

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

    public String getTENGA() {
        return tENGA;
    }

    public void setTENGA(String tENGA) {
        this.tENGA = tENGA;
    }

    public String getDIACHI() {
        return dIACHI;
    }

    public void setDIACHI(String dIACHI) {
        this.dIACHI = dIACHI;
    }

    public String getMATINH() {
        return mATINH;
    }

    public void setMATINH(String mATINH) {
        this.mATINH = mATINH;
    }

}