package com.balansefit.controller;

import com.balansefit.dto.MailDTO;
import com.balansefit.dto.UserInfoDTO;
import com.balansefit.persistance.mapper.IRecipeMapper;
import com.balansefit.service.IFoodService;
import com.balansefit.service.IMailService;
import com.balansefit.service.IRecipeService;
import com.balansefit.service.IUserInfoService;
import com.balansefit.util.CmmUtil;
import com.balansefit.util.EncryptUtil;
import com.balansefit.util.RamdomMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class UserInfoController {

    @Resource(name = "UserInfoService")
    private IUserInfoService UserInfoService;

    @Resource(name = "RecipeService")
    private IRecipeService recipeService;

    @Resource(name = "FoodService")
    private IFoodService FoodService;

    @Resource(name = "MailService")
    IMailService mailService;

    private IRecipeMapper recipeMapper;

    @GetMapping(value = "/top")
    public String top() throws Exception {

        log.info(this.getClass().getName() + ".top ok");

        return "/login/top";
    }

    // 유저 메인 페이지
    @GetMapping(value = "userIndex")
    public String userIndex() {

        return "/user/userIndex";

    }

    // 식당 추천 Map
    @GetMapping(value = "Map")
    public String Map() {

        return "/Map";

    }

    /*
     * 회원가입 화면으로 이동
     */
    @GetMapping(value = "/regUser")
    public String regUser() throws Exception {

        log.info(this.getClass().getName() + ".signUp ok");

        return "/register/user";
    }

    /*
     * 회원가입 로직 처리
     */
    @PostMapping(value = "/regUser1")
    public String insertUser(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + "UserController : 회원가입 시작");

        // 회원가입 결과에 대한 메시지 전달할 변수
        String msg = "";
        String url = "";
        //웹 회원가입결과에 대한 메시지를 전달할변수
        UserInfoDTO tDTO;

        try {
            //오류났던 이유 HttpServletRequest(여기 둘다 같아서 HttpServletRequest, HttpServletResponse 오른쪽에 있던걸로 써서 getParameter 빨간줄 뜸) request, HttpServletResponse response,
            String user_id = CmmUtil.nvl(request.getParameter("user_id"));
            String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));
            String user_name = CmmUtil.nvl(request.getParameter("user_name"));
            String user_email = CmmUtil.nvl(request.getParameter("user_email"));
            String user_age = CmmUtil.nvl(request.getParameter("user_age"));
            String user_gender = CmmUtil.nvl(request.getParameter("user_gender"));
            String user_height = CmmUtil.nvl(request.getParameter("user_height"));
            String user_weight = CmmUtil.nvl(request.getParameter("user_weight"));

            log.info("user_id " + user_id);
            log.info("user_name " + user_name);
            log.info("user_email " + user_email);
            log.info("user_pw " + user_pw);
            log.info("user_age " + user_age);
            log.info("user_gender " + user_gender);
            log.info("user_height " + user_height);
            log.info("user_weight " + user_weight);

            tDTO = new UserInfoDTO();
            tDTO.setUser_id(user_id);
            tDTO.setUser_name(user_name);
            tDTO.setUser_age(user_age);
            tDTO.setUser_gender(user_gender);
            tDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw)); // 비밀번호 해시 알고리즘 암호화
            tDTO.setUser_email(user_email); // 이메일
//            tDTO.setUser_email(EncryptUtil.encAES128CBC(user_email)); // 이메일 AES-128-CBC 암호화
            tDTO.setUser_height(user_height);
            tDTO.setUser_weight(user_weight);

            int res = UserInfoService.insertUser(tDTO);


            if (res == 1) {
                msg = "회원가입이 되었습니다.";
                url = "/loginPage";
//            } else if(res == 3) {
//                msg = "이미 가입된 ID입니다";
//                url = "/regUser";
//            } else if(res == 2) {
//                msg = "이미 가입된 Email입니다";
//                url = "/regUser";
            } else {
                msg = "오류로 인해 회원가입이 실패했습니다.";
                url = "/regUser";
                System.out.println("오류로 회원가입이 실패했습니다");
            }

        } catch (Exception e) {
            // 저장이 실패되면 유저 보여줄 메시지
            msg = "회원가입에 실패하였습니다.";
            log.info(e.toString());
            e.printStackTrace();
        } finally {
            log.info(this.getClass().getName() + "UserController : 회원가입 끝");

            // 회원가입 여부 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);
            tDTO = null;

        }

        return "/redirect";

    }

    // 아이디 중복 체크
    @RequestMapping(value = "/userIdChk", method = RequestMethod.POST)
    @ResponseBody
    public String getUserExists(HttpServletRequest request) throws Exception {

        log.info("getUserExists start!");

        String user_id = CmmUtil.nvl(request.getParameter("user_id"));

        log.info("user_id : " + user_id);

        int res = 0;

        res = UserInfoService.getUserExists(user_id);

        log.info("res : " + res);


        if (res == 0) {

            return "fail";    // 중복 아이디가 존재

        } else {

            return "success";    // 중복 아이디 x

        }

    }

    // 이메일 중복 체크
    @PostMapping(value = "/userEmailChk")
    @ResponseBody
    public String getUserExists2(HttpServletRequest request) throws Exception {

        log.info("getUserExists2 start!");

        String user_email = CmmUtil.nvl(request.getParameter("user_email"));

        log.info("user_email : " + user_email);

        int res = 0;

        res = UserInfoService.getUserExists2(user_email);

        log.info("res : " + res);


        if (res == 0) {

            return "fail";    // 중복 아이디가 존재

        } else {

            return "success";    // 중복 아이디 x

        }

    }

    // 회원 로그인 페이지
    @GetMapping(value = "/loginPage")
    public String userlogin() throws Exception {

        log.info(this.getClass().getName() + "userlogin  시작!");

        //FoodService.collectFood();

        log.info(this.getClass().getName() + "userlogin  끝!");

        return "/login/userLogin";

    }

    // 회원 로그인페이지 로직
    @PostMapping(value = "/loginPage1")
    public String loginPage(HttpServletRequest request, HttpSession session, ModelMap model)
            throws Exception {

        log.info(this.getClass().getName() + "UserController : 로그인페이지 시작");

        String msg = "";
        String url = "";
        String icon = "";
        int res = 0;

        UserInfoDTO tDTO = null;


        try {
            String user_id = CmmUtil.nvl(request.getParameter("user_id"));
            String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));

            log.info("user_id : " + user_id);
            log.info("user_pw : " + user_pw);

            tDTO = new UserInfoDTO();
            tDTO.setUser_id(user_id);
            tDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));

            res = UserInfoService.getUserLoginCheck(tDTO);
            session.setAttribute("SS_USER_ID", user_id);

            if (res == 1) {

                msg = "환영합니다!";
                url = "/userIndex";
                icon = "success";
                log.info("user_id : " + user_id);
                log.info("user_pw : " + user_pw);

            } else {
                log.info("user_id : " + user_id);
                log.info("user_pw : " + user_pw);
                msg = "아이디 비밀번호를 확인해주세요";
                url = "/loginPage";
                icon = "error";
            }
        } catch (Exception e) {
            res = 2;
            log.info(e.toString());
            e.printStackTrace();

        } finally {

            log.info(this.getClass().getName() + "UserController : 로그인페이지 끝!");
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);
            model.addAttribute("icon", icon);

            tDTO = null;

        }

        return "/redirect";
    }

    @GetMapping(value = "/userlogout") // 로그아웃
    public String userLogout(HttpServletRequest request, ModelMap model) {
        log.info(this.getClass().getName() + ".user/userLogout start");
        HttpSession session = request.getSession();

        String url = "/loginPage";
        String msg = "로그아웃 성공";

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        return "/loginPage";
    }

    // 아이디 찾기 보여주는 페이지
    @RequestMapping(value = "user/findId")
    public String findId(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass() + "user/findId start!!");

        log.info(this.getClass() + "user/findId end!!");

        return "/user/findId";
    }

    // 이메일 입력 후 인증 메일 발송하는 컨트롤러
    @RequestMapping(value = "user/sendMailForFindID")
    public String SendMailForFindID(HttpServletRequest request, ModelMap model, HttpSession session)
            throws Exception {

        log.info(this.getClass() + "SendMailForFindID Page start!!");

        session.invalidate(); // 세션 정보 초기화

        String email = CmmUtil.nvl(request.getParameter("user_email"));
        log.info("email : " + email);

        UserInfoDTO tDTO = new UserInfoDTO();
        String msg;
        String url;

        tDTO.setUser_email(email);

        UserInfoDTO rDTO = UserInfoService.getFindUserInfo(tDTO);
        String emailForDB = rDTO.getUser_email();

        log.info("email : " + email);
        log.info("rDTO.getUser_email : " + emailForDB);

        if (emailForDB == null) {
            log.info("null : " + emailForDB);
            msg = "가입된 회원이 없습니다.";
            url = "/loginPage";
        } else {

            String title = "메일로 아이디를 보내드렸습니다";
            MailDTO pDTO = new MailDTO();

            pDTO.setTitle(title);
            pDTO.setToMail(email);

            rDTO = UserInfoService.getFindUserID(tDTO);
            String idForDB = rDTO.getUser_id();

            String contents = idForDB;
            pDTO.setContents(contents);

            session = request.getSession();
            session.setAttribute("random", contents);
            session.setAttribute("sessionEmail", emailForDB);

            log.info("rDTO.getUser_id : " + idForDB);

            mailService.doSendMail(pDTO);

            msg = "메일 발송을 완료 하였습니다.";
            url = "/loginPage";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass() + "SendMailForFindID Page end!!");

        return "/redirect";
    }

    // 비밀번호 찾기 보여주는 페이지
    @RequestMapping(value = "user/sodoi")
    public String sodoi(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass() + "user/userLogin start!!");

        log.info(this.getClass() + "user/userLogin end!!");

        return "/user/sodoi";
    }

    // 이메일 입력 후 인증 메일 발송하는 컨트롤러
    @RequestMapping(value = "user/sendMail")
    public String SendMailForFindUser(HttpServletRequest request, ModelMap model, HttpSession session)
            throws Exception {

        log.info(this.getClass() + "SendMail Page start!!");

        session.invalidate(); // 세션 정보 초기화

        String email = CmmUtil.nvl(request.getParameter("user_email"));
        log.info("email : " + email);
        UserInfoDTO tDTO = new UserInfoDTO();
        String msg;
        String url;

        tDTO.setUser_email(email);

        UserInfoDTO rDTO = new UserInfoDTO();
        rDTO = UserInfoService.getFindUserInfo(tDTO);
        String emailForDB = rDTO.getUser_email();

        log.info("email : " + email);
        log.info("rDTO.getUser_email : " + emailForDB);

        if (emailForDB == null) {
            log.info("null : " + emailForDB);
            msg = "가입된 회원이 없습니다.";
            url = "/loginPage";
        } else {

            String title = "BalanceFit에서 인증번호를 보내드립니다.";
            MailDTO pDTO = new MailDTO();

            pDTO.setTitle(title);
            pDTO.setToMail(email);

            String contents = RamdomMail.SendRamdomMail();
            pDTO.setContents(contents);

            session = request.getSession();
            session.setAttribute("random", contents);
            session.setAttribute("sessionEmail", emailForDB);

            log.info("random str : " + contents);

            mailService.doSendMail(pDTO);

            msg = "메일 발송을 완료 하였습니다.";
            url = "/user/changePwd";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass() + "SendMail Page end!!");

        return "/redirect";
    }

    // 인증메일 비교를 위한 페이지 띄우는 로직
    @RequestMapping(value = "user/changePwd")
    public String changePwdPage() throws Exception {
        log.info(this.getClass().getName() + "changePwdPage Start!!");
        log.info(this.getClass().getName() + "changePwdPage End!!");
        return "/user/changePwd";
    }

    // 인증번호 성공 후 비번 변경 페이지 띄우는 컨트롤러
    @RequestMapping(value = "user/changePwdProcPage")
    public String changePwdProcPage() throws Exception {
        log.info(this.getClass().getName() + "changePwdProcPage Start!!");
        log.info(this.getClass().getName() + "changePwdProcPage End!!");
        return "/user/changePwdProc";
    }

    // 비밀번호 변경처리 로직
    @RequestMapping(value = "user/changePwdProc")
    public String changePwdProc(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
        log.info(this.getClass() + "changePwdProd Page end!!");

//        String email = CmmUtil.nvl(session.getAttribute("sessionEmail").toString());
//
//        session.invalidate(); // 세션 정보 초기화
        String user_id = CmmUtil.nvl(request.getParameter("user_id"));
        String user_pw = CmmUtil.nvl(EncryptUtil.encHashSHA256(request.getParameter("user_pw")));

        log.info("user_id : " + user_id);
        log.info("user_pw : " + user_pw);

        UserInfoDTO pDTO = new UserInfoDTO();

        pDTO.setUser_id(user_id);
        pDTO.setUser_pw(user_pw);

        int success = UserInfoService.updateUserPwdInfo(pDTO);

        String msg = "";
        String url = "";

        if (success == 1) {
            msg = "정보 변경이 완료되었습니다. 로그인 해주세요.";
            url = "/loginPage";
        } else {
            msg = "오류로 인해 비밀번호 변경을 실패하였습니다";
            url = "/user/changePwdProcPage";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass() + "changePwdProd Page end!!");

        return "/redirect";
    }

    @RequestMapping(value = "/mypage")
    public String mypage(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {

        log.info(this.getClass().getName()+ "mypage 시작!!");

        String msg = "";

        try {

            // 아이디 세션 얻기
            String userId = (String) session.getAttribute("SS_USER_ID");

            // 사용자 정보 조회
            UserInfoDTO rDTO = UserInfoService.getUserInfo(userId);

            log.info("getUserInfo success!!");
            log.info(this.getClass().getName() + " .user_name :" + rDTO.getUser_name());
            log.info(this.getClass().getName() + " .user_email : " + rDTO.getUser_email());
            log.info(this.getClass().getName() + " .user_age : " + rDTO.getUser_age());
            log.info(this.getClass().getName() + " .user_gender : " + rDTO.getUser_gender());
            log.info(this.getClass().getName() + " .user_height : " + rDTO.getUser_height());
            log.info(this.getClass().getName() + " .user_weight : " + rDTO.getUser_weight());

            // 조회된 리스트 결과값 넣어주기
            model.addAttribute("rDTO", rDTO);
            log.info("rDTO 값은?"+ rDTO.getUser_id());

        } catch (Exception e) {

            msg= "실패하였습니다 : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + " / mypage 끝!!");

            // 결과 메시지 전달
            model.addAttribute("msg", msg);

        }

        return "/mypage/userInfo";
    }

}

