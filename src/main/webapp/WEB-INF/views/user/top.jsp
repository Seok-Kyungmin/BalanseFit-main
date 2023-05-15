<%@ page import="com.balansefit.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String user_name = CmmUtil.nvl((String)session.getAttribute("name"));
%>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<div style="background-color: green; height: 50px">

    <%if(user_name.isEmpty()) {%>
    <a href="/loginPage" style="color: #2b2b2b;"> 로그인 </a>
    <%} else{ %>
    <%=user_name %>님 환영합니다.
    <a href="/userlogout" style="color: #2b2b2b;"> 로그아웃</a>
    <%} %>

</div>
</body>
</html>