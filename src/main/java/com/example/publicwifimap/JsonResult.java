package com.example.publicwifimap;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JsonResult {

    @SerializedName("TbPublicWifiInfo")
    TbPublicWifiInfo tbPublicWifiInfo;
    @Override
    public String toString() {
        return tbPublicWifiInfo.toString();
    }
}
