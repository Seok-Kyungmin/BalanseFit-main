package com.balansefit.dto;

import lombok.Data;

@Data
public class UserDietDTO {

    String diet_seq;        // 식단 번호
    String diet_name;       // 식단 이름
    String food_m_1;        // 아침1
    String food_mc_1;          // 아침 식품1 수량
    String food_m_2;
    String food_mc_2;
    String food_m_3;
    String food_mc_3;
    String food_a_1;        // 점심1
    String food_ac_1;          // 점심1 수량
    String food_a_2;
    String food_ac_2;
    String food_a_3;
    String food_ac_3;
    String food_d_1;        // 저녁1
    String food_dc_1;          // 저녁1 수량
    String food_d_2;
    String food_dc_2;
    String food_d_3;
    String food_dc_3;
    String diet_calories;   // 총칼로리
    String user_id;        // 아이디
    String reg_id;         // 최초 등록자아이디
    String reg_dt;         // 최초 등록시간
    String chg_id;         // 마지막 수정자아이디
    String chg_dt;         // 마지막 수정시간

    String user_name;       // 등록자명
}
