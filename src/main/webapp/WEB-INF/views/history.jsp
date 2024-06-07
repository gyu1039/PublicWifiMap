<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.publicwifimap.data.History" %><%--
  Created by IntelliJ IDEA.
  User: InGyu
  Date: 2024-06-05
  Time: 오후 4:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/default.css">
</head>
<body>
    <h1>위치 히스토리 목록</h1>
    <jsp:include page = "/WEB-INF/common.jsp"/>
    <table>
        <tr>
            <th>ID</th>
            <th>Y좌표</th>
            <th>X좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        <%
            List<History> data = (List<History>)request.getAttribute("data");
            if(!data.isEmpty()) {

                for(History d : data) {
        %>
            <tr>
                <td><%= d.getId()%></td>
                <td><%= d.getLatitude()%></td>
                <td><%= d.getLongitude()%></td>
                <td><%= d.getViewed()%></td>
                <td><button onclick="deleteRow(<%= d.getId() %>)">삭제</button></td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
               <td colspan="5"> 위치 정보가 없습니다.</td>
            </tr>
        <%
            }
        %>

    </table>

    <script>
        function deleteRow(id) {

            fetch("${pageContext.request.contextPath}/history", {

                method: 'delete',
                redirect: 'follow',
                headers: {
                    'Content-type' : 'application/json'
                },
                body: JSON.stringify({
                    'id': id
                })
            }).then(res => {

                console.log(res);
                window.location.href = res.url
            }).catch(e => console.log(e));
        }
    </script>
</body>
</html>
