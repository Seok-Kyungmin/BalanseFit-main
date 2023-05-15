<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.balansefit.util.CmmUtil" %>
<%@ page import="com.balansefit.dto.FoodDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Food List</title>
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

    <link href="../css/exerciseInfo.css" rel="stylesheet">
    <%
        session.setAttribute("SS_USER_ID", "USER01"); //세션 강제 적용, 로그인된 상태로 보여주기 위함

        List<FoodDTO> fList = (List<FoodDTO>) request.getAttribute("rList");

        //식품 정보 조회 결과 보여주기
        if (fList == null) {
            fList = new ArrayList<FoodDTO>();

        }
    %>
    <script type="text/javascript">

        //상세보기 이동
        function doDetail(seq) {
            location.href = "/user/FoodInfo?fSeq=" + seq;
        }

        function getSearchList(){
            $.ajax({
                type: 'GET',
                url : "/getSearchList",
                data : $("form[name=search-form]").serialize(),
                success : function(result){
                    //테이블 초기화
                    $('#boardtable > tbody').empty();
                    if(result.length>=1){
                        result.forEach(function(item){
                            str='<tr>'
                            str += "<td>"+item.idx+"</td>";
                            str+="<td>"+item.writer+"</td>";
                            str+="<td><a href = '/board/detail?idx=" + item.idx + "'>" + item.title + "</a></td>";
                            str+="<td>"+item.date+"</td>";
                            str+="<td>"+item.hit+"</td>";
                            str+="</tr>"
                            $('#boardtable').append(str);
                        })
                    }
                }
            })
        }
    </script>
    <style>
        .tagcloud {
            padding: 0; }
        .tagcloud a {
            text-transform: uppercase;
            display: inline-block;
            padding: 4px 10px;
            margin-bottom: 7px;
            margin-right: 4px;
            border-radius: 4px;
            color: #000000;
            border: 1px solid #ccc;
            font-size: 11px; }
        .tagcloud a:hover {
            border: 1px solid rgb(255, 128, 0); }

        .page_wrap {
            text-align:center;
            font-size:0;
        }
        .page_nation {
            display:inline-block;
        }
        .page_nation .none {
            display:none;
        }
        .page_nation a {
            display:block;
            margin:0 3px;
            float:left;
            border:1px solid #e6e6e6;
            width:28px;
            height:28px;
            line-height:28px;
            text-align:center;
            background-color:#fff;
            font-size:13px;
            color:#999999;
            text-decoration:none;
        }
        .page_nation .arrow {
            border:1px solid #ccc;
        }
        .page_nation .pprev {
            background:#f8f8f8 url('../img/page_pprev.png') no-repeat center center;
            margin-left:0;
        }
        .page_nation .prev {
            background:#f8f8f8 url('../img/page_prev.png') no-repeat center center;
            margin-right:7px;
        }
        .page_nation .next {
            background:#f8f8f8 url('../img/page_next.png') no-repeat center center;
            margin-left:7px;
        }
        .page_nation .nnext {
            background:#f8f8f8 url('../img/page_nnext.png') no-repeat center center;
            margin-right:0;
        }
        .page_nation a.active {
            background-color:#42454c;
            color:#fff;
            border:1px solid #42454c;
        }
    </style>
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
                <h1 class="display-3 text-white mb-3 animated slideInDown">Our Food</h1>
            </div>
        </div>
    </div>
    <!-- Navbar & Hero End -->


    <!-- Team Start -->
    <div class="container-xxl pt-5 pb-3">
        <div class="container">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                <h5 class="section-title ff-secondary text-center text-primary fw-normal">Foods</h5>
                <h1 class="mb-5">Our Food</h1>
            </div>
<%--            <div class="row">--%>
<%--                <form name="search-form" autocomplete="off">--%>
<%--                    <div class="position-relative mx-auto" style="max-width: 400px;">--%>

<%--                        <select class="btn btn-primary py-2 position-absolute top-0 mt-2" name="type" style="margin-left: 230px;">--%>
<%--                            <option selected value="">선택</option>--%>
<%--                            <option value="food_name">식품명</option>--%>
<%--                        </select>--%>

<%--                        <input class="form-control border-primary w-100 py-3 ps-4 pe-5" type="text" name="keyword" value="" placeholder="검색어 입력">--%>
<%--                        <input type="button" class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2" value="검색"></input>--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--            </div>--%>
            <div class="con">
                <div class="divTable minimalistBlack">
                    <div class="divTableHeading">
                        <div class="divTableRow">
                            <div class="divTableHead">식품 코드</div>
                            <div class="divTableHead">식품명</div>
                            <div class="divTableHead">칼로리</div>
                        </div>
                    </div>
                    <%
                        FoodDTO fDTO = null;

                        for (int i = 0; i < fList.size(); i++) {
                            fDTO = fList.get(i);

                            if (fDTO == null) {
                                fDTO = new FoodDTO();
                            }

                    %>
                    <div class="divTableBody">
                        <div class="divTableRow">
                            <div class="divTableCell"><%=CmmUtil.nvl(String.valueOf(fDTO.getFood_seq()))%></div>
                            <div class="divTableCell">
                                <a href="javascript:doDetail('<%=CmmUtil.nvl(String.valueOf(fDTO.getFood_seq()))%>');">
                                    <%=CmmUtil.nvl(fDTO.getFood_name())%></a></div>
                            <div class="divTableCell"><%=CmmUtil.nvl(fDTO.getFood_calories())%></div>
                        </div>
                    </div>

                    <%

                        }
                    %>
                </div>

                <nav aria-label="Page navigation example" style="margin-top: 50px;">
                    <ul class="pagination justify-content-center">
                        <li class="page-item disabled">
                            <a class="page-link">Previous</a>
                        </li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#">Next</a>
                        </li>
                    </ul>
                </nav>

            </div>
<%--            <div class="page_wrap">--%>
<%--                <th:block th:each="page : ${numbers.sequence(startPage, endPage)}">--%>
<%--                    <a class="arrow pprev" if="${page != nowPage}" href="@{/user/FoodList(page = ${page - 1})}" th:text="${page}"></a>--%>
<%--                    <strong class="arrow pprev" if="${page == nowPage}" text="${page}" style="color: red"></strong>--%>
<%--                </th:block>--%>
<%--            </div>--%>

<%--            <nav aria-label="Page navigation example">--%>
<%--                <ul class="pagination justify-content-center">--%>
<%--                    <li class="page-item disabled">--%>
<%--                        <a class="page-link">Previous</a>--%>
<%--                    </li>--%>
<%--                    <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
<%--                    <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
<%--                    <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
<%--                    <li class="page-item">--%>
<%--                        <a class="page-link" href="#">Next</a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </nav>--%>
        </div>
    </div>
</div>
<!-- Team End -->


<!-- Footer Start -->
<div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
    <div class="container py-5">
        <div class="container">
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