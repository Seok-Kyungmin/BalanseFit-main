<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.balansefit.util.CmmUtil" %>
<%
String jspRes = CmmUtil.nvl((String)request.getAttribute("res"), "0");

String toMail = CmmUtil.nvl(request.getParameter("toMail"));
String title = CmmUtil.nvl(request.getParameter("title"));
String contents = CmmUtil.nvl(request.getParameter("contents"));
%>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>메일 발송 결과 화면</title>
</head>
<body>

<%
    if(jspRes.equals("1")){
%>
<script>
    alert('<%=toMail%>로 임시 비밀번호를 발송했습니다')
    document.location.href="/loginpage"
</script>
<%
    }else{
%>
<script>
    alert('<%=toMail%>로 임시 비밀번호를 발송 실패했습니다')
    document.location.href="/user/login"
</script>
<%
    }
%>
<%--<%--%>
<%--  if(jspRes.equals("1")){--%>
<%--      out.println(toMail +"메일 전송 성공");--%>
<%--%>--%>
<%--<script>--%>
<%--    alert('<<%=toMail%>로 임시 비밀번호를 발송했습니다')--%>
<%--    document.location.href="/user/login"--%>
<%--</script>--%>
<%--<%--%>
<%--      out.println("메일 제목 : " + title);--%>
<%--      out.println("메일 내용 : " + contents);--%>
<%--  }else{--%>
<%--      out.println(toMail +"메일 전송 실패");--%>
<%--  }--%>
<%--%>--%>

</body>
</html>