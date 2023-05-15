package com.balansefit.persistance.mapper;

import com.balansefit.dto.KakaoDTO;
import com.balansefit.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserInfoMapper {

    // 회원정보 불러오기
    //UserInfoDTO getUserInfo(UserInfoDTO uDTO) throws Exception;

    // 로그인 체크
    UserInfoDTO getUserLoginCheck(UserInfoDTO tDTO) throws Exception;

    // 회원가입 아이디 중복확인
    String getUserExists(String user_id) throws Exception;

    // 회원가입 이메일 중복확인
    String getUserExists2(String user_email) throws Exception;

    //유저 회원가입
    int insertUser(UserInfoDTO tDTO) throws Exception;

    // 회원 비밀번호 변경 메서드
    int updateUserPwdInfo(UserInfoDTO pDTO) throws Exception;

    // 회원 비번 찾기 위한 메서드
    UserInfoDTO getFindUserInfo(UserInfoDTO tDTO);

    // 회원 아이디 찾기 위한 메서드
    UserInfoDTO getFindUserID(UserInfoDTO tDTO) throws Exception;

    // 마이 페이지 사용자 정보
    UserInfoDTO getUserInfo(String uDTO) throws Exception;

    // 카카오 로그인을 통한 로그인 후 유저정보 가져오기
    KakaoDTO kakaoLoginForDgService(KakaoDTO pDTO);

    // 비밀번호 찾기
//    String findPw(HttpServletResponse resp, UserInfoDTO tDTO) throws Exception;


}