package com.balansefit.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoDTO {

    String user_seq;        // 회원번호
    String user_auth;       // 권한
    String user_id;         // 아이디
    String user_pw;         // 회원비밀번호
    String user_name;       // 회원닉네임
    String user_email;      // 회원 이메일
    String user_age;       // 회원 연령
    String user_gender;     // 회원 성별
    String user_height;    // 회원 키
    String user_weight;    // 회원 몸무게
//    String user_RC;        // 1일 적정량(처방칼로리)
//    String user_BM;        // 기초대사량
//    String user_BMI;       // 회원 체질량지수(BMI)
//    String user_RE;        // 회원 권장운동량
//    String target_weight;  // 회원 목표몸무게
//    String target_period;  // 회원 목표기간
//    String crt_itk;        // 현재 섭취량
//    String crt_exc;        // 현재 운동량
    String reg_id;         // 최초 등록자아이디
    String reg_dt;         // 최초 등록시간
    String chg_id;         // 마지막 수정자아이디
    String chg_dt;         // 마지막 수정시간
//    Timestamp reg_date;

    // N 관계의 테이블을 List 형태로 선언
    List<NoticeDTO> userNoticeList;

    // 회원가입시, 중복가입을 방지 위해 사용할 변수
    // DB를 조회해서 회원이 존재하면 Y값을 반환함
    // DB 테이블에 존재하지 않는 가상의 컬럼(ALIAS)
    String exists_yn;

    // 회원가입시, 중복가입을 방지 위해 사용할 변수
    // DB를 조회해서 회원이 존재하면 Y값을 반환함
    // DB 테이블에 존재하지 않는 가상의 컬럼(ALIAS)
    String exists_yn2;

    private String file_name;

    private String file_path;

}
