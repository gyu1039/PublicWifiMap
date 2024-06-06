package com.example.publicwifimap.data;

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
        private String msg;

        @Override
        public String toString() {
            return "Result{" +
                    "code='" + code + '\'' +
                    ", mesString='" + msg + '\'' +
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
