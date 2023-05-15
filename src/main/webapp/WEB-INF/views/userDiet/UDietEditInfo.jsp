<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.balansefit.util.CmmUtil" %>
<%@ page import="com.balansefit.dto.DietDTO" %>
<%@ page import="com.balansefit.dto.UserMadeDietDTO" %>
<%
    UserMadeDietDTO dDTO = (UserMadeDietDTO) request.getAttribute("dDTO");
    String user_id =(String) request.getAttribute("user_id");

//운동 정보를 못불러왔다면, 객체 생성
    if (dDTO == null) {
        dDTO = new UserMadeDietDTO();

    }

    int access = 1; //(작성자 : 2 / 다른 사용자: 1)

    System.out.println(CmmUtil.nvl("session에서 가져온 id: " + (String)session.getAttribute("SS_USER_ID")));
    System.out.println(CmmUtil.nvl(" 가져온 id: " + user_id));

    if (CmmUtil.nvl((String)session.getAttribute("SS_USER_ID")).equals(user_id)) {
        access = 2;
    }

    String seq = CmmUtil.nvl((String)request.getAttribute("diet_seq"));
    System.out.println("dietEdit: " + seq);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="../img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&family=Pacifico&display=swap"
          rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="../lib/animate/animate.min.css" rel="stylesheet">
    <link href="../lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="../lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet"/>

    <!-- Customized Bootstrap Stylesheet -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="../css/style.css" rel="stylesheet">

    <title>Diet Edit Info</title>
    <script type="text/javascript">

        //작성자 여부체크
        function doOnload() {

            if ("<%=access%>" == "1") {
                alert("작성자만 수정할 수 있습니다.");
                location.href = "/userDiet/UDietList";

            }
        }

        //전송시 유효성 체크
        function doSubmit(f) {

            if (f.diet_name.value == "") {
                alert("식단 명을 입력하시기 바랍니다.");
                f.diet_name.focus();
                return false;
            }

            if (f.food_m_1.value == "") {
                alert("아침 식단 식품명을 입력하시기 바랍니다.");
                f.food_m_1.focus();
                return false;
            }

            if (f.food_mc_1.value == "") {
                alert("수량을 입력하시기 바랍니다.");
                f.food_mc_1.focus();
                return false;
            }

            if (f.food_a_1.value == "") {
                alert("점심 식단 식품명을 입력하시기 바랍니다.");
                f.food_a_1.focus();
                return false;
            }

            if (f.food_ac_1.value == "") {
                alert("수량을 입력하시기 바랍니다.");
                f.food_ac_1.focus();
                return false;
            }

            if (f.food_d_1.value == "") {
                alert("저녁 식단 명을 입력하시기 바랍니다.");
                f.food_d_1.focus();
                return false;
            }

            if (f.food_dc_1.value == "") {
                alert("수량을 입력하시기 바랍니다.");
                f.food_dc_1.focus();
                return false;
            }

            if (f.diet_calories.value == "") {
                alert("총칼로리을 입력하시기 바랍니다.");
                f.diet_calories.focus();
                return false;
            }
        }

    </script>
</head>
<body onload="doOnload();">
<div class="container-xxl bg-white p-0">
    <!-- Spinner Start -->
    <div id="spinner"
         class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Spinner End -->


    <!-- Navbar & Hero Start -->
    <div class="container-xxl position-relative p-0">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4 px-lg-5 py-3 py-lg-0">
            <a href="/userIndex" class="navbar-brand p-0">
                <h1 class="text-primary m-0"><i class="fa fa-utensils me-3"></i>Balance fit</h1>
                <!-- <img src="img/logo.png" alt="Logo"> -->
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="fa fa-bars"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto py-0 pe-4">
                    <a href="/userIndex" class="nav-item nav-link">Home</a>
                    <a href="/user/DietList" class="nav-item nav-link">Diet</a>
                    <a href="/user/ExerciseList" class="nav-item nav-link">Exercise</a>
                    <a href="/user/FoodList" class="nav-item nav-link">Food</a>
                    <a href="/Map" class="nav-item nav-link">Diet Restaurant</a>
                    <a href="/notice/noticeList" class="nav-item nav-link">Community</a>
                    <a href="/mypage" class="nav-item nav-link">MyPage</a>
                    <a href="/mail/mailForm" class="nav-item nav-link">Contact</a>
                </div>
                <a href="/loginPage" class="btn btn-primary py-2 px-4">LogOut</a>
            </div>
        </nav>

        <div class="container-xxl py-5 bg-dark hero-header mb-5">
            <div class="container text-center my-5 pt-5 pb-4">
                <h1 class="display-3 text-white mb-3 animated slideInDown">Diet Info</h1>
            </div>
        </div>
    </div>
    <!-- Navbar & Hero End -->

    <!-- Menu Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                <h5 class="section-title ff-secondary text-center text-primary fw-normal">Diet</h5>
                <h1 class="mb-5">식단정보 수정해주세요</h1>
                <form name="f" method="post" action="/admin/DietUpdate" onsubmit="return doSubmit(this);">
                    <div>
                        <input type="hidden" name="dSeq" value="<%=CmmUtil.nvl(request.getParameter("dSeq")) %>"/>
                        <div style="text-align: center;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="name" name="diet_name"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_diet_name()) %>" style="width: 340px; ">
                                <label for="name">식단 이름</label>
                            </div>
                        </div>
                        <h3>아침</h3>
                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_m_1" name="food_m_1"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_m_1()) %>" style="width: 340px; ">
                                <label for="food_m_1">아침식단 식품명</label>
                            </div>
                        </div>
                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_mc_1" name="food_mc_1"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_mc_1()) %>" style="width: 340px; ">
                                <label for="food_mc_1">아침식단 식품 수량</label>
                            </div>
                        </div>

                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_m_2" name="food_m_2"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_m_2()) %>" style="width: 340px; ">
                                <label for="food_m_2">아침식단 식품명</label>
                            </div>
                        </div>
                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_mc_2" name="food_mc_2"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_mc_2()) %>" style="width: 340px; ">
                                <label for="food_mc_2">아침식단 식품 수량</label>
                            </div>
                        </div>

                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_m_3" name="food_m_3"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_m_3()) %>" style="width: 340px; ">
                                <label for="food_m_3">아침식단 식품명</label>
                            </div>
                        </div>
                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_mc_3" name="food_mc_3"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_mc_3()) %>" style="width: 340px; ">
                                <label for="food_mc_3">아침식단 식품 수량</label>
                            </div>
                        </div>
                    <h3>점심</h3>
                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_a_1" name="food_a_1"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_a_1()) %>" style="width: 340px; ">
                                <label for="food_a_1">점심 식단 식품명</label>
                            </div>
                        </div>
                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_ac_1" name="food_ac_1"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_ac_1()) %>" style="width: 340px; ">
                                <label for="food_ac_1">점심 식단 식품 수량</label>
                            </div>
                        </div>

                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_a_2" name="food_a_2"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_a_2()) %>" style="width: 340px; ">
                                <label for="food_a_2">점심식단 식품명</label>
                            </div>
                        </div>
                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_ac_2" name="food_ac_2"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_ac_2()) %>" style="width: 340px; ">
                                <label for="food_ac_2">점심식단 식품 수량</label>
                            </div>
                        </div>

                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_a_3" name="food_a_3"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_a_3()) %>" style="width: 340px; ">
                                <label for="food_a_3">점심식단 식품명</label>
                            </div>
                        </div>
                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_ac_3" name="food_ac_3"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_ac_3()) %>" style="width: 340px; ">
                                <label for="food_ac_3">점심식단 식품 수량</label>
                            </div>
                        </div>
                    <h3>저녁</h3>
                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_d_1" name="food_d_1"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_d_1()) %>" style="width: 340px; ">
                                <label for="food_d_1">저녁식단 식품명</label>
                            </div>
                        </div>
                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_dc_1" name="food_dc_1"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_dc_1()) %>" style="width: 340px; ">
                                <label for="food_dc_1">저녁식단 식품 수량</label>
                            </div>
                        </div>

                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_d_2" name="food_d_2"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_d_2()) %>" style="width: 340px; ">
                                <label for="food_d_2">저녁식단 식품명</label>
                            </div>
                        </div>
                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_dc_2" name="food_dc_2"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_dc_2()) %>" style="width: 340px; ">
                                <label for="food_dc_2">저녁식단 식품 수량</label>
                            </div>
                        </div>

                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_d_3" name="food_d_3"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_d_3()) %>" style="width: 340px; ">
                                <label for="food_d_3">저녁식단 식품명</label>
                            </div>
                        </div>
                        <div style="text-align: center; margin-top: 15px;">
                            <div class="form-floating" style="display: inline-block;">
                                <input type="text" class="form-control" id="food_dc_3" name="food_dc_3"
                                       value="<%=CmmUtil.nvl(dDTO.getUser_food_dc_3()) %>" style="width: 340px; ">
                                <label for="food_dc_3">저녁식단 식품 수량</label>
                            </div>
                        </div>

                        <div class="col-12"
                             style="display: flex;justify-content: center; align-items: center;margin-top: 25px;">
                            <button class="btn btn-primary py-2" type="submit">등록</button>
                            <button class="btn btn-primary py-2" style="margin-left: 10px;" type="reset">다시 작성</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>

    <!-- Footer Start -->
    <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container py-5">

        </div>
    </div>
    <!-- Footer End -->


    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../lib/wow/wow.min.js"></script>
    <script src="../lib/easing/easing.min.js"></script>
    <script src="../lib/waypoints/waypoints.min.js"></script>
    <script src="../lib/counterup/counterup.min.js"></script>
    <script src="../lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="../lib/tempusdominus/js/moment.min.js"></script>
    <script src="../lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="../lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="../js/main.js"></script>
</div>
</body>
</html>