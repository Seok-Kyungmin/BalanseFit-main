<%@ page import="com.balansefit.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    String sessionEmail = (String) session.getAttribute("sessionEmail");
%>
<!DOCTYPE html>
<html class="h-100" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>비밀번호변경</title>
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
                            <a class="text-center" href="/adminLoginPage"> <h4>"<%=sessionEmail %>"님의 비밀번호 입력</h4></a>
                            <form name='f' class="mt-5 mb-5 login-input" action='/admin/changeAdminPwdProc' onsubmit="return doChangeUserCheck(this);">

                                <div class="form-group">
                                    <input type="password" class="form-control" placeholder="id" name="adm_id">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" placeholder="password" name="adm_pw">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" placeholder="passwordCheck" name="adm_pw2">
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
    function doChangeUserCheck(f) {

        // ID
        if (f.adm_id.value==""){
            alert("아이디를 입력하세요");
            f.adm_id.focus();
            return false;
        }

        // Password
        if (f.user_pw.value==""){
            alert("비밀번호를 입력하세요");
            f.user_pw.focus();
            return false;
        }
        if(!RegExp.test(f.adm_pw.value)){ //패스워드 유효성검사
            alert("Password는 4~12자의 영문 대소문자와 숫자로만 입력하여 주세요.");
            f.adm_pw.focus();
            return false;
        }

        if(f.adm_pw.value == f.adm_id.value){ //패스워드와 ID가 동일한지 검사
            alert("Password는 ID와 동일하면 안됩니다.");
            f.adm_pw.focus();
            return false; }

        if(f.adm_pw2.value != f.adm_pw.value){ //비밀번호와 비밀번호확인이 동일한지 검사
            alert("비밀번호가 서로 다릅니다. 다시 확인하여 입력해주세요.");
            f.adm_pw2.focus();
            return false;
        }

    }
</script>
</body>
</html>