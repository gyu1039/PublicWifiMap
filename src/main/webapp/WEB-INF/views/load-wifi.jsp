<%--
  Created by IntelliJ IDEA.
  User: InGyu
  Date: 2024-06-05
  Time: 오후 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        body {
            text-align: center;
        }
    </style>
</head>
<body>

    <h1><%= (String)request.getAttribute("data")%>개의 WIFI정보를 정상적으로 저장하였습니다.</h1>
    <a href="${pageContext.request.contextPath}/">홈으로 가기</a>
</body>
</html>
