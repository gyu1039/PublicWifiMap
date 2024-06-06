package com.example.publicwifimap;

import com.example.publicwifimap.data.JsonResult;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

class APITest {

    final String UTF_8 = "UTF-8";
    final String URL = "http://openapi.seoul.go.kr:8088";
    final String SERVICE = "TbPublicWifiInfo";

    final String START_IDX = "24593";

    final String END_IDX = "24600";

    @Test
    void get() throws IOException {

        String resource = "key.property";
        Properties properties = new Properties();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(resource);
        properties.load(resourceAsStream);

        String KEY = (String)properties.get("key");
        // api 요청url - 순서를 지켜야 함

        String urlBuilder = URL + "/" +
                URLEncoder.encode(KEY, UTF_8) + "/" +
                URLEncoder.encode("json", UTF_8) + "/" +
                URLEncoder.encode(SERVICE, UTF_8) + "/" +
                URLEncoder.encode(START_IDX, UTF_8) + "/" +
                URLEncoder.encode(END_IDX, UTF_8);

        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "applications/json");
        int responseCode = conn.getResponseCode();
        System.out.println("Response code: " + responseCode);

        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        System.out.println("----------");

        JsonResult result = new Gson().fromJson(sb.toString(), JsonResult.class);
        System.out.println(result.getTbPublicWifiInfo().getRowList().get(0));
    }

    @Test
    void 프로퍼티_값_불러오기() throws IOException {

        String resource = "key.property";
        Properties properties = new Properties();

        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(resource);

        properties.load(resourceAsStream);

        System.out.println(properties.get("key"));

    }
}