<%@ page import="com.balansefit.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String jspRes = CmmUtil.nvl((String) request.getAttribute("res"), "0");

    String toMail = CmmUtil.nvl(request.getParameter("toMail")); // 받는 사람
    String title = CmmUtil.nvl(request.getParameter("title")); // 제목
    String contents = CmmUtil.nvl(request.getParameter("contents")); // 내용
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메일 발송 결과</title>
</head>
<body>
<%
    // 메일 발송이 성공했다면...
    if (jspRes.equals("1")){
        out.println(toMail +"로 메일전송이 성공하였습니다.");
        out.println("메일 제목 : "+ title);
        out.println("메일 내용 : " + contents);

    //메일 발송이 실패했다면
    }else {
        out.println(toMail +"로 메일 전송이실패하였습니다.");
    }
%>
</body>
</html>