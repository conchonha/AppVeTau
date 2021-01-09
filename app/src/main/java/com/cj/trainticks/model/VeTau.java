package com.cj.trainticks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VeTau {
    private Boolean mCheck = false;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ghe_id")
    @Expose
    private Integer gheId;
    @SerializedName("NGAY")
    @Expose
    private String nGAY;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("SOGHE")
    @Expose
    private Integer sOGHE;
    @SerializedName("MALOAIGHE")
    @Expose
    private String mALOAIGHE;
    @SerializedName("toa_id")
    @Expose
    private Integer toaId;
    @SerializedName("TENLOAIGHE")
    @Expose
    private String tENLOAIGHE;
    @SerializedName("MACHUYEN")
    @Expose
    private String mACHUYEN;
    @SerializedName("GIAVE")
    @Expose
    private Integer gIAVE;
    @SerializedName("TenToa")
    @Expose
    private String tenToa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGheId() {
        return gheId;
    }

    public void setGheId(Integer gheId) {
        this.gheId = gheId;
    }

    public String getNGAY() {
        return nGAY;
    }

    public void setNGAY(String nGAY) {
        this.nGAY = nGAY;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getSOGHE() {
        return sOGHE;
    }

    public void setSOGHE(Integer sOGHE) {
        this.sOGHE = sOGHE;
    }

    public String getMALOAIGHE() {
        return mALOAIGHE;
    }

    public void setMALOAIGHE(String mALOAIGHE) {
        this.mALOAIGHE = mALOAIGHE;
    }

    public Integer getToaId() {
        return toaId;
    }

    public void setToaId(Integer toaId) {
        this.toaId = toaId;
    }

    public String getTENLOAIGHE() {
        return tENLOAIGHE;
    }

    public void setTENLOAIGHE(String tENLOAIGHE) {
        this.tENLOAIGHE = tENLOAIGHE;
    }

    public String getMACHUYEN() {
        return mACHUYEN;
    }

    public void setMACHUYEN(String mACHUYEN) {
        this.mACHUYEN = mACHUYEN;
    }

    public Integer getGIAVE() {
        return gIAVE;
    }

    public void setGIAVE(Integer gIAVE) {
        this.gIAVE = gIAVE;
    }

    public String getTenToa() {
        return tenToa;
    }

    public void setTenToa(String tenToa) {
        this.tenToa = tenToa;
    }

    public Boolean getmCheck() {
        return mCheck;
    }

    public void setmCheck(Boolean mCheck) {
        this.mCheck = mCheck;
    }
}