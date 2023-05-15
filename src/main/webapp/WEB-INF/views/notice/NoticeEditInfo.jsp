<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.balansefit.util.CmmUtil" %>
<%@ page import="com.balansefit.dto.NoticeDTO" %>
<%
    NoticeDTO rDTO = (NoticeDTO) request.getAttribute("rDTO");
    //공지글 정보를 못불러왔다면, 객체 생성
    if (rDTO == null) {
        rDTO = new NoticeDTO();
    }
    int access = 1; //(작성자 : 2 / 다른 사용자: 1)
    if (CmmUtil.nvl((String) session.getAttribute("SS_USER_ID")).equals(
            CmmUtil.nvl(rDTO.getUser_id()))) {
        access = 2;
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

    <link rel="stylesheet" href="/css/table.css"/>
    <title>공지사항 수정하기</title>
    <script type="text/javascript">
        //작성자 여부체크
        function doOnload() {
            if ("<%=access%>" === "1") {
                alert("작성자만 수정할 수 있습니다.");
                location.href = "/notice/noticeList";
            }
        }

        //전송시 유효성 체크
        function doSubmit(f) {
            if (f.title.value === "") {
                alert("제목을 입력하시기 바랍니다.");
                f.title.focus();
                return false;
            }
            if (calBytes(f.title.value) > 200) {
                alert("최대 200Bytes까지 입력 가능합니다.");
                f.title.focus();
                return false;
            }
            let noticeCheck = false; //체크 여부 확인 변수
            for (let i = 0; i < f.noticeYn.length; i++) {
                if (f.noticeYn[i].checked) {
                    noticeCheck = true;
                }
            }
            if (noticeCheck === false) {
                alert("공지글 여부를 선택하시기 바랍니다.");
                f.noticeYn[0].focus();
                return false;
            }
            if (f.contents.value === "") {
                alert("내용을 입력하시기 바랍니다.");
                f.contents.focus();
                return false;
            }
            if (calBytes(f.contents.value) > 4000) {
                alert("최대 4000Bytes까지 입력 가능합니다.");
                f.contents.focus();
                return false;
            }
        }

        //글자 길이 바이트 단위로 체크하기(바이트값 전달)
        function calBytes(str) {
            let tcount = 0;
            let tmpStr = String(str);
            let strCnt = tmpStr.length;
            let onechar;
            for (let i = 0; i < strCnt; i++) {
                onechar = tmpStr.charAt(i);
                if (escape(onechar).length > 4) {
                    tcount += 2;
                } else {
                    tcount += 1;
                }
            }
            return tcount;
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
                <h1 class="display-3 text-white mb-3 animated slideInDown">Community</h1>
            </div>
        </div>
    </div>
    <!-- Navbar & Hero End -->


    <!-- Menu Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                <h5 class="section-title ff-secondary text-center text-primary fw-normal">Community</h5>

                <h2 class="mb-5">공지사항 수정하기</h2>

                <form name="f" method="post" action="/notice/noticeUpdate" onsubmit="return doSubmit(this);">
                    <input type="hidden" name="nSeq" value="<%=rDTO.getNotice_seq() %>"/>
                    <div class="divTable minimalistBlack">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableCell">제목
                                </div>
                                <div class="divTableCell">
                                    <input type="text" name="title" maxlength="100"
                                           value="<%=CmmUtil.nvl(rDTO.getTitle()) %>"
                                           style="width: 95%"/>
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
                                <div class="divTableCell">조회수
                                </div>
                                <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getRead_cnt())%>
                                </div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">내용
                                </div>
                                <div class="divTableCell">
                    <textarea name="contents"
                              style="width: 95%; height: 400px"><%=CmmUtil.nvl(rDTO.getContents()) %></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div style="margin-top: 40px;">
                            <div style="display: inline-block;width: 100px;">
                                <input type="submit" class="btn btn-primary w-100" value="수정"/>
                            </div>
                            <div style="display: inline-block;width: 100px;">
                                <input type="reset" class="btn btn-primary w-100" value="다시 작성"/>
                            </div>
                        </div>
                    </div>
                </form>
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