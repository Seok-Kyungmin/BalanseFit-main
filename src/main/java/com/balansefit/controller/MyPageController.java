package com.balansefit.controller;

import com.balansefit.dto.UserInfoDTO;
import com.balansefit.service.IMyPageService;
import com.balansefit.service.IUserInfoService;
import com.balansefit.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class MyPageController {

    @Resource(name = "UserInfoService")
    private IUserInfoService userInfoService;

    @Resource(name = "MyPageService")
    private IMyPageService myPageService;

    @GetMapping(value = "CaloricCalculator")
    public String CaloricCalculator() {
        log.info(this.getClass().getName()+ "CaloricCalculator 시작!!");
        return "/mypage/CaloricCalculator";
    }

    // 회원 정보 수정 페이지
    @RequestMapping(value = "/userEditInfo")
    public String userEditInfo(HttpServletRequest request, ModelMap model) throws Exception{

        log.info(this.getClass().getName()+ "userEditInfo 시작!!");

        String user_id = CmmUtil.nvl(request.getParameter("user_id"));

        log.info(this.getClass().getName()+".ExerciseEdit user_id! "+ user_id);

        log.info(this.getClass().getName()+ "userEditInfo 끝!!");

        model.addAttribute("user_id", user_id);

        return "/mypage/userEditInfo";
    }

    // 회원 정보 수정
    @RequestMapping(value = "/updateUserInfo")
    public String updateUserInfo(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception{

        log.info(this.getClass().getName()+ "updateUserInfo 시작!!");

        String msg = "";
        String url = "";

        try{
            String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
            String user_name = CmmUtil.nvl(request.getParameter("user_name"));
            String user_email = CmmUtil.nvl(request.getParameter("user_email"));
            String user_age = CmmUtil.nvl(request.getParameter("user_age"));
            String user_gender = CmmUtil.nvl(request.getParameter("user_gender"));
            String user_height = CmmUtil.nvl(request.getParameter("user_height"));
            String user_weight = CmmUtil.nvl(request.getParameter("user_weight"));

            log.info("user_id : " + user_id);
            log.info("user_name : " + user_name);
            log.info("user_email : " + user_email);
            log.info("user_age : " + user_age);
            log.info("user_gender : " + user_gender);
            log.info("user_height : " + user_height);
            log.info("user_weight : " + user_weight);

            UserInfoDTO uDTO = new UserInfoDTO();

            uDTO.setUser_id(user_id);
            uDTO.setUser_name(user_name);
            uDTO.setUser_email(user_email);
            uDTO.setUser_age(user_age);
            uDTO.setUser_gender(user_gender);
            uDTO.setUser_height(user_height);
            uDTO.setUser_weight(user_weight);

            myPageService.updateUserInfo(uDTO);

            msg = "수정되었습니다";
            url = "/mypage";

        } catch (Exception e) {
            msg = "실패하였습니다 : " + e.getMessage();
            url = "/updateUserInfo";
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName()+"finally .updateUserInfo end!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

        }
        log.info((String) model.getAttribute("url"));
        return "/redirect";
    }

//    @RequestMapping(value = "/userDelete", method = RequestMethod.GET)
//    public String userDelete() throws Exception{
//
//        log.info(this.getClass().getName()+".userDelete start!");
//
//        return "/mypage/userDelete";
//    }

    // 회원 탈퇴
    @RequestMapping(value="/userDelete")
    public String userDelete(HttpSession session, HttpServletRequest request, ModelMap model)throws Exception{

        log.info(this.getClass().getName()+".userDelete start!");

        String msg = "";
        String url = "";

        try{

            String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));

            log.info("user_id : " + user_id);

            UserInfoDTO uDTO = new UserInfoDTO();

            uDTO.setUser_id(user_id);

            // 회원 탈퇴하기
            myPageService.userDelete(uDTO);

            msg = "삭제되었습니다";
            url = "/loginPage";

        } catch (Exception e) {
            msg = "실패하였습니다 : " + e.getMessage();
            url = "/mypage";
            log.info(e.toString());
            e.printStackTrace();
        } finally {
            log.info(this.getClass().getName()+".userDelete end! finally");

            //결과 메시지 전달하기
            model.addAttribute("msg",msg);
            model.addAttribute("url", url);

        }
        log.info((String) model.getAttribute("url"));
        return "/redirect";
    }

    @GetMapping(value = "/WeightGraph")
    public String getWeightList(HttpServletRequest request, ModelMap model) throws Exception{

        log.info(this.getClass().getName()+".WeightGraph start!");

        // 웨이트디티오에 세션아이디를 넣고, 그 디티오를 파라미터로 넣는다
        String msg = "";

        try{

            /*관리자 페이지 연결 요함*/
            String session_id = CmmUtil.nvl(request.getParameter("SS_USER_ID"));

            session_id ="qwe";


            log.info("uId : " + session_id);

//            UserWeightDTO pDTO = new UserWeightDTO();
//            pDTO.setUser_id(session_id);
//
//            List<UserWeightDTO> rlist = myPageService.getWeightList(pDTO);
//
//            if (rlist == null) {
//                rlist = new ArrayList<>();
//            }
//
//
//            //조회된 리스트 결과값 보여주기
//            model.addAttribute("rlist", rlist);
//
//            for(UserWeightDTO w : rlist){
//                log.info("w.getCurrent_w(): "+w.getCurrent_w());
//            }


            log.info("getWeightList success!!");
        }catch (Exception e){
            e.printStackTrace();
        }

        log.info(this.getClass().getName()+".WeightGraph end!");

        return "/mypage/WeightGraph";
    }

    @GetMapping(value = "/CalorieGraph")
    public String getCalorieGraph(HttpServletRequest request, ModelMap model) throws Exception{

        log.info(this.getClass().getName()+".CalorieGraph start!");

        // 웨이트디티오에 세션아이디를 넣고, 그 디티오를 파라미터로 넣는다
        String msg = "";

        try{

            /*r관리자 페이지 연결 요함*/
            String session_id = CmmUtil.nvl(request.getParameter("SS_USER_ID"));

            session_id ="qwe";


            log.info("uId : " + session_id);


            log.info("getCalorieGraph success!!");
        }catch (Exception e){
            e.printStackTrace();
        }

        log.info(this.getClass().getName()+".CalorieGraph end!");

        return "/mypage/CalorieGraph";
    }

    // 이미지 파일 업로드
    @PostMapping(value = "/ProfileImageInsert")
    public String ProfileImageInsert(MultipartFile file) throws Exception{

        myPageService.write(file);

        return "/redirect";
    }

    // 이미지 파일 업로드
    @PostMapping(value = "/ProfileImageUpdate")
    public String ProfileImageUpdate(MultipartFile file) throws Exception{

        log.info(this.getClass().getName()+".ProfileImageUpdate start!");

        myPageService.write(file);

        log.info(this.getClass().getName()+".ProfileImageUpdate end!");

        return "/redirect";
    }

}
