<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

    <style>
        table{ border-collapse : collapse; }  /*이중선 제거*/
        th,td{
            width: 100px;
            height: 50px;
            text-align: center;
            border: 1px solid #000;

            /*vertical-align: top;	!* 위 *!*/
            /*vertical-align: bottom;   !* 아래 *!*/
            vertical-align: middle;   /* 가운데 */
        }
    </style>
</head>
<body>
    <h1><%= "Hello World!" %></h1>
    <br/>
    <a href="test">Hello Servlet</a>
    <br/>
    <h1>와이파이 정보 구하기</h1>
    <p> <a href="${pageContext.request.contextPath}">홈</a> |
        <a href="${pageContext.request.contextPath}/history">히스토리 목록</a> |
        <a href="${pageContext.request.contextPath}/openapi-list">Open API WIFI 정보</a>
    </p>
    <p>
        <label for="lat">LAT: </label>
        <input id="lat" type="number" value="0.0"/>,

        <label for="lnt">LNT: </label>
        <input id="lnt" type="number" value="0.0"/>

        <input type="button" value="내 위치 가져오기" onclick="getMyLocation()"/>
        <input type="button" value="근처 WIFI 정보 보기" onclick=""/>
    </p>

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
        <tr>
            <td colspan="16">
                위치 정보를 입력한 후에 조회해 주세요.
            </td>
        </tr>
    </table>


    <script>

        function getMyLocation() {

            if(navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(pos => {
                    let latitude = pos.coords.latitude;
                    let longitude = pos.coords.longitude;
                    alert("현재 위치는 : " + latitude + ", "+ longitude);
                });
            } else {
                alert("위치를 불러올 수 없습니다.")
            }
        }

        function getNearByWifi() {

        }

    </script>
</body>
</html>