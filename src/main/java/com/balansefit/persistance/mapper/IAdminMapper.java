package com.balansefit.persistance.mapper;

import com.balansefit.dto.AdminInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminMapper {

    //관리자 로그인
    AdminInfoDTO getAdminLoginCheck(AdminInfoDTO tDTO) throws Exception;

    // 회원가입 아이디 중복확인
    String getAdminExists(String adm_id) throws Exception;

    // 회원가입 이메일 중복확인
    String getAdminExists2(String adm_email) throws Exception;

    //유저 회원가입
    int insertAdmin(AdminInfoDTO tDTO) throws Exception;

    // 유저 비밀번호 변경 메서드
    int updateAdminPwdInfo(AdminInfoDTO pDTO) throws Exception;

    // 유저 아이디 변경 메서드
    AdminInfoDTO getFindAdminInfo(AdminInfoDTO tDTO) throws Exception;

    // 유저 이메일 찾기 위한 메드
    AdminInfoDTO getFindAdminID(AdminInfoDTO tDTO) throws Exception;
}
