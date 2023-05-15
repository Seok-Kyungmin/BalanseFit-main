package com.balansefit.dto;

import lombok.Data;

@Data
public class ExerciseDTO {

    String exercise_seq;    //운동 번호
    String exercise_name;   //운동명
    String exercise_type;   // 운동종목
    String exercise_met;    //소모 칼로리
    String user_id;         // 아이디
    String reg_id;         // 최초 등록자아이디
    String reg_dt;         // 최초 등록시간
    String chg_id;         // 마지막 수정자아이디
    String chg_dt;         // 마지막 수정시간

    String user_name;       // 등록자명
}
