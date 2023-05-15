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

    <script type="text/javascript">

        // 유효성 체크는 반드시 해줄 것
        function check(f) {
            console.log("함수 실행");

            if (toMail.value == "") {
                alert("이메일을 입력해주세요");
                f.toMail.focus();
                return false;
            }
            if (title.value == "") {
                alert("제목을 입력해주세요");
                f.title.focus();
                return false;
            }
            if (contents.value == "") {
                alert("내용을 입력해주세요");
                f.contents.focus();
                return false;
            }
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
                <h1 class="display-3 text-white mb-3 animated slideInDown">Contact Us</h1>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb justify-content-center text-uppercase">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item"><a href="#">Pages</a></li>
                        <li class="breadcrumb-item text-white active" aria-current="page">Contact</li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
    <!-- Navbar & Hero End -->


    <!-- Contact Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                <h5 class="section-title ff-secondary text-center text-primary fw-normal">Contact Us</h5>
                <h1 class="mb-5">Contact For Any Query</h1>
            </div>
            <div class="row g-4">
                <div class="col-12">
                    <div class="row gy-4">
                        <div class="col-md-4">
                            <h5 class="section-title ff-secondary fw-normal text-start text-primary">Email</h5>
                            <p><i class="fa fa-envelope-open text-primary me-2"></i>sukm386@naver.com</p>
                        </div>
                        <div class="col-md-4">
                            <h5 class="section-title ff-secondary fw-normal text-start text-primary">Gmail</h5>
                            <p><i class="fa fa-envelope-open text-primary me-2"></i>sukm38621@gmail.com</p>
                        </div>
                        <div class="col-md-4">
                            <h5 class="section-title ff-secondary fw-normal text-start text-primary">Phone number</h5>
                            <p><i class="fa fa-phone-alt text-primary me-2"></i>010 - 8851 -3607</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 wow fadeIn" data-wow-delay="0.1s">
<%--                    <!-- 이미지 지도를 표시할 div 입니다 -->--%>
<%--                    <div id="map" style="width:600px;height:350px;"></div>--%>
<%--                    <p><em>마커를 클릭해주세요!</em></p>--%>
<%--                    <!-- KAKAO API -->--%>
<%--                    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ca041a45c0ed421d3ed105f7173662b0"></script>--%>
<%--                    <script>--%>
<%--                        var mapContainer = document.getElementById('map'), // 지도를 표시할 div--%>
<%--                            mapOption = {--%>
<%--                                center: new kakao.maps.LatLng(37.549926, 126.842417), // 지도의 중심좌표--%>
<%--                                level: 3 // 지도의 확대 레벨--%>
<%--                            };--%>

<%--                        var map = new kakao.maps.Map(mapContainer, mapOption);--%>

<%--                        // 마커가 표시될 위치입니다--%>
<%--                        var markerPosition  = new kakao.maps.LatLng(37.549926, 126.842417);--%>

<%--                        // 마커를 생성합니다--%>
<%--                        var marker = new kakao.maps.Marker({--%>
<%--                            position: markerPosition--%>
<%--                        });--%>

<%--                        // 마커가 지도 위에 표시되도록 설정합니다--%>
<%--                        marker.setMap(map);--%>

<%--                        var iwContent = '<div style="padding:5px;">BalanceFit<br><a href="https://map.kakao.com/link/map/BalanceFit,37.549926,126.842417" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/BalanceFit,37.549926,126.842417" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다--%>
<%--                            iwPosition = new kakao.maps.LatLng(37.549926, 126.842417); //인포윈도우 표시 위치입니다--%>

<%--                        // 인포윈도우를 생성합니다--%>
<%--                        var infowindow = new kakao.maps.InfoWindow({--%>
<%--                            position : iwPosition,--%>
<%--                            content : iwContent--%>
<%--                        });--%>

<%--                        // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다--%>
<%--                        infowindow.open(map, marker);--%>
<%--                    </script>--%>
                                    <div class="service-item rounded pt-3">
                        <div class="p-4">
                            <i class="fa fa-3x fa-headset text-primary mb-4"></i>
                            <h5>24/7 Service</h5>
                            <p>Balance Fit 고객 지원 센터 어시스턴스</p>
                        </div>
                    </div>


                    <div class="col-12" href="/chatbot" style="margin-top: 1rem;">
                        <button class="btn btn-primary w-100 py-3" type="submit"><a href="/chat/intro" style="color: #FFFFFF">Chatting room</a></button>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="wow fadeInUp" data-wow-delay="0.2s">
                        <form action="/mail/sendMailProc" class="sendMailAction" name="f" onsubmit="return check(this);">
                            <div class="row g-3">
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="email" class="form-control" id="toMail" name="toMail" value="" placeholder="Recipient Email">
                                        <label for="toMail">Recipient Email</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="title" name="title" value="" placeholder="Subject">
                                        <label for="title">Subject</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating">
                                        <textarea class="form-control" placeholder="Leave a message here" id="contents" name="contents" style="height: 150px"></textarea>
                                        <label for="contents">Message</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <button class="btn btn-primary w-100 py-3">Send Message</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Contact End -->


    <!-- Footer Start -->
    <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container py-5">
            <div class="row g-5">
            </div>
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