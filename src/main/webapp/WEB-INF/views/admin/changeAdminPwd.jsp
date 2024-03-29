<%@ page import="com.balansefit.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    String random = CmmUtil.nvl((String) session.getAttribute("random"));
%>

<!DOCTYPE html>
<html class="h-100" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>인증번호확인</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="../assets/images/favicon.png">
    <!-- <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous"> -->
    <link href="../css/style-2.css" rel="stylesheet">
</head>
<body class="h-100">
<!--*******************
    Preloader start
********************-->
<div id="preloader">
    <div class="loader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10" />
        </svg>
    </div>
</div>
<!--*******************
    Preloader end
********************-->
<div class="login-form-bg h-100">
    <div class="container h-100">
        <div class="row justify-content-center h-100">
            <div class="col-xl-6">
                <div class="form-input-content">
                    <div class="card login-form mb-0">
                        <div class="card-body pt-5">
                            <a class="text-center" href="/adminLoginPage"> <h4>인증번호 입력</h4></a>
                            <form class="mt-5 mb-5 login-input" action='/admin/changeAdminPwdProcPage' onsubmit="return randomCheck(this);">
                                <div class="form-group">
                                    <input type="password" class="form-control" placeholder="access Code">
                                </div>
                                <button class="btn login-form__btn submit w-100">FIND YOUR PASSWORD</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--**********************************
    Scripts
***********************************-->
<script src="../plugins/common/common.min.js"></script>
<script src="../js/custom.min.js"></script>
<script src="../js/settings.js"></script>
<script src="../js/gleek.js"></script>
<script src="../js/styleSwitcher.js"></script>
<script type="text/javascript">
    function randomCheck(f) {

        if(check.value != "<%=random %>") {
            alert("인증번호를 확인해주세요");
            f.check.focus();
            return false;
        }
    }
</script>
</body>
</html>