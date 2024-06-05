package com.example.publicwifimap.data;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class API {

    private static final String UTF_8 = "UTF-8";
    private static final String URL = "http://openapi.seoul.go.kr:8088";
    private static final String SERVICE = "TbPublicWifiInfo";

    private static final String KEY;

    private static final String baseUrl;


    static {
        KEY = getKey();
        try {
            baseUrl = URL + "/" +
                    URLEncoder.encode(KEY, UTF_8) + "/" +
                    URLEncoder.encode("json", UTF_8) + "/" +
                    URLEncoder.encode(SERVICE, UTF_8) + "/";

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    private static String getKey() {

        String resource = "key.property";
        Properties properties = new Properties();
        InputStream resourceAsStream = API.class.getClassLoader().getResourceAsStream(resource);

        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return (String)properties.get("key");
    }

    private API() {}

    public static List<JsonResult> getData() throws IOException {

        List<JsonResult> ret = new ArrayList<>();

        int start = 1;
        int size = 1_000;
        int totalSize = 24593;

        int len = totalSize / size + 1;

        for(int i=0; i<len; i++) {
            String urlBuilder = baseUrl + URLEncoder.encode(start + "", UTF_8) + "/"
                    + URLEncoder.encode(size * (i + 1) + "", UTF_8);

            URL url = new URL(urlBuilder);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            int responseCode = conn.getResponseCode();
            if(200 <= responseCode && responseCode < 300) {
                Reader rd = new InputStreamReader(conn.getInputStream());
                JsonResult jsonResult = new Gson().fromJson(rd, JsonResult.class);
                ret.add(jsonResult);
            } else {
                log.info("데이터를 반환 받을 수 없습니다.");
                log.info((new Gson().fromJson(new InputStreamReader(conn.getErrorStream()), JsonResult.class)).toString());
                return null;
            }

            start = size * (i + 1) + 1;
        }

        return ret;
    }
}
