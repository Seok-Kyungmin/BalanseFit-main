<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.balansefit.dto.ExerciseDTO" %>
<%@ page import="com.balansefit.util.CmmUtil" %>
<%@ page import="com.balansefit.dto.UserInfoDTO" %>
<%
    UserInfoDTO rDTO = (UserInfoDTO) request.getAttribute("rDTO");

    //공지글 정보를 못불러왔다면, 객체 생성
    if (rDTO == null) {
        System.out.println("정보 불러오는 것을 실패했습니다");
        rDTO = new UserInfoDTO();

    }

    String ss_user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));

//본인이 작성한 글만 수정 가능하도록 하기(1:작성자 아님 / 2: 본인이 작성한 글 / 3: 로그인안함)
    int edit = 1;

//로그인 안했다면....
    if (ss_user_id.equals("")) {
        edit = 3;

//본인이 작성한 글이면 2가 되도록 변경
    } else if (ss_user_id.equals(CmmUtil.nvl(rDTO.getUser_id()))) {
        edit = 2;

    }

    System.out.println("user_id : " + CmmUtil.nvl(rDTO.getUser_id()));
    System.out.println("ss_user_id : " + ss_user_id);
    String user_name = CmmUtil.nvl(rDTO.getUser_name());
    String user_email = CmmUtil.nvl(rDTO.getUser_email());
    String user_age = CmmUtil.nvl(rDTO.getUser_age());
    String user_gender = CmmUtil.nvl(rDTO.getUser_gender());
    String user_height = CmmUtil.nvl(rDTO.getUser_height());
    String user_weight = CmmUtil.nvl(rDTO.getUser_weight());


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

    <title>회원정보 보기</title>
    <script type="text/javascript">

        //삭제하기
        function doDelete() {
            if ("<%=edit%>" == 2) {
                if (confirm("회원을 삭제하시겠습니까?")) {
                    location.href = "/admin/AdminUserDelete?user_id=<%=CmmUtil.nvl(rDTO.getUser_id())%>";
                }
            } else if ("<%=edit%>" == 3) {
                alert("로그인 하시길 바랍니다.");

            } else {
                alert("본인이 작성한 글만 삭제 가능합니다.");

            }
        }

        //목록으로 이동
        function doList() {
            window.location.href = "/admin/AdminUserList";

        }
    </script>

</head>
<body>
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
            <a href="/adminIndex" class="navbar-brand p-0">
                <h1 class="text-primary m-0"><i class="fa fa-utensils me-3"></i>Balance fit</h1>
                <!-- <img src="img/logo.png" alt="Logo"> -->
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="fa fa-bars"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto py-0 pe-4">
                    <a href="/index" class="nav-item nav-link">Home</a>
                    <a href="/admin/DietList" class="nav-item nav-link">Diet</a>
                    <a href="/admin/ExerciseList" class="nav-item nav-link">Exercise</a>
                    <a href="/admin/AdminUserList" class="nav-item nav-link">User</a>
                    <a href="/admin/FoodList" class="nav-item nav-link">Food</a>
                </div>
                <a href="/loginPage" class="btn btn-primary py-2 px-4">Log out</a>
            </div>
        </nav>

        <div class="container-xxl py-5 bg-dark hero-header mb-5">
            <div class="container text-center my-5 pt-5 pb-4">
                <h1 class="display-3 text-white mb-3 animated slideInDown">User Info</h1>
            </div>
        </div>
    </div>
    <!-- Navbar & Hero End -->


    <!-- Menu Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                <h5 class="section-title ff-secondary text-center text-primary fw-normal">User Info</h5>
                <h1 class="mb-5">회원 상세 정보</h1>
                <div>
                    <div style="text-align: center;">
                        <div class="form-floating" style="display: inline-block;">
                                <span class="form-control" id="name" style="width: 340px;">
                                    <%=user_name%>
                                </span>
                            <label for="name">User Name</label>
                        </div>
                    </div>

                    <div style="text-align: center; margin-top: 15px;">
                        <div class="form-floating" style="display: inline-block;">
                            <div class="form-control" id="email" style="width: 340px;">
                                <%=user_email%>
                            </div>
                            <label for="email">User Email</label>
                        </div>
                    </div>

                    <div style="text-align: center; margin-top: 15px;">
                        <div class="form-floating" style="display: inline-block;">
                            <div class="form-control" id="age" style="width: 340px;">
                                <%=user_age%>
                            </div>
                            <label for="age">User Age</label>
                        </div>
                    </div>

                    <div style="text-align: center; margin-top: 15px;">
                        <div class="form-floating" style="display: inline-block;">
                            <div class="form-control" id="gender" style="width: 340px;">
                                <%=user_gender%>
                            </div>
                            <label for="gender">User Gender</label>
                        </div>
                    </div>

                    <div style="text-align: center; margin-top: 15px;">
                        <div class="form-floating" style="display: inline-block;">
                            <div class="form-control" id="height" style="width: 340px;">
                                <%=user_height%>
                            </div>
                            <label for="height">User Height</label>
                        </div>
                    </div>

                    <div style="text-align: center; margin-top: 15px;">
                        <div class="form-floating" style="display: inline-block;">
                            <div class="form-control" id="weight" style="width: 340px;">
                                <%=user_weight%>
                            </div>
                            <label for="weight">User Weight</label>
                        </div>
                    </div>
                    <div style="text-align: center;margin-top: 25px;">
                        <div style="display: inline-block;width: 100px;">
                            <%--                                <a href="javascript:doDelete();">--%>
                            <button type="button" class="btn btn-primary w-100" onclick="doDelete()">삭제</button>
                            <%--                                </a>--%>
                        </div>

                        <div style="display: inline-block;width: 100px;">
                            <a href="javascript:doList();">
                                <button type="button" class="btn btn-primary w-100" onclick="doList()">목록</button>
                            </a>
                        </div>
                    </div>

                </div>

            </div>

        </div>
    </div>
    <!-- Menu End -->


    <!-- Footer Start -->
    <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container py-5">

        </div>
    </div>
    <!-- Footer End -->


    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
</div>

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
</body>
</html>