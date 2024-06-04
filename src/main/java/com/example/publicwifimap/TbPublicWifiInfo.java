package com.example.publicwifimap;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TbPublicWifiInfo {

    @SerializedName("list_total_count")
    private int listTotalCount;

    @SerializedName("RESULT")
    private Result result;

    @SerializedName("row")
    private List<Row> rowList;

    @Getter @Setter
    static class Result {

        @SerializedName("CODE")
        private String code;

        @SerializedName("MESSAGE")
        private String mesString;

        @Override
        public String toString() {
            return "Result{" +
                    "code='" + code + '\'' +
                    ", mesString='" + mesString + '\'' +
                    '}';
        }
    }

    @Getter @Setter
    static class Row {
        @SerializedName("X_SWIFI_MGR_NO")
        private String managerNo;

        @SerializedName("X_SWIFI_WRDOFC")
        private String wardOffice;

        @SerializedName("X_SWIFI_MAIN_NM")
        private String mainName;

        @SerializedName("X_SWIFI_ADRES1")
        private String address1;

        @SerializedName("X_SWIFI_ADRES2")
        private String address2;

        @SerializedName("X_SWIFI_INSTL_FLOOR")
        private String installFloor;

        @SerializedName("X_SWIFI_INSTL_TY")
        private String installType;

        @SerializedName("X_SWIFI_INSTL_MBY")
        private String installBy;

        @SerializedName("X_SWIFI_SVC_SE")
        private String serviceSe;

        @SerializedName("X_SWIFI_CMCWR")
        private String cmcwr;

        @SerializedName("X_SWIFI_CNSTC_YEAR")
        private String constructionYear;

        @SerializedName("X_SWIFI_INOUT_DOOR")
        private String inoutDoor;

        @SerializedName("X_SWIFI_REMARS3")
        private String remars3;

        @SerializedName("LAT")
        private String latitude;

        @SerializedName("LNT")
        private String longitude;

        @SerializedName("WORK_DTTM")
        private String workDateTime;

        @Override
        public String toString() {
            return "Row{" +
                    "managerNo='" + managerNo + '\'' +
                    ", wardOffice='" + wardOffice + '\'' +
                    ", mainName='" + mainName + '\'' +
                    ", address1='" + address1 + '\'' +
                    ", address2='" + address2 + '\'' +
                    ", installFloor='" + installFloor + '\'' +
                    ", installType='" + installType + '\'' +
                    ", installBy='" + installBy + '\'' +
                    ", serviceSe='" + serviceSe + '\'' +
                    ", cmcwr='" + cmcwr + '\'' +
                    ", constructionYear='" + constructionYear + '\'' +
                    ", inoutDoor='" + inoutDoor + '\'' +
                    ", remars3='" + remars3 + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", longitude='" + longitude + '\'' +
                    ", workDateTime='" + workDateTime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for(Row row : rowList) {
            sb.append(row).append(",");
        }

        return "TbPublicWifiInfo{" +
                "\n\tlistTotalCount=" + listTotalCount +
                "\n\tresult=" + result +
                "\t\trowList=" + sb.toString() +
                "\n}";
    }
}
