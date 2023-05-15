package com.balansefit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class RecipeDTO {

    String rcp_seq;    // 레시피 번호
    String rcp_nm;  // 메뉴명
    String rcp_wgt; // 요리 종류
    String info_wgt;    // 중량(1인분)
    String info_eng;   // 열량
    String info_car;   // 탄수화물
    String info_pro;   // 단백질
    String info_fat;   // 지방
    String info_na;    // 나트륨
    String rcp_parts_dtls;  // 재료정보
    String manual01;    // 만드는 법1
    String manual02;    // 만드는 법2
    String manual03;    // 만드는 법3
    String manual04;    // 만드는 법4
    String manual05;    // 만드는 법5
    String manual06;    // 만드는 법6
    String manual07;    // 만드는 법7
    String manual08;    // 만드는 법8
    String manual09;    // 만드는 법9
    String manual10;    // 만드는 법10
    String manual11;    // 만드는 법11
    String manual12;    // 만드는 법12
    String manual13;    // 만드는 법13
    String manual14;    // 만드는 법14
    String manual15;    // 만드는 법15
    String manual16;    // 만드는 법16
    String manual17;    // 만드는 법17
    String manual18;    // 만드는 법18
    String manual19;    // 만드는 법19
    String manual20;    // 만드는 법20

    String user_id;        // 아이디
    String reg_id;         // 최초 등록자아이디
    String reg_dt;         // 최초 등록시간
    String chg_id;         // 마지막 수정자아이디
    String chg_dt;         // 마지막 수정시간

    String user_name;       // 등록자명
}
