<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>caloric calculator</title>
    <!-- Font css -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
          rel="stylesheet">

    <style>
        .tit {
            color: #424242;
            text-align: center;
            margin: 0px;
            font-size: 45px;
            font-family: 'Noto Sans KR', sans-serif;
            font-weight: 700;
        }

        .cate {
            font-size: 40px;
            font-family: 'Noto Sans KR', sans-serif;
            font-weight: 700;
            color: #566a7f;

        }

        .value {
            display: block;
            width: 60%;
            padding: 17px 20px;
            font-size: 36px;
            font-weight: 500;
            line-height: 1.53;
            color: #697a8d;
            background-color: #fff;
            background-clip: padding-box;
            border: 3px solid #697a8d;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            border-radius: 15px;
            transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        }

        .box {
            margin: 120px 40px 20px 40px;
            height: 100%;
            border-radius: 20px;
            background-color: #fff;
            flex: 1 1 auto;
            padding: 64px;
        }

        .bt {
            display: inline-block;
            font-weight: 400;
            line-height: 1.53;
            color: #697a8d;
            text-align: center;
            vertical-align: middle;
            cursor: pointer;
            user-select: none;
            background-color: transparent;
            border: 1px solid transparent;
            padding: 20px 20px;
            font-size: 40px;
            border-radius: 20px;
            transition: all 0.2s ease-in-out;
            font-family: 'Noto Sans KR', sans-serif;
            transform: translate(5%);

        }

        .bt-primary {
            color: #fff;
            background-color: #696cff;
            border-color: #696cff;
            box-shadow: 0 0.125rem 0.25rem 0 rgb(105 108 255 / 40%);
            width: 90%;
        }

        .G {
            margin-bottom: 1rem !important;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .radio_G{
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 35px;
        }

    </style>
</head>
<body style="background-color: #EAEAF9; margin: 0px">

<div style="background-color: #fff; padding: 30px; margin: 0; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.16); box-shadow: 5px 5px 5px#bec8cb">
    <h3 class="tit">칼로리 처방</h3>
</div>
<form id="formAuthentication" class="mb-3" method="post" action="/mypage" onsubmit="return doUser_RC(this)">
<div class="box">
    <div class="G">
        <h4 class="cate">키</h4>
        <input type="text" class="value" id="height" name="user_height" placeholder="키를 입력해주세요"/>
    </div>
    <div class="G">
        <h4 class="cate">몸무게</h4>
        <input type="text" class="value" id="weight" name="user_weight" placeholder="몸무게를 입력해주세요"/>
    </div>
    <div class="G">
        <h4 class="cate">나이</h4>
        <input type="text" class="value" id="age" name="user_age" placeholder="나이를 입력해주세요"/>
    </div>
    <div class="G">
        <h4 class="cate">성별</h4>
        <div class="radio_G">
            <input type="radio" id="man" name="chk_info" value="man" style="width: 25px;height: 25px">남자
            <input type="radio" id="woman" name="chk_info" value="woman" style="width: 25px;height: 25px; margin-left: 20px;">여자
        </div>
    </div>
    <div class="G">
        <h4 class="cate">목표체중</h4>
        <input type="text" class="value" id="targetW" placeholder="목표 체중를 입력해주세요"/>
    </div>
    <div class="G">
        <h4 class="cate">목표 기간</h4>
        <input type="text" class="value" id="targetD" placeholder="목표 기간를 입력해주세요"/>
    </div>
    <div class="G">
        <h4 class="cate">활동량</h4>
        <div class="radio_G">
            <input type="radio" id="A1" name="chk_info" value="man" style="width: 25px;height: 25px">가벼운 활동<br>(앉아서 하는 일)
            <input type="radio" id="A2" name="chk_info" value="woman" style="width: 25px;height: 25px; margin-left: 20px;">중등도 활동 <br>(서서 하는 일)
        </div>
    </div>
    <div class="radio_G">
        <input type="radio" id="A3" name="chk_info" value="man" style="width: 25px;height: 25px;margin-left: 50px; margin-left: 210px;">강한 활동<br>(활동량 많은 일)
        <input type="radio" id="A4" name="chk_info" value="woman" style="width: 25px;height: 25px; margin-left: 20px;">아주 강한 활동<br>(농사,운동선수)
    </div>
    </div>
    <hr style="position: absolute; top: 0%;">
</div>
<div>
    <a href="/mypage">
        <input type="button" class="bt bt-primary" value="칼로리 처방 받기">
    </a>
</div>
</form>
<script>

    let meter;
    let sWeight;
    let cm = document.querySelector('#height');
    let m = document.querySelector('#man');
    let w = document.querySelector('#woman');
    let a1 = document.querySelector('#A1');
    let a2 = document.querySelector('#A2');
    let a3 = document.querySelector('#A3');
    let a4 = document.querySelector('#A4');



    // meter 변환
    meter = cm / 100.0;

    function doUser_RC(f) {

        // 표준 체중 계산
        if (m) {
            sWeight = (meter * meter) * 22;
        } else if (w) {
            sWeight = (meter * meter) * 21;
        }

        // 1일 적정량 계산
        if (a1) {
            f.user_RC =  sWeight * 25;
        } else if (a2){
            f.user_RC =  sWeight * 30;
        } else if (a3){
            f.user_RC =  sWeight * 35;
        } else if (a4){
            f.user_RC =  sWeight * 40;
        }
    }
</script>
</body>
</html>