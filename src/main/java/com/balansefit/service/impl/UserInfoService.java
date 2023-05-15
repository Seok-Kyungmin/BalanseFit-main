package com.balansefit.service.impl;

import com.balansefit.dto.UserInfoDTO;
import com.balansefit.persistance.mapper.IUserInfoMapper;
import com.balansefit.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("UserInfoService")
public class UserInfoService implements IUserInfoService {

    @Resource(name = "IUserInfoMapper")
    private IUserInfoMapper UserInfoMapper;

//    @Override
//    public UserInfoDTO getUserInfo(UserInfoDTO uDTO) throws Exception {
//
//        return userInfoMapper.getUserInfo(uDTO);
//    }


    //회원가입
    @Override
    public int insertUser(UserInfoDTO tDTO) throws Exception {

        log.info(this.getClass().getName() + "UserService : insertUser 시작!");

        // 회원가입 성공 : 1, 에러 : 0
        int res = 0;
//        String msg;
//        String url;
//
//        if (tDTO == null) {
//            tDTO = new UserInfoDTO();
//        }
//
//        // 회원가입 중복 장지를 위해 DB에서 데이터 조회
//        log.info("아이디 중복체크 start");
//        UserInfoDTO rDTO = UserInfoMapper.getUserExists(tDTO);
//        log.info("아이디 중복체크 end");
//
//        log.info("이메일 중복체크 start");
//        UserInfoDTO sDTO = UserInfoMapper.getUserExists2(tDTO);
//        log.info("이메일 중복체크 end");
//
//        // mapper에서 값이 정상적으로 못 넘어오는 경우를 대비
//        if (rDTO == null) {
//            rDTO = new UserInfoDTO();
//        }
//        if (sDTO == null) {
//            sDTO = new UserInfoDTO();
//        }
//
//        log.info("rDTO.getExists_yn()).equals(\"Y\") :  " + rDTO.getExists_yn());
//        if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
//            // 아이디 중복이므로 회원가입 x
//            log.info("아이디가 중복됩니다");
//            msg = "아이디가 중복됩니다";
//            url = "/regUser";
//            res = 3;
//        }
//        log.info("sDTO.getExists_yn2()).equals(\"Y\") :  " + sDTO.getExists_yn2());
//        if (CmmUtil.nvl(rDTO.getExists_yn2()).equals("Y")) {
//            // 이메일 중복이므로 회원가입 x
//            log.info("이메일이 중복됩니다");
//            msg = "이메일이 중복됩니다";
//            url = "/regUser";
//            res = 2;
//
//        }else {
        log.info("insertUser start");
        int success = UserInfoMapper.insertUser(tDTO);
        log.info("insertUser end");

        if (success > 0) {
            res = 1;

        } else {
            res = 0;
        }
//        }
        return res;
    }

    @Override
    public int getUserExists(String user_id) throws Exception {

        log.info(this.getClass().getName() + "getUserExists start");
        String num = UserInfoMapper.getUserExists(user_id);
        log.info("num : "+ num);

        int res= 0;

        if(num.contains("Y")) {
            res = 1;
        } else {
            res = 0;
        }

        return res;
    }

    @Override
    public int getUserExists2(String user_email) throws Exception {

        log.info(this.getClass().getName() + "getUserExists2 start");
        String num = UserInfoMapper.getUserExists2(user_email);
        log.info("num : "+ num);

        int res= 0;

        if(num.contains("Y")) {
            res = 1;
        } else {
            res = 0;
        }

        return res;
    }

    //로그인 체크
    @Override
    public int getUserLoginCheck(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + "UserService : getUserLoginCheck 시작");

        // 성공: 1  실패: 0
        int res = 0;

        UserInfoDTO rDTO = UserInfoMapper.getUserLoginCheck(pDTO);

        if (rDTO == null) {
            log.info(this.getClass().getName() + "로그인 실패");
            res = 0;
        } else {
            log.info(this.getClass().getName() + "로그인 성공");
            res = 1;
        }

        log.info(this.getClass().getName() + "UserService : getUserLoginCheck 끝");

        return res;
    }

    // 유저 비번 찾기 위한 메서드
    @Override
    public UserInfoDTO getFindUserInfo(UserInfoDTO tDTO) throws Exception {

        log.info("유저 이메일 찾기 프로세스 실행");

        UserInfoDTO rDTO = UserInfoMapper.getFindUserInfo(tDTO);
        // 널처리 안하면 어떻게 되는 지 보여줌
        // 메모리 올리기 처리 예시
        if (rDTO == null) {
            rDTO = new UserInfoDTO();
        }

        log.info("유저 이메일 찾기 프로세스 종료");
        return rDTO;
    }

    // 유저 아이디 찾기 위한 메서드
    @Override
    public UserInfoDTO getFindUserID(UserInfoDTO tDTO) throws Exception {

        log.info("유저 이메일 찾기 프로세스 실행");

        UserInfoDTO rDTO = UserInfoMapper.getFindUserID(tDTO);
        // 널처리 안하면 어떻게 되는 지 보여줌
        // 메모리 올리기 처리 예시
        if (rDTO == null) {
            rDTO = new UserInfoDTO();
        }

        log.info("유저 이메일 찾기 프로세스 종료");
        return rDTO;
    }

//    @Override
//    public UserInfoDTO getUserExists2(UserInfoDTO tDTO) throws Exception {
//
//        int res = UserInfoMapper.getUserExists2(tDTO);
//        return res;
//    }

    // 유저 비밀번호 변경 메서드
    @Override
    public int updateUserPwdInfo(UserInfoDTO pDTO) throws Exception {
        int success = UserInfoMapper.updateUserPwdInfo(pDTO);

        int res = 0;
        if (success == 1) {
            res = 1;
        }

        return res;
    }

    // 마이 페이지 사용자게시판
    @Override
    public UserInfoDTO getUserInfo(String uDTO) throws Exception {

        log.info(this.getClass().getName() + "getUserInfo 시작!");

        return UserInfoMapper.getUserInfo(uDTO);

    }

}