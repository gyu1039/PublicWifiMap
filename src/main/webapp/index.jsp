<%@ page import="java.util.List" %>
<%@ page import="com.example.publicwifimap.data.WifiInfoWithDistance" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/default.css">
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<jsp:include page = "WEB-INF/common.jsp"/>

<form name="form" method="get" action="${pageContext.request.contextPath}/nearby" style="display: inline">
    <label for="lat">LAT: </label>
    <input name="lat" id="lat" type="number" value="0.0"/>,

    <label for="lnt">LNT: </label>
    <input name="lnt" id="lnt" type="number" value="0.0"/>

    <input type="button" value="내 위치 가져오기" onclick="getMyLocation()"/>
    <input type="button" value="근처 WIFI 정보 보기" onclick="getNearByWifi()"/>
</form>
<br/>
<table>
    <tr>
        <th>거리(Km)</th>
        <th>관리 번호</th>
        <th>자치구</th>
        <th>WIFI 이름</th>
        <th>도로명주소</th>
        <th>상세 주소</th>
        <th>설치위치(층)</th>
        <th>설치 유형</th>
        <th>설치 기관</th>
        <th>서비스 구분</th>
        <th>망종류</th>
        <th>설치 년도</th>
        <th>실내외구분</th>
        <th>WIFI 접속 환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업 일자</th>
    </tr>
    <%
        List<WifiInfoWithDistance> data = (List<WifiInfoWithDistance>)request.getAttribute("data");
        if(data == null) {
    %>
    <tr>
        <td colspan="17">
            위치 정보를 입력한 후에 조회해 주세요.
        </td>
    </tr>
    <%
    } else {
        for(WifiInfoWithDistance d : data) {
    %>
    <tr>
        <td><%= d.getDistance()%></td>
        <td><%= d.getManagerNo()%></td>
        <td><%= d.getWardOffice()%></td>
        <td><%= d.getMainName()%></td>
        <td><%= d.getAddress1()%></td>
        <td><%= d.getAddress2()%></td>
        <td><%= d.getInstallFloor()%></td>
        <td><%= d.getInstallType()%></td>
        <td><%= d.getInstallBy()%></td>
        <td><%= d.getServiceSe()%></td>
        <td><%= d.getCmcwr()%></td>
        <td><%= d.getConstructionYear()%></td>
        <td><%= d.getInoutDoor()%></td>
        <td><%= d.getRemars3()%></td>
        <td><%= d.getLatitude()%></td>
        <td><%= d.getLongitude()%></td>
        <td><%= d.getWorkDateTime()%></td>
    </tr>
    <%
            }
        }
    %>
</table>


<script>

    document.addEventListener("DOMContentLoaded", (e) => {
        const url = new URLSearchParams(window.location.search);
        const lat = url.get("lat");
        const lnt = url.get("lnt");

        let v1 = "0.0";
        let v2 = "0.0";
        if(lat != null) {
            v1 = lat;
            v2 = lnt;
        }

        document.getElementById("lat").value = v1;
        document.getElementById("lnt").value = v2;
    })

    function getMyLocation() {

        if(navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(pos => {
                let latitude = pos.coords.latitude;
                let longitude = pos.coords.longitude;
                document.getElementById('lat').value = latitude;
                document.getElementById('lnt').value = longitude;

                fetch("${pageContext.request.contextPath}/history", {

                    method: 'POST',
                    headers: {
                        'Content-type': "application/x-www-form-urlencoded"
                    },
                    body: new URLSearchParams({
                        'lat': latitude,
                        'lnt': longitude,
                    })
                }).then(response => response.text())
                    .then(data => {})
                    .catch(error => alert('error: ' + error));

                alert("현재 위치는 : " + latitude + ", "+ longitude);
            });
        } else {
            alert("위치를 불러올 수 없습니다.")
        }
    }

    function getNearByWifi() {

        let lat = document.getElementById("lat").value;
        let lnt = document.getElementById("lnt").value;

        if(lat == 0 && lnt == 0) {
            alert("'내 위치'를 먼저 가져와야합니다.");
        } else {
            document.form.submit();
        }
    }

</script>
</body>
</html>