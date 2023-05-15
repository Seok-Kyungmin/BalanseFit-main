<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html
        lang="en"
        class="light-style customizer-hide"
        dir="ltr"
        data-theme="theme-default"
        data-assets-path="../assets/"
        data-template="vertical-menu-template-free"
>
<head>
    <meta charset="utf-8"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>Sign up</title>

    <meta name="description" content=""/>

    <!-- Favicon /..../static/assets/img/favicon/favicon.ico-->
    <link rel="icon" type="image/x-icon" href="../assets/img/favicon/favicon.ico"/>

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link
            href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet"
    />

    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="../assets/vendor/fonts/boxicons.css"/>

    <!-- Core CSS -->
    <link rel="stylesheet" href="../assets/vendor/css/core.css" class="template-customizer-core-css"/>
    <link rel="stylesheet" href="../assets/vendor/css/theme-default.css" class="template-customizer-theme-css"/>
    <link rel="stylesheet" href="../assets/css/demo.css"/>

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css"/>

    <!-- Page CSS -->
    <!-- Page -->
    <link rel="stylesheet" href="../assets/vendor/css/pages/page-auth.css"/>
    <!-- Helpers -->
    <script src="../assets/vendor/js/helpers.js"></script>

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="../assets/js/config.js"></script>
    <style>
        /* 중복아이디 존재하지 않는경우 */
        .id_input_re_1{
            color : green;
            display : none;
        }
        /* 중복아이디 존재하는 경우 */
        .id_input_re_2{
            color : red;
            display : none;
        }
        /* 유효성 검사 실패시 */
        .id_input_re_3{
            color : red;
            display : none;
        }
    </style>

    <script type="text/javascript">

        //아이디 중복검사
        function checkId(){


            let user_id = $('#user_id').val();  //id값이 "id"인 입력란의 값을 저장
            let data = {"user_id" : user_id}

            console.log(user_id);

            $.ajax({
                type : "post",
                url : "/userIdChk",
                data : data,
                success : function(res){

                    // console.log("성공 여부" + result);
                    if(res == 'success'){   //res가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
                        $('.id_input_re_2').css("display","inline-block");
                        $('.id_input_re_1').css("display", "none");
                    } else {    // res가 1일 경우 -> 이미 존재하는 아이디
                        $('.id_input_re_1').css("display","inline-block");
                        $('.id_input_re_2').css("display", "none");
                    }

                } // success 종료
            }); // ajax 종료

        };// function 종료

        // 이메일 중복검사
        function checkEmail(){

            let user_email = $('#user_email').val();  // .email_input에 입력되는 값
            let data = {"user_email" : user_email}

            console.log(user_email);

            $.ajax({
                type : 'post',
                url : "/userEmailChk",
                data : data,
                success : function(res){

                    if(res == 'success'){   //res가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
                        $('.email_input_re_2').css("display","inline-block");
                        $('.email_input_re_1').css("display", "none");
                    } else {    // res가 1일 경우 -> 이미 존재하는 아이디
                        $('.email_input_re_1').css("display","inline-block");
                        $('.email_input_re_2').css("display", "none");
                    }

                }

            });

        };// function 종료

        //회원가입 정보의 유효성 체크하기
        function doRegUserCheck(f) {

            let RegExp = /^[a-zA-Z0-9]{4,12}$/; //id와 pwassword 유효성 검사 정규식
            // 이메일 유효성검사
            let e_RegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            let n_RegExp = /^[가-힣]{2,15}$/; //이름 유효성검사 정규식

            // ID
            if (f.user_id.value == "") {
                alert("아이디를 입력하세요.");
                f.user_id.focus();
                return false;
            }

            if (!RegExp.test(f.user_id.value)) { //아이디 유효성검사
                alert("ID는 4~12자의 영문 대소문자와 숫자로만 입력하여 주세요.");
                return false;
            }

            // Password
            if (f.user_pw.value == "") {
                alert("비밀번호를 입력하세요");
                f.user_pw.focus();
                return false;
            }
            if (!RegExp.test(f.user_pw.value)) { //패스워드 유효성검사
                alert("Password는 4~12자의 영문 대소문자와 숫자로만 입력하여 주세요.");
                f.user_pw.focus();
                return false;
            }

            if (f.user_pw.value == f.user_id.value) { //패스워드와 ID가 동일한지 검사
                alert("Password는 ID와 동일하면 안됩니다.");
                f.user_pw.focus();
                return false;
            }

            if (f.user_pw2.value != f.user_pw.value) { //비밀번호와 비밀번호확인이 동일한지 검사
                alert("비밀번호가 서로 다릅니다. 다시 확인하여 입력해주세요.");
                f.user_pw2.focus();
                return false;
            }

            // Name
            if (f.user_name.value == "") {
                alert("닉네임을 입력하세요");
                f.user_name.focus();
                return false;
            }

            // Email
            if (f.user_email.value == "") {
                alert("이메일을 입력하세요");
                f.user_email.focus();
                return false;
            }
            if (!e_RegExp.test(f.user_email.value)) { //이메일 유효성 검사
                alert("올바른 이메일형식이 아닙니다.");
                return false;
            }

            // Name
            if (f.user_name.value == '') {
                alert("이름을 입력해주세요.");
                return false;
            }

            if (f.user_age.value == "") {
                alert("나이를 입력하세요");
                f.user_age.focus();
                return false;
            }
            if (f.user_gender.value == "") {
                alert("성별을 입력하세요");
                f.user_gender.focus();
                return false;
            }
            if (f.user_height.value == "") {
                alert("키를 입력하세요");
                f.user_height.focus();
                return false;
            }
            if (f.user_weight.value == "") {
                alert("몸무게를 입력하세요");
                f.user_weight.focus();
                return false;
            }

        }
    </script>
</head>

<body>
<!-- Content -->
<div class="container-xxl">
    <div class="authentication-wrapper authentication-basic container-p-y">
        <div class="authentication-inner">
            <!-- Register Card -->
            <div class="card">
                <div class="card-body">
                    <!-- Logo -->
                    <div class="app-brand justify-content-center">
                        <a href="index.html" class="app-brand-link gap-2">
                  <span class="app-brand-logo demo">
                    <svg
                            width="25"
                            viewBox="0 0 25 42"
                            version="1.1"
                            xmlns="http://www.w3.org/2000/svg"
                            xmlns:xlink="http://www.w3.org/1999/xlink"
                    >
                      <defs>
                        <path
                                d="M13.7918663,0.358365126 L3.39788168,7.44174259 C0.566865006,9.69408886 -0.379795268,12.4788597 0.557900856,15.7960551 C0.68998853,16.2305145 1.09562888,17.7872135 3.12357076,19.2293357 C3.8146334,19.7207684 5.32369333,20.3834223 7.65075054,21.2172976 L7.59773219,21.2525164 L2.63468769,24.5493413 C0.445452254,26.3002124 0.0884951797,28.5083815 1.56381646,31.1738486 C2.83770406,32.8170431 5.20850219,33.2640127 7.09180128,32.5391577 C8.347334,32.0559211 11.4559176,30.0011079 16.4175519,26.3747182 C18.0338572,24.4997857 18.6973423,22.4544883 18.4080071,20.2388261 C17.963753,17.5346866 16.1776345,15.5799961 13.0496516,14.3747546 L10.9194936,13.4715819 L18.6192054,7.984237 L13.7918663,0.358365126 Z"
                                id="path-1"
                        ></path>
                        <path
                                d="M5.47320593,6.00457225 C4.05321814,8.216144 4.36334763,10.0722806 6.40359441,11.5729822 C8.61520715,12.571656 10.0999176,13.2171421 10.8577257,13.5094407 L15.5088241,14.433041 L18.6192054,7.984237 C15.5364148,3.11535317 13.9273018,0.573395879 13.7918663,0.358365126 C13.5790555,0.511491653 10.8061687,2.3935607 5.47320593,6.00457225 Z"
                                id="path-3"
                        ></path>
                        <path
                                d="M7.50063644,21.2294429 L12.3234468,23.3159332 C14.1688022,24.7579751 14.397098,26.4880487 13.008334,28.506154 C11.6195701,30.5242593 10.3099883,31.790241 9.07958868,32.3040991 C5.78142938,33.4346997 4.13234973,34 4.13234973,34 C4.13234973,34 2.75489982,33.0538207 2.37032616e-14,31.1614621 C-0.55822714,27.8186216 -0.55822714,26.0572515 -4.05231404e-15,25.8773518 C0.83734071,25.6075023 2.77988457,22.8248993 3.3049379,22.52991 C3.65497346,22.3332504 5.05353963,21.8997614 7.50063644,21.2294429 Z"
                                id="path-4"
                        ></path>
                        <path
                                d="M20.6,7.13333333 L25.6,13.8 C26.2627417,14.6836556 26.0836556,15.9372583 25.2,16.6 C24.8538077,16.8596443 24.4327404,17 24,17 L14,17 C12.8954305,17 12,16.1045695 12,15 C12,14.5672596 12.1403557,14.1461923 12.4,13.8 L17.4,7.13333333 C18.0627417,6.24967773 19.3163444,6.07059163 20.2,6.73333333 C20.3516113,6.84704183 20.4862915,6.981722 20.6,7.13333333 Z"
                                id="path-5"
                        ></path>
                      </defs>
                      <g id="g-app-brand" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                        <g id="Brand-Logo" transform="translate(-27.000000, -15.000000)">
                          <g id="Icon" transform="translate(27.000000, 15.000000)">
                            <g id="Mask" transform="translate(0.000000, 8.000000)">
                              <mask id="mask-2" fill="white">
                                <use xlink:href="#path-1"></use>
                              </mask>
                              <use fill="#696cff" xlink:href="#path-1"></use>
                              <g id="Path-3" mask="url(#mask-2)">
                                <use fill="#696cff" xlink:href="#path-3"></use>
                                <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-3"></use>
                              </g>
                              <g id="Path-4" mask="url(#mask-2)">
                                <use fill="#696cff" xlink:href="#path-4"></use>
                                <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-4"></use>
                              </g>
                            </g>
                            <g
                                    id="Triangle"
                                    transform="translate(19.000000, 11.000000) rotate(-300.000000) translate(-19.000000, -11.000000) "
                            >
                              <use fill="#696cff" xlink:href="#path-5"></use>
                              <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-5"></use>
                            </g>
                          </g>
                        </g>
                      </g>
                    </svg>
                  </span>
                            <span class="app-brand-text demo text-body fw-bolder">Balance fit</span>
                        </a>
                    </div>
                    <!-- /Logo -->
                    <h4 class="mb-2">회원가입 🚀</h4>
                    <!--              <p class="mb-4">Make your app management easy and fun!</p>-->

                    <form id="formAuthentication" class="mb-3" method="post" action="/regUser1"
                          onsubmit="return doRegUserCheck(this);">

                        <input type="hidden" id="authCheck" name="user_auth" value="0">

                        <div class="mb-3">
                            <label for="user_id" class="form-label">아이디</label>
                            <input type="text" class="form-control" id="user_id" name="user_id" placeholder="Id" autofocus onchange="checkId()"/>
                            <span class="id_input_re_1">사용 가능한 아이디입니다.</span>
                            <span class="id_input_re_2">아이디가 이미 존재합니다.</span>
                        </div>
                        <div class="mb-3">
                            <label for="user_email" class="form-label">이메일</label>
                            <input type="text" class="form-control" id="user_email" name="user_email" placeholder="email" onchange = "checkEmail()"/>
                            <span class="email_input_re_1" style="color: green; display : none;">사용 가능한 이메일입니다.</span>
                            <span class="email_input_re_2" style="color: red; display : none;">이메일이 이미 존재합니다.</span>
                        </div>
                        <div class="mb-3 form-password-toggle">
                            <label class="form-label" for="password">비밀번호</label>
                            <div class="input-group input-group-merge">
                                <input type="password" id="password" class="form-control" name="user_pw"
                                       placeholder="&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;" aria-describedby="password"/>
                                <span class="input-group-text cursor-pointer"><i class="bx bx-hide"></i></span>
                            </div>
                        </div>
                        <div class="mb-3 form-password-toggle">
                            <label class="form-label" for="password2">비밀번호 확인</label>
                            <div class="input-group input-group-merge">
                                <input type="password" id="password2" class="form-control" name="user_pw2"
                                       placeholder="&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;" aria-describedby="password"/>
                                <span class="input-group-text cursor-pointer"><i class="bx bx-hide"></i></span>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="user_name" class="form-label">닉네임</label>
                            <input type="text" class="form-control" id="user_name" name="user_name" placeholder="nickname" autofocus/>
                        </div>
                        <div class="mb-3">
                            <label for=age class="form-label">나이</label>
                            <input type="text" class="form-control" id="age" name="user_age" placeholder="age"/>
                        </div>
                        <div class="mb-3">
                            <%--                            <label for=gender class="form-label">성별</label>--%>
                            <%--                            <input--%>
                            <%--                                    type="text"--%>
                            <%--                                    class="form-control"--%>
                            <%--                                    id="gender"--%>
                            <%--                                    name="user_gender"--%>
                            <%--                                    placeholder="gender" />--%>
                            <label class="form-label">성별</label>
                            <input type="radio" id="man" name="user_gender" value="남자"
                                   style="width: 12px;height: 12px;margin-left: 25px;"> 남자
                            <input type="radio" id="woman" name="user_gender" value="여자"
                                   style="width: 12px;height: 12px; margin-left: 12px;"> 여자
                        </div>
                        <div class="mb-3">
                            <label for="height" class="form-label">키</label>
                            <input type="text" class="form-control" id="height" name="user_height" placeholder="height"/>
                        </div>
                        <div class="mb-3">
                            <label for="weight" class="form-label">몸무게</label>
                            <input type="text" class="form-control" id="weight" name="user_weight" placeholder="weight"/>
                        </div>

<%--                        <div class="mb-3">--%>
<%--                            <div class="form-check">--%>
<%--                                <input class="form-check-input" type="checkbox" id="terms-conditions" name="terms"/>--%>
<%--                                <label class="form-check-label" for="terms-conditions">--%>
<%--                                    개인 정보 보호 정책 및 약관에 동의합니다.--%>
<%--                                    <a href="javascript:void(0);"></a>--%>
<%--                                </label>--%>
<%--                            </div>--%>
<%--                            x--%>
<%--                        </div>--%>
                        <input type="submit" class="btn btn-primary d-grid w-100" value="회원가입"/>
                    </form>

                    <p class="text-center">
                        <a href="/loginPage">
                            <span>로그인</span>
                        </a>
                    </p>
                </div>
            </div>
            <!-- Register Card -->
        </div>
    </div>
</div>
<%--<script>--%>
<%--    let m = document.querySelector('#man');--%>
<%--    let w = document.querySelector('#woman');--%>

<%--    function doGenderBMR(f) {--%>
<%--        if (m) {--%>
<%--            f.user_gender = "남자";--%>
<%--            f.user_BMR = 66 + (13.8 * f.user_weight) + (5*f.user_height) - (6.8*f.user_age);--%>
<%--        } else if (w) {--%>
<%--            f.user_gender = "여자";--%>
<%--            f.user_BMR = 655 + (9.6 * f.user_weight) + (1.8*f.user_height) - (4.7*f.user_age);--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>
<script>

</script>
<!-- / Content -->

<!--    <div class="buy-now">-->
<!--      <a-->
<!--        href="https://themeselection.com/products/sneat-bootstrap-html-admin-template/"-->
<!--        target="_blank"-->
<!--        class="btn btn-danger btn-buy-now"-->
<!--        >Upgrade to Pro</a-->
<!--      >-->
<!--    </div>-->

<!-- Core JS -->
<!-- build:js ..../static/assets/vendor/js/core.js -->
<script src="../assets/vendor/libs/jquery/jquery.js"></script>
<script src="../assets/vendor/libs/popper/popper.js"></script>
<script src="../assets/vendor/js/bootstrap.js"></script>
<script src="../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

<script src="../assets/vendor/js/menu.js"></script>
<!-- endbuild -->

<!-- Vendors JS -->

<!-- Main JS -->
<script src="../assets/js/main.js"></script>

<!-- Page JS -->

<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>
