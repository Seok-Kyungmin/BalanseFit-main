package com.balansefit.controller;

import com.balansefit.dto.UserInfoDTO;
import com.balansefit.service.IAdminUserService;
import com.balansefit.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class AdminUserController {

    @Resource(name = "AdminUserService")
    private IAdminUserService adminUserService;

    /**
     * 게시판 리스트 보여주기
     */
    @GetMapping(value = "admin/AdminUserList")
    public String AdminUserList(ModelMap model)
            throws Exception{

        log.info(this.getClass().getName()+".AdminUserList start!");

        // 운동 리스트 가져오기
        List<UserInfoDTO> uList = adminUserService.getUserList();

        if (uList == null) {
            uList = new ArrayList<>();
        }

        // 조회된 결과값 가져오기
        model.addAttribute("uList", uList);

        log.info(this.getClass().getName()+".AdminUserList end!");

        return "/adminUser/AdminUserList";
    }

    /**
     * 운동정보 작성 페이지 이동
     */
//    @GetMapping(value = "admin/AdminUserReg")
//    public String AdminUserReg() {
//
//        log.info(this.getClass().getName()+".AdminUserReg start!");
//
//        log.info(this.getClass().getName()+".AdminUserReg end!");
//
//        return "/adminUser/AdminUserReg";
//    }

    /**
     * 게시판 상세보기
     */
    @GetMapping(value = "admin/AdminUserInfo")
    public String AdminUserInfo(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + ".AdminUserInfo start!");

        String msg = "";

        try {
            String uSeq = request.getParameter("uSeq");
            log.info("uSeq : " + uSeq);

            UserInfoDTO pDTO = new UserInfoDTO();
            pDTO.setUser_seq(uSeq);

            // 상세정보 가져오기
            UserInfoDTO rDTO = adminUserService.getUserInfo(pDTO);

            if (rDTO == null) {
                rDTO = new UserInfoDTO();
            }

            String session_id = CmmUtil.nvl(request.getParameter("SS_USER_ID"));

            log.info("getExerciseInfo success!!");
            log.info(this.getClass().getName() + " rDTO.getUser_id() "+rDTO.getUser_id());
            log.info(this.getClass().getName() + " session_id : "+ session_id);
            log.info(this.getClass().getName() + " user_name : "+ rDTO.getUser_name());
            log.info(this.getClass().getName() + " .user_email : " + rDTO.getUser_email());
            log.info(this.getClass().getName() + " .user_age : " + rDTO.getUser_age());
            log.info(this.getClass().getName() + " .user_gender : " + rDTO.getUser_gender());
            log.info(this.getClass().getName() + " .user_height : " + rDTO.getUser_height());
            log.info(this.getClass().getName() + " .user_weight : " + rDTO.getUser_weight());

            // 조회된 리스트 결과값 넣어주기
            model.addAttribute("rDTO", rDTO);
            log.info("rDTO 값은?"+rDTO.getUser_id());

        } catch (Exception e) {

            msg= "실패하였습니다 : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();
            
        }finally {
            log.info(this.getClass().getName() + "finally.AdminUserInfo end!");

            // 결과 메시지 전달
            model.addAttribute("msg", msg);

        }

        log.info((String) model.getAttribute("url"));
        return "/adminUser/AdminUserInfo";
    }

    /**
     * 게시판 글 삭제
     */
    @GetMapping(value = "admin/AdminUserDelete")
    public String AdminUserDelete(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName()+".AdminUserDelete start!");

        String msg = "";
        String url = "";

        try {

            String userId = CmmUtil.nvl(request.getParameter("user_id"));

            log.info("userId : " + userId);

            UserInfoDTO pDTO = new UserInfoDTO();

            pDTO.setUser_id(userId);

            //게시글 삭제하기 DB
            adminUserService.deleteUserInfo(pDTO);

            msg = "삭제되었습니다";
            url = "/admin/AdminUserList";

        } catch (Exception e) {
            msg = "실패하였습니다 : " + e.getMessage();
            url = "/admin/AdminUserList";
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName()+".AdminUserDelete end! finally");

            //결과 메시지 전달하기
            model.addAttribute("msg",msg);
            model.addAttribute("url", url);

        }
        log.info((String) model.getAttribute("url"));
        return "/redirect";
    }

}
