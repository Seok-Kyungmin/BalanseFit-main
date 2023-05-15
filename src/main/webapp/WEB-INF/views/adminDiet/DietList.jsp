<%@ page import="com.balansefit.dto.ExerciseDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.balansefit.dto.DietDTO" %>
<%@ page import="com.balansefit.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Restoran - Bootstrap Restaurant Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="../img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&family=Pacifico&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="../lib/animate/animate.min.css" rel="stylesheet">
    <link href="../lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="../lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="../css/style.css" rel="stylesheet">

    <%
        session.setAttribute("SS_USER_ID", "USER01"); //세션 강제 적용, 로그인된 상태로 보여주기 위함

        List<DietDTO> dList = (List<DietDTO>) request.getAttribute("dList");

        //식단 정보 조회 결과 보여주기
        if (dList == null) {
            dList = new ArrayList<DietDTO>();

        }
    %>
    <script type="text/javascript">

        //상세보기 이동
        function doDetail(seq) {
            location.href = "/admin/DietInfo?dSeq=" + seq;
        }

    </script>
</head>

<body>
<div class="container-xxl bg-white p-0">
    <!-- Spinner Start -->
    <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
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
                <a href="" class="btn btn-primary py-2 px-4">Log out</a>
            </div>
        </nav>

        <div class="container-xxl py-5 bg-dark hero-header mb-5">
            <div class="container text-center my-5 pt-5 pb-4">
                <h1 class="display-3 text-white mb-3 animated slideInDown">Diet List</h1>
            </div>
        </div>
    </div>
    <!-- Navbar & Hero End -->


    <!-- Menu Start -->

    <div class="container">
        <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
            <h5 class="section-title ff-secondary text-center text-primary fw-normal">Diet List</h5>
            <h1 class="mb-5">List Items</h1>
            <div style="text-align: right;margin-bottom: 30px;">
                <a href="/admin/DietReg" class="btn btn-primary py-2 px-4">추가하기</a>
            </div>
        </div>
        <%
            DietDTO dDTO = null;

            for (int i = 0; i < dList.size(); i++) {
                dDTO = dList.get(i);

                if (dDTO == null) {
                    dDTO = new DietDTO();
                }


        %>
        <div style="display: flex">
            <div class="container-xxl py-5" style="border: 2px solid; color: #FEA116FF; width: 450px; height: 424px; margin: 10px">
                <div class="tab-class text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h5><%=CmmUtil.nvl(dDTO.getDiet_name())%></h5>
                    <h4 class="text-primary"><%=CmmUtil.nvl(dDTO.getDiet_calories())%>kcal</h4>
                    <ul class="nav nav-pills d-inline-flex justify-content-center border-bottom mb-5">
                        <li class="nav-item">
                            <a class="d-flex align-items-center text-start mx-3 ms-0 pb-3 active" data-bs-toggle="pill" href="#tab-1">
                                <!--                                <i class="fa fa-coffee fa-2x text-primary"></i>-->
                                <div class="ps-3">
                                    <!--                                    <small class="text-body">Popular</small>-->
                                    <h6 class="mt-n1 mb-0">Breakfast</h6>
                                </div>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="d-flex align-items-center text-start mx-3 pb-3" data-bs-toggle="pill" href="#tab-2">
                                <!--                                <i class="fa fa-hamburger fa-2x text-primary"></i>-->
                                <div class="ps-3">
                                    <!--                                    <small class="text-body">Special</small>-->
                                    <h6 class="mt-n1 mb-0">Launch</h6>
                                </div>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="d-flex align-items-center text-start mx-3 me-0 pb-3" data-bs-toggle="pill" href="#tab-3">
                                <!--                                <i class="fa fa-utensils fa-2x text-primary"></i>-->
                                <div class="ps-3">
                                    <!--                                    <small class="text-body">Lovely</small>-->
                                    <h6 class="mt-n1 mb-0">Dinner</h6>
                                </div>
                            </a>
                        </li>
                    </ul>
                    <div class="tab-content">

                        <div id="tab-1" class="tab-pane fade show p-0 active">
                            <div class="row g-4">

                                <div>
                                    <div class="d-flex align-items-center">
                                        <!--                                        <img class="flex-shrink-0 img-fluid rounded" src="img/menu-1.jpg" alt="" style="width: 80px;">-->
                                        <div class="w-100 d-flex flex-column text-start ps-4">
                                            <h5 class="d-flex justify-content-between border-bottom pb-2">
                                                <span><%=CmmUtil.nvl(dDTO.getFood_m_1())%></span>
                                                <span class="text-primary"><%=CmmUtil.nvl(dDTO.getFood_mc_1())%></span>
                                            </h5>
<%--                                            <small class="fst-italic"><%=CmmUtil.nvl(dDTO.getFood_mc_1())%></small>--%>
                                        </div>
                                    </div>
                                </div>

                                <div>
                                    <div class="d-flex align-items-center">
                                        <!--                                        <img class="flex-shrink-0 img-fluid rounded" src="img/menu-1.jpg" alt="" style="width: 80px;">-->
                                        <div class="w-100 d-flex flex-column text-start ps-4">
                                            <h5 class="d-flex justify-content-between border-bottom pb-2">
                                                <span><%=CmmUtil.nvl(dDTO.getFood_m_2())%></span>
                                                <span class="text-primary"><%=CmmUtil.nvl(dDTO.getFood_mc_2())%></span>
                                            </h5>
<%--                                            <small class="fst-italic"><%=CmmUtil.nvl(dDTO.getFood_mc_2())%></small>--%>
                                        </div>
                                    </div>
                                </div>

                                <div>
                                    <div class="d-flex align-items-center">
                                        <!--                                        <img class="flex-shrink-0 img-fluid rounded" src="img/menu-1.jpg" alt="" style="width: 80px;">-->
                                        <div class="w-100 d-flex flex-column text-start ps-4">
                                            <h5 class="d-flex justify-content-between border-bottom pb-2">
                                                <span><%=CmmUtil.nvl(dDTO.getFood_m_3())%></span>
                                                <span class="text-primary"><%=CmmUtil.nvl(dDTO.getFood_mc_3())%></span>
                                            </h5>
                                            <%--                                            <small class="fst-italic"><%=CmmUtil.nvl(dDTO.getFood_ac_1())%></small>--%>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div id="tab-2" class="tab-pane fade show p-0">
                            <div class="row g-4">

                                <div>
                                    <div class="d-flex align-items-center">
<!--                                        <img class="flex-shrink-0 img-fluid rounded" src="img/menu-1.jpg" alt="" style="width: 80px;">-->
                                        <div class="w-100 d-flex flex-column text-start ps-4">
                                            <h5 class="d-flex justify-content-between border-bottom pb-2">
                                                <span><%=CmmUtil.nvl(dDTO.getFood_a_1())%></span>
                                                <span class="text-primary"><%=CmmUtil.nvl(dDTO.getFood_ac_1())%></span>
                                            </h5>
<%--                                            <small class="fst-italic"><%=CmmUtil.nvl(dDTO.getFood_ac_1())%></small>--%>
                                        </div>
                                    </div>
                                </div>

                                <div>
                                    <div class="d-flex align-items-center">
                                        <!--                                        <img class="flex-shrink-0 img-fluid rounded" src="img/menu-1.jpg" alt="" style="width: 80px;">-->
                                        <div class="w-100 d-flex flex-column text-start ps-4">
                                            <h5 class="d-flex justify-content-between border-bottom pb-2">
                                                <span><%=CmmUtil.nvl(dDTO.getFood_a_2())%></span>
                                                <span class="text-primary"><%=CmmUtil.nvl(dDTO.getFood_ac_2())%></span>
                                            </h5>
<%--                                            <small class="fst-italic"><%=CmmUtil.nvl(dDTO.getFood_ac_2())%></small>--%>
                                        </div>
                                    </div>
                                </div>

                                <div>
                                    <div class="d-flex align-items-center">
                                        <!--                                        <img class="flex-shrink-0 img-fluid rounded" src="img/menu-1.jpg" alt="" style="width: 80px;">-->
                                        <div class="w-100 d-flex flex-column text-start ps-4">
                                            <h5 class="d-flex justify-content-between border-bottom pb-2">
                                                <span><%=CmmUtil.nvl(dDTO.getFood_a_3())%></span>
                                                <span class="text-primary"><%=CmmUtil.nvl(dDTO.getFood_ac_3())%></span>
                                            </h5>
<%--                                            <small class="fst-italic"><%=CmmUtil.nvl(dDTO.getFood_ac_3())%></small>--%>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div id="tab-3" class="tab-pane fade show p-0">
                            <div class="row g-4">

                                <div>
                                    <div class="d-flex align-items-center">
                                        <!--                                        <img class="flex-shrink-0 img-fluid rounded" src="img/menu-1.jpg" alt="" style="width: 80px;">-->
                                        <div class="w-100 d-flex flex-column text-start ps-4">
                                            <h5 class="d-flex justify-content-between border-bottom pb-2">
                                                <span><%=CmmUtil.nvl(dDTO.getFood_d_1())%></span>
                                                <span class="text-primary"><%=CmmUtil.nvl(dDTO.getFood_dc_1())%></span>
                                            </h5>
<%--                                            <small class="fst-italic"><%=CmmUtil.nvl(dDTO.getFood_dc_1())%></small>--%>
                                        </div>
                                    </div>
                                </div>

                                <div>
                                    <div class="d-flex align-items-center">
                                        <!--                                        <img class="flex-shrink-0 img-fluid rounded" src="img/menu-1.jpg" alt="" style="width: 80px;">-->
                                        <div class="w-100 d-flex flex-column text-start ps-4">
                                            <h5 class="d-flex justify-content-between border-bottom pb-2">
                                                <span><%=CmmUtil.nvl(dDTO.getFood_d_2())%></span>
                                                <span class="text-primary"><%=CmmUtil.nvl(dDTO.getFood_dc_2())%></span>
                                            </h5>
<%--                                            <small class="fst-italic"><%=CmmUtil.nvl(dDTO.getFood_dc_2())%></small>--%>
                                        </div>
                                    </div>
                                </div>

                                <div>
                                    <div class="d-flex align-items-center">
                                        <!--                                        <img class="flex-shrink-0 img-fluid rounded" src="img/menu-1.jpg" alt="" style="width: 80px;">-->
                                        <div class="w-100 d-flex flex-column text-start ps-4">
                                            <h5 class="d-flex justify-content-between border-bottom pb-2">
                                                <span><%=CmmUtil.nvl(dDTO.getFood_d_3())%></span>
                                                <span class="text-primary"><%=CmmUtil.nvl(dDTO.getFood_dc_3())%></span>
                                            </h5>
                                            <%--                                            <small class="fst-italic"><%=CmmUtil.nvl(dDTO.getFood_dc_2())%></small>--%>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <a href="javascript:doDetail('<%=CmmUtil.nvl(String.valueOf(dDTO.getDiet_seq()))%>');" class="btn btn-primary py-2 px-4">수정하기</a>
                    </div>
                </div>
            </div>
        </div>

        <%
            }
        %>

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