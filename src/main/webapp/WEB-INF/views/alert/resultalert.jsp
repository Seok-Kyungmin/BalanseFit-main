<%@ page import="com.balansefit.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.balansefit.util.CmmUtil" %>
<%
    //전달받은 메시지
    String msg = CmmUtil.nvl((String)request.getAttribute("msg"));
    String url = CmmUtil.nvl((String)request.getAttribute("url"));
    String icon = CmmUtil.nvl((String)request.getAttribute("icon"));
    String contents = CmmUtil.nvl((String)request.getAttribute("contents"));
%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Market</title>
    <script src="/js/jquery-3.6.0.min.js"></script>


</head>
<body>

</body>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">

    swal( "<%=msg%>", "<%=contents%>","<%=icon%>")
        .then(function(){
            location.href="<%=url%>";
        });
</script>
</html>