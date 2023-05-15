<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko" dir="ltr">

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>식단 테스트</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
    <link href="../css/default.css" rel="stylesheet">
    <link href="../css/main.css" rel="stylesheet">

</head>

<body>
<div class="container">
    <section id="main" class="mx-auto my-5 py-5 px-3">
        <h1>나한테 딱 맞는 식단 찾기</h1>
        <div class="col-lg-6 col-md-8 col-sm-10 col-12 mx-auto">
            <img src="./img/main.png" alt="mainImage" class="img-fluid">
        </div>
        <p>
            나만의 MBTI 사이트입니다! <br>
            아래 시작하기 버튼을 눌러 시작해 주십시오.
        </p>
        <button type="button" class="btn btn-outline-danger mt-3" onclick="js:begin()">시작하기</button>
    </section>
    <section id="qna">
        <div class="status mx-auto mt-5">
            <div class="statusBar">
            </div>
        </div>
        <div class="qBox my-5 py-3 mx-auto">

        </div>
        <div class="answerBox">

        </div>
    </section>
    <section id="result">

    </section>
    <script src="./js/data.js" charset="utf-8"></script>
    <script src="./js/start.js" charset="utf-8"></script>
</div>
</body>
</html>