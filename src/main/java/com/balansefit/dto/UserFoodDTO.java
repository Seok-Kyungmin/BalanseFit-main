package com.balansefit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class UserFoodDTO {

    String food_seq;    //음식 번호
    String food_name;   //음식명
    String food_calories;  //음식 칼로리
    String food_carbohydrate;  // 음식 탄수화물
    String food_protein;   //단백질
    String food_fat;   // 지방
    String food_sugar;     //당
    String food_natrium;   //나트륨
    String food_weight;    //1회제공량
    String user_id;        // 아이디
    String reg_id;         // 최초 등록자아이디
    String reg_dt;         // 최초 등록시간
    String chg_id;         // 마지막 수정자아이디
    String chg_dt;         // 마지막 수정시간

    String user_name;       // 등록자명

    // 검색필터터
   String type;            // 검색 타입
    String keyword;         // 검색 내용

}
