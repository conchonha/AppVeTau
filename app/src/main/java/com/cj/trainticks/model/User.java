package com.cj.trainticks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

@SerializedName("TENKH")
@Expose
private String tENKH;
@SerializedName("SOCMND")
@Expose
private String sOCMND;
@SerializedName("EMAIL")
@Expose
private String eMAIL;
@SerializedName("SODT")
@Expose
private String sODT;
@SerializedName("MADOITUONG")
@Expose
private String mADOITUONG;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("id")
@Expose
private Integer id;

public String getTENKH() {
return tENKH;
}

public void setTENKH(String tENKH) {
this.tENKH = tENKH;
}

public String getSOCMND() {
return sOCMND;
}

public void setSOCMND(String sOCMND) {
this.sOCMND = sOCMND;
}

public String getEMAIL() {
return eMAIL;
}

public void setEMAIL(String eMAIL) {
this.eMAIL = eMAIL;
}

public String getSODT() {
return sODT;
}

public void setSODT(String sODT) {
this.sODT = sODT;
}

public String getMADOITUONG() {
return mADOITUONG;
}

public void setMADOITUONG(String mADOITUONG) {
this.mADOITUONG = mADOITUONG;
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

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

}