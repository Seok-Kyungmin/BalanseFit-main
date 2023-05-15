package com.balansefit.dto;

import lombok.Data;

@Data
public class AdminInfoDTO {

    String adm_id;         // 아이디
    String adm_pw;         // 회원비밀번호
    String adm_name;       // 회원닉네임
    String adm_email;      // 회원 이메일
    String reg_id;         // 최초 등록자아이디
    String reg_dt;         // 최초 등록시간
    String chg_id;         // 마지막 수정자아이디
    String chg_dt;         // 마지막 수정시간

    // 회원가입시, 중복가입을 방지 위해 사용할 변수
    // DB를 조회해서 회원이 존재하면 Y값을 반환함
    // DB테이블에 존재하지 않는 가상의 컬럼(ALIAS)
    String exists_yn3; // 아이디 중복 확인

    String exists_yn4; // 이메일 중복 확인

}
