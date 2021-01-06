package com.cj.trainticks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Toa {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("MALOAITOA")
@Expose
private String mALOAITOA;
@SerializedName("tau_id")
@Expose
private Integer tauId;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("TENTOA")
@Expose
private String tENTOA;
@SerializedName("CHUTHICHTOA")
@Expose
private String cHUTHICHTOA;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getMALOAITOA() {
return mALOAITOA;
}

public void setMALOAITOA(String mALOAITOA) {
this.mALOAITOA = mALOAITOA;
}

public Integer getTauId() {
return tauId;
}

public void setTauId(Integer tauId) {
this.tauId = tauId;
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

public String getTENTOA() {
return tENTOA;
}

public void setTENTOA(String tENTOA) {
this.tENTOA = tENTOA;
}

public String getCHUTHICHTOA() {
return cHUTHICHTOA;
}

public void setCHUTHICHTOA(String cHUTHICHTOA) {
this.cHUTHICHTOA = cHUTHICHTOA;
}

}