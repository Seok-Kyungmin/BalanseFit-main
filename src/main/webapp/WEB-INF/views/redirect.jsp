<%@ page import="com.balansefit.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
    
<%
    //전달받은 메시지
    String msg = CmmUtil.nvl((String)request.getAttribute("msg"));
    String url = CmmUtil.nvl((String)request.getAttribute("url"));
    String icon = CmmUtil.nvl((String)request.getAttribute("icon"));
    String contents = CmmUtil.nvl((String)request.getAttribute("contents"));
    
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	html {
  font-size: 18px;
}
body {
  color:black;
  font-family: "Questrial", sans-serif;
  background-color: #ffec63;
  background-image: linear-gradient(
      45deg,
      #ffd966 25%,
      transparent 25%,
      transparent 75%,
      #ffd966 75%,
      #ffd966
    ),
    linear-gradient(
      -45deg,
      #ffd966 25%,
      transparent 25%,
      transparent 75%,
      #ffd966 75%,
      #ffd966
    );
  background-size: 60px 60px;
  background-position: 0 0;
  animation: slide 4s infinite linear;
}

@keyframes slide {
  from {
    background-position: 0 0;
  }

  to {
    background-position: -120px 60px;
  }
}

.modal {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: auto;
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  padding: 1.6rem 3rem;
  border: 3px solid black;
  border-radius: 5px;
  background: white;
  box-shadow: 8px 8px 0 rgba(0, 0, 0, 0.2);
}
.message {
  font-size:1.1rem;
  margin-bottom: 1.6rem;
  margin-top: 0;
}
.btn {
  color:inherit;
    font-family:inherit;
  font-size: inherit;
  background: white;
  padding: 0.3rem 3.4rem;
  border: 3px solid black;
  margin-right: 2.6rem;
  box-shadow: 0 0 0 black;
  transition: all 0.2s;
}

.btn:last-child {
  margin: 0;
}

.btn:hover {
  box-shadow: 0.4rem 0.4rem 0 black;
  transform: translate(-0.4rem, -0.4rem);
}

.btn:active {
  box-shadow: 0 0 0 black;
  transform: translate(0, 0);
}

.options {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

</style>

<script type="text/javascript">
alert("<%=msg%>");
location.href = "<%=url%>";
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>처리페이지</title>
</head>
<body>

</body>
</html>