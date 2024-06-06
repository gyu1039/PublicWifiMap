package com.example.publicwifimap.data;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DB {

    private static final Connection connection;
    private DB() {}

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/SQLite/WIFIINFO.sqlite");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        if(connection == null) {
            log.info("커넥션을 얻지 못했습니다.");
        }
    }


    public static List<WifiInfoWithDistance> getNearByWifi(double lat, double lng) {

        String exp = "(6371 * acos(" +
                        "cos(radians(" + lat + ")) * cos(radians(latitude)) * cos(radians(longitude) - radians(" + lng + ")) + " +
                        "sin(radians(" + lat + ")) * sin(radians(latitude))" +
                    "))";

        String sql = "SELECT " + exp + " AS distance, * FROM wifiinfo ORDER BY distance limit 20;";

        List<WifiInfoWithDistance> ret = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                WifiInfoWithDistance data = WifiInfoWithDistance.builder()
                        .distance((int)(rs.getDouble(1) * 10_000) / 10_000.0 + "")
                        .managerNo(rs.getString(2))
                        .wardOffice(rs.getString(3))
                        .mainName(rs.getString(4))
                        .address1(rs.getString(5))
                        .address2(rs.getString(6))
                        .installFloor(rs.getString(7))
                        .installType(rs.getString(8))
                        .installBy(rs.getString(9))
                        .serviceSe(rs.getString(10))
                        .cmcwr(rs.getString(11))
                        .constructionYear(rs.getString(12))
                        .inoutDoor(rs.getString(13))
                        .remars3(rs.getString(14))
                        .latitude(rs.getString(15))
                        .longitude(rs.getString(16))
                        .workDateTime(rs.getString(17))
                        .build();

                ret.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static int checkDataExists() {

        String query = "SELECT count(*) AS rowCount FROM WIFIINFO";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            int rowCount = resultSet.getInt("rowCount");
            if(rowCount == 0) {
                List<JsonResult> data = API.getData();
                if (data != null) {
                    rowCount = DB.insert(data);
                }
            }

            return rowCount;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private static int insert(List<JsonResult> data) {

        int count = 0;

        String SQL = "INSERT INTO WIFIINFO VALUES (?, ?, ?, ?, ?, " +
                                                    "?, ?, ?, ?, ?, " +
                                                    "?, ?, ?, ?, ?, " +
                                                    "?);";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            for(JsonResult result : data) {

                TbPublicWifiInfo tbPublicWifiInfo = result.tbPublicWifiInfo;
                List<Row> rowList = tbPublicWifiInfo.getRowList();
                int size = rowList.size();

                for(int i=0; i<size; i++) {
                    Row row = rowList.get(i);

                    preparedStatement.setString(1, row.getManagerNo());
                    preparedStatement.setString(2,row.getWardOffice());
                    preparedStatement.setString(3, row.getMainName());
                    preparedStatement.setString(4, row.getAddress1());
                    preparedStatement.setString(5, row.getAddress2());
                    preparedStatement.setString(6, row.getInstallFloor());
                    preparedStatement.setString(7, row.getInstallType());
                    preparedStatement.setString(8, row.getInstallBy());
                    preparedStatement.setString(9, row.getServiceSe());
                    preparedStatement.setString(10,row.getCmcwr());
                    preparedStatement.setString(11, row.getConstructionYear());
                    preparedStatement.setString(12, row.getInoutDoor());
                    preparedStatement.setString(13, row.getRemars3());
                    preparedStatement.setString(14, row.getLatitude());
                    preparedStatement.setString(15, row.getLongitude());
                    preparedStatement.setString(16, row.getWorkDateTime());

                    count += 1;
                    // 배치에 추가
                    preparedStatement.addBatch();
                    preparedStatement.clearParameters();
                }

                preparedStatement.executeBatch();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                return -1;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

    public static List<Row> selectAll() {

        String query = "SELECT * FROM WIFIINFO";
        List<Row> ret = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Row row = Row.builder()
                        .managerNo(rs.getString(1))
                        .wardOffice(rs.getString(2))
                        .mainName(rs.getString(3))
                        .address1(rs.getString(4))
                        .address2(rs.getString(5))
                        .installFloor(rs.getString(6))
                        .installType(rs.getString(7))
                        .installBy(rs.getString(8))
                        .serviceSe(rs.getString(9))
                        .cmcwr(rs.getString(10))
                        .constructionYear(rs.getString(11))
                        .inoutDoor(rs.getString(12))
                        .remars3(rs.getString(13))
                        .latitude(rs.getString(14))
                        .longitude(rs.getString(15))
                        .workDateTime(rs.getString(16))
                        .build();

                ret.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ret;
    }
}
