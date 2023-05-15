<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.balansefit.dto.NoticeDTO" %>
<%@ page import="com.balansefit.util.CmmUtil" %>
<%
    NoticeDTO rDTO = (NoticeDTO) request.getAttribute("rDTO");
//공지글 정보를 못불러왔다면, 객체 생성
    if (rDTO == null) {
        System.out.println("정보 불러오는 것을 실패했습니다");
        rDTO = new NoticeDTO();
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
    String user_id = CmmUtil.nvl(rDTO.getUser_id());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
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

    <title>게시판 글보기</title>
    <link rel="stylesheet" href="/css/table.css"/>
    <script type="text/javascript">
        //수정하기
        function doEdit() {
            if ("<%=edit%>" === "2") {
                alert("수정하기 이동");
                location.href = "/notice/noticeEditInfo?nSeq=<%=rDTO.getNotice_seq()%>";
            } else if ("<%=edit%>" === "3") {
                alert("로그인 하시길 바랍니다.");
            } else {
                alert("본인이 작성한 글만 수정 가능합니다.");
            }
        }

        //삭제하기
        function doDelete() {
            if ("<%=edit%>" === "2") {
                if (confirm("작성한 글을 삭제하시겠습니까?")) {
                    location.href = "/notice/noticeDelete?nSeq=<%=rDTO.getNotice_seq()%>";
                }
            } else if ("<%=edit%>" === "3") {
                alert("로그인 하시길 바랍니다.");
            } else {
                alert("본인이 작성한 글만 삭제 가능합니다.");
            }
        }

        //목록으로 이동
        function doList() {
            location.href = "/notice/noticeList";
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
                <h1 class="display-3 text-white mb-3 animated slideInDown">공지사항 상세보기</h1>
            </div>
        </div>
    </div>
    <!-- Navbar & Hero End -->

    <!-- Menu Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                <h5 class="section-title ff-secondary text-center text-primary fw-normal">Community</h5>
                <h2 class="mb-5">공지사항 상세보기</h2>

                <div class="divTable minimalistBlack" style="margin-left: 5%;margin-bottom: 100px">
                    <div class="divTableBody">
                        <div class="divTableRow">
                            <div class="divTableCell">제목
                            </div>
                            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getTitle())%>
                            </div>
                        </div>
                        <div class="divTableRow">
                            <div class="divTableCell">공지글 여부
                            </div>
                            <div class="divTableCell">
                                예<input type="radio" name="noticeYn" value="Y"
                                    <%=CmmUtil.checked(CmmUtil.nvl(rDTO.getNotice_yn()), "Y") %>/>
                                아니오<input type="radio" name="noticeYn" value="N"
                                    <%=CmmUtil.checked(CmmUtil.nvl(rDTO.getNotice_yn()), "N") %>/>
                            </div>
                        </div>
                        <div class="divTableRow">
                            <div class="divTableCell">작성일
                            </div>
                            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getReg_dt())%>
                            </div>
                        </div>
                        <div class="divTableRow">
                            <div class="divTableCell">조회수
                            </div>
                            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getRead_cnt())%>
                            </div>
                        </div>
                        <div class="divTableRow">
                            <div class="divTableCell">내용
                            </div>
                            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getContents()).replaceAll("\r\n", "<br/>") %>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card my-4" style="width: 80%;margin: auto;">
                    <h5 class="card-header">Leave a Comment:</h5>
                    <div class=" card-body">
                        <form name="comment-form" action="/board/comment/write" method="post" autocapitalize="off">
                            <div class="form-group">
                                <input type="hidden" name="idx" th:value="*{idx}" />
                                <textarea name="content" class="form-control" rows="3"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary" style="float: right; margin-top: 10px;">Submit</button>
                        </form>
                    </div>
                </div>

                <div class="card" style="margin-top: 30px; width: 80%;margin: auto;">
                    <div class="card-header">Comment List</div>
                    <ul id="comment--box" class="list-group">
                        <li id="comment--1" class="list-group-item d-flex justify-content-between">
                            <div>댓글 내용입니다!</div>
                            <div class="d-flex">
                                <div class="font-italic">작성자:kyungmin &nbsp;</div>
                                <button class="btn btn-primary">Delete</button>
                            </div>
                        </li>
                    </ul>
                </div>
                <div>
                    <div style="margin-top: 40px;">
                        <div style="display: inline-block;width: 100px;">
                            <a href="javascript:doEdit();" class="btn btn-primary w-100">수정</a>
                        </div>
                        <div style="display: inline-block; width: 100px;">
                            <a href="javascript:doDelete();" class="btn btn-primary w-100">삭제</a>
                        </div>
                        <div style="display: inline-block; width: 100px;">
                            <a href="javascript:doList();" class="btn btn-primary w-100">목록</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- Footer Start -->
<div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
    <div class="container py-5">
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