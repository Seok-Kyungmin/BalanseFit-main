package com.balansefit.dto;

import lombok.Data;

@Data
public class UserWeightDTO {

    String weight_dt; // 몸무게별 날짜
    Integer current_w; // 회원 현재 몸무게
    String user_id;         //회원 아이디

}
