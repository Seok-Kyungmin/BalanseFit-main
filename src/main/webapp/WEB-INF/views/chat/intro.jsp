<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>채팅방 입장을 위한 별명 설정</title>
    <link href="../css/style-2.css" rel="stylesheet">
    <!-- Core CSS -->
    <link rel="stylesheet" href="../assets/vendor/css/core.css" class="template-customizer-core-css" />
    <link rel="stylesheet" href="../assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
    <link rel="stylesheet" href="../assets/css/demo.css" />
    <script src="/js/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            // id를 통해 button html태그 객체 가져오기
            let btnSend = document.getElementById("btnSend");

            btnSend.onclick = function () {

                // id를 통해 form html태그 객체 가져오기
                const f = document.getElementById("f");
                f.submit(); // form 태그 정보 전송하기
            }
        });
        setInterval(function () {
            $.ajax({
                url: "/chat/roomList",
                type: "get",
                dataType: "JSON",
                success: function (json) {

                    let roomHtml = "";

                    for (const room of json) {
                        roomHtml += ("<span>" + room + "</span> ");
                    }

                    $("#rooms").html(roomHtml);
                }
            });

        }, 5000)
    </script>
</head>
<body>
<div class="login-form-bg h-100">
    <div class="container h-100">
        <div class="row justify-content-center h-100">
            <div class="col-xl-6">
                <div class="form-input-content">
                    <div class="card login-form mb-0">
                        <div class="card-body pt-5">
                            <div><b>현재 오픈된 채팅방</b></div>
                            <hr/>
                            <div id="rooms"></div>
                            <br/>
                            <br/>
                            <form method="post" id="f" action="/chat/room">
                                <div><b>채팅 입장</b></div>
                                <hr/>
                                <div class="mb-3 form-password-toggle" style="width: 95%; margin-left: 3%;">
                                    <div class="d-flex justify-content-between">
                                        <label for="cName" class="form-label">채팅방 이름</label>
                                    </div>
                                    <input type="text" class="form-control" id="cName" name="roomname">
                                </div>
                                <div class="mb-3 form-password-toggle" style="width: 95%; margin-left: 3%;">
                                    <div class="d-flex justify-content-between">
                                        <label for="cNickname" class="form-label">채팅 별명 :</label>
                                    </div>
                                    <input type="text" class="form-control" id="cNickname" name="nickname">
                                </div>
                                <button class="btn btn-primary d-grid" id="btnSend"
                                        style="width: 80% !important;transform: translateX(-50%); margin-top: 50px;margin-left: 50%;">채팅방 입장</button>
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
</body>
</html>