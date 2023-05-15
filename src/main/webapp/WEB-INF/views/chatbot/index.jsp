<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello WebSocket</title>
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

<%--    <!-- Customized Bootstrap Stylesheet -->--%>
<%--    <link href="../css/bootstrap.min.css" rel="stylesheet">--%>

<%--    <!-- Template Stylesheet -->--%>
<%--    <link href=../css/style.css" rel="stylesheet">--%>

    <script src="../assets/vendor/libs/jquery/jquery.js"></script>
    <%--    <script src="../webjars/sockjs-client/sockjs.min.js"></script>--%>

    <script src="../js/stomp.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>

    <script src="../js/app.js" charset="UTF-8"></script>

    <style>
        body {
            background-color: #F1F8FF;
        }
        #main-content {
            max-width: 940px;
            padding: 2em 3em;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
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
        <div class="container-xxl py-5 bg-dark hero-header mb-5">
            <div class="container text-center my-5 pt-5 pb-4">
                <h1 class="display-3 text-white mb-3 animated slideInDown" style=" font-family:Nunito,sans-serif; font-weight: 800; font-size: 2.5rem;">Chatting</h1>
            </div>
        </div>
    </div>
    <!-- Navbar & Hero End -->

    <noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript
        being
        enabled. Please enable
        Javascript and reload this page!</h2></noscript>
    <div id="main-content" class="container">
        <div class="row">
            <div class="col-md-6">
                <form class="form-inline">
                    <div class="form-group">
                        <label for="connect">웹소켓 연결:</label>
                        <button id="connect" class="btn btn-default" type="submit">연결</button>
                        <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">해제
                        </button>
                    </div>
                </form>
            </div>
            <div class="col-md-6">
                <form class="form-inline">
                    <div class="form-group">
                        <label for="msg">문의사항</label>
                        <input type="text" id="msg" class="form-control" placeholder="내용을 입력하세요....">
                    </div>
                    <button id="send" class="btn btn-default" disabled type="submit">보내기</button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table id="conversation" class="table table-striped">
                    <thead>
                    <tr>
                        <th>메세지</th>
                    </tr>
                    </thead>
                    <tbody id="communicate">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>