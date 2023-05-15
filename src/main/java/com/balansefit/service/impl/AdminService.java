package com.balansefit.service.impl;

import com.balansefit.dto.AdminInfoDTO;
import com.balansefit.persistance.mapper.IAdminMapper;
import com.balansefit.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("AdminService")
public class AdminService implements IAdminService {

    public final IAdminMapper iAdminMapper;

    @Autowired
    public AdminService(IAdminMapper iAdminMapper){
        this.iAdminMapper = iAdminMapper;
    }

    //로그인
    @Override
    public int getAdminLoginCheck(AdminInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + "AdminService : getAdminLoginCheck 시작");
        //성공: 1  실패: 0
        int res = 0;

        AdminInfoDTO rDTO = iAdminMapper.getAdminLoginCheck(pDTO);

        if(rDTO == null) {
            log.info(this.getClass().getName() + "로그인 실패");
            res = 0;
        }else {
            log.info(this.getClass().getName() + "로그인 성공");
            res = 1;
        }

        log.info(this.getClass().getName() + "AdminService : getAdminLoginCheck 끝");

        return res;
    }

    //회원가입
    @Override
    public int insertAdmin(AdminInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + "AdminService : insertAdmin 시작");


        //회원가입 성공 :1 ,아이디 중복 : 2, 에러 : 0
        int res = 0;

//        if(pDTO == null) {
//            pDTO = new AdminInfoDTO();
//
//        }
//        AdminInfoDTO rDTO = iAdminMapper.getAdminExists(pDTO);
//        log.info("중복체크");
//
//        if(rDTO == null) {
//            rDTO = new AdminInfoDTO();
//
//        }
//
//        if(CmmUtil.nvl(rDTO.getExists_yn3()).equals("Y")){
//            //아이디 중복이므로 회원가입 x
//            res = 2;
//
//        }else {
            int success = iAdminMapper.insertAdmin(pDTO);

            if(success > 0){
                res = 1;

            } else {
                res = 0;
            }
//        }
            return res;
    }

    @Override
    public int updateAdminPwdInfo(AdminInfoDTO pDTO) throws Exception {
        int success = iAdminMapper.updateAdminPwdInfo(pDTO);

        int res = 0;
        if (success == 1) {
            res = 1;
        }

        return res;
    }

    @Override
    public AdminInfoDTO getFindAdminInfo(AdminInfoDTO tDTO) throws Exception {

        log.info("유저 이메일 찾기 프로세스 실행");

        AdminInfoDTO rDTO = iAdminMapper.getFindAdminInfo(tDTO);
        // 널처리 안하면 어떻게 되는 지 보여줌
        // 메모리 올리기 처리 예시
        if (rDTO == null) {
            rDTO = new AdminInfoDTO();
        }

        log.info("유저 이메일 찾기 프로세스 종료");
        return rDTO;
    }

    @Override
    public AdminInfoDTO getFindAdminID(AdminInfoDTO tDTO) throws Exception {

        log.info("유저 이메일 찾기 프로세스 실행");

        AdminInfoDTO rDTO = iAdminMapper.getFindAdminID(tDTO);
        // 널처리 안하면 어떻게 되는 지 보여줌
        // 메모리 올리기 처리 예시
        if (rDTO == null) {
            rDTO = new AdminInfoDTO();
        }

        log.info("유저 이메일 찾기 프로세스 종료");
        return rDTO;
    }

    @Override
    public int getAdminExists(String adm_id) throws Exception {

        log.info(this.getClass().getName() + "getAdminExists start");
        String num = iAdminMapper.getAdminExists(adm_id);
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
    public int getAdminExists2(String adm_email) throws Exception {

        log.info(this.getClass().getName() + "getAdminExists2 start");
        String num = iAdminMapper.getAdminExists2(adm_email);
        log.info("num : "+ num);

        int res= 0;

        if(num.contains("Y")) {
            res = 1;
        } else {
            res = 0;
        }

        return res;
    }

}

