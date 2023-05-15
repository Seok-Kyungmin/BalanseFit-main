package com.balansefit.controller;

import com.balansefit.dto.AdminInfoDTO;
import com.balansefit.dto.MailDTO;
import com.balansefit.service.IAdminService;
import com.balansefit.service.IMailService;
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
public class AdminController {

    @Resource(name = "AdminService")
    private IAdminService AdminService;

    @Resource(name = "MailService")
    IMailService mailService;

    // 관리자 메인 페이지
    @GetMapping(value = "adminIndex")
    public String adminIndex() {

        return "/admin/adminIndex";

    }

    //관리자 로그인 페이지
    @GetMapping(value = "/adminLoginPage")
    public String adminLogin() throws Exception{

        return "/login/adminLogin";

    }

    //관리자 로그인페이지 로직
    @PostMapping(value = "/adminLoginPage1")
    public  String adminLoginPage(HttpServletRequest request, HttpSession session, ModelMap model)
            throws Exception {

        log.info(this.getClass().getName() + "AdminController : 관리자 로그인페이지 시작");

        String msg = "";
        String url = "";
        String icon = "";
        int res = 0;

        AdminInfoDTO tDTO = null;


        try {
            String adm_id = CmmUtil.nvl(request.getParameter("adm_id"));
            String adm_pw = CmmUtil.nvl(request.getParameter("adm_pw"));

            log.info("adm_id : " + adm_id);
            log.info("adm_pw : " + adm_pw);

            tDTO = new AdminInfoDTO();
            tDTO.setAdm_id(adm_id);
            tDTO.setAdm_pw(EncryptUtil.encHashSHA256(adm_pw));

            res = AdminService.getAdminLoginCheck(tDTO);
            session.setAttribute("SS_ADM_ID", adm_id);

            if (res == 1) {

                msg = "로그인 성공";
                url = "/adminIndex";
                icon = "success";
                log.info("adm_id : " + adm_id);

            }else {
                msg = "로그인 실패!";
                url = "/adminLoginPage";
                icon = "error";
            }
        } catch (Exception e) {
            res = 2;
            log.info(e.toString());
            e.printStackTrace();

        } finally {

            log.info(this.getClass().getName() + "AdminController : 관리자 로그인페이지 끝!");
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);
            model.addAttribute("icon", icon);

            tDTO = null;

        }
        return  "/redirect";
    }

    @GetMapping(value = "/adminLogout") // 로그아웃
    public String adminLogout(HttpServletRequest request, ModelMap model) {
        log.info(this.getClass().getName() + ".admin/adminLogout start");
        HttpSession session = request.getSession();

        String url = "/adminIndex";
        String msg = "로그아웃 성공";

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        return "/admin/adminIndex";
    }

    /*
     * 관리자 회원가입 화면으로 이동
     */
    @GetMapping(value="/regAdmin")
    public  String regAdmin() throws Exception{

        log.info(this.getClass().getName()+ ".signUp ok");

        return "/register/admin";
    }

    /*
     * 관리자 회원가입 로직 처리
     */
    @PostMapping(value = "/regAdmin1")
    public String insertAdmin(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + "AdminController : 관리자 회원가입 시작");


        // 회원가입 결과에 대한 메시지 전달할 변수
        String msg = "";
        String url = "";
        //웹 회원가입결과에 대한 메시지를 전달할변수
        AdminInfoDTO tDTO = null;

        try {
            //오류났던 이유 HttpServletRequest(여기 둘다 같아서 HttpServletRequest, HttpServletResponse 오른쪽에 있던걸로 써서 getParameter 빨간줄 뜸) request, HttpServletResponse response,
            String adm_id = CmmUtil.nvl(request.getParameter("adm_id"));
            String adm_pw = CmmUtil.nvl(request.getParameter("adm_pw"));
            String adm_name = CmmUtil.nvl(request.getParameter("adm_name"));
            String adm_email = CmmUtil.nvl(request.getParameter("adm_email"));

            log.info("adm_id "+ adm_id);
            log.info("adm_name "+ adm_name);
            log.info("adm_email "+ adm_email);
            log.info("adm_pw "+ adm_pw);

            tDTO = new AdminInfoDTO();

            tDTO.setAdm_id(adm_id);
            tDTO.setAdm_name(adm_name);
            tDTO.setAdm_email(adm_email);
            tDTO.setAdm_pw(EncryptUtil.encHashSHA256(adm_pw)); // 비밀번호 해시 알고리즘 암호화
            //tDTO.setAdm_email(EncryptUtil.encAES128CBC(adm_email)); // 이메일 AES-128-CBC 암호화

            int res = AdminService.insertAdmin(tDTO);


            if (res == 1) {
                msg = "회원가입이 되었습니다.";
                url = "/adminLoginPage";
//            } else if(res == 2) {
//                msg = "이미 가입된 ID입니다";
//                url = "/regAdmin";
            }else {
                msg = "오류로 인해 회원가입이 실패했습니다.";
                url = "/regAdmin";
                System.out.println("오류로 회원가입이 실패했습니다");
            }

        } catch (Exception e) {
            // 저장이 실패되면 유저 보여줄 메시지
            msg = "회원가입에 실패하였습니다.";
            log.info(e.toString());
            e.printStackTrace();
        } finally {
            log.info(this.getClass().getName() + "AdminController : 회원가입 끝");

            // 회원가입 여부 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);
            tDTO = null;

        }

        return "/redirect";

    }

    // 아이디 중복 체크
    @RequestMapping(value = "/adminIdChk", method = RequestMethod.POST)
    @ResponseBody
    public String getAdminExists(HttpServletRequest request) throws Exception {

        log.info("getUserExists start!");

        String adm_id = CmmUtil.nvl(request.getParameter("adm_id"));

        log.info("adm_id : " + adm_id);

        int res = 0;

        res = AdminService.getAdminExists(adm_id);

        log.info("res : " + res);


        if (res == 0) {

            return "fail";    // 중복 아이디가 존재

        } else {

            return "success";    // 중복 아이디 x

        }

    }

    // 이메일 중복 체크
    @PostMapping(value = "/adminEmailChk")
    @ResponseBody
    public String getAdminExists2(HttpServletRequest request) throws Exception {

        log.info("getAdminExists2 start!");

        String adm_email = CmmUtil.nvl(request.getParameter("adm_email"));

        log.info("adm_email : " + adm_email);

        int res = 0;

        res = AdminService.getAdminExists2(adm_email);

        log.info("res : " + res);


        if (res == 0) {

            return "fail";    // 중복 아이디가 존재

        } else {

            return "success";    // 중복 아이디 x

        }

    }

    // 아이디 찾기 보여주는 페이지
    @RequestMapping(value = "admin/findAdminId")
    public String findAdminId(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass() + "admin/findAdminId start!!");

        log.info(this.getClass() + "admin/findAdminId end!!");

        return "/admin/findAdminId";
    }

    // 이메일 입력 후 인증 메일 발송하는 컨트롤러
    @RequestMapping(value = "admin/sendMailForFindID")
    public String SendMailForAdminFindID(HttpServletRequest request, ModelMap model, HttpSession session)
            throws Exception {

        log.info(this.getClass() + "sendMailForFindID Page start!!");

        session.invalidate(); // 세션 정보 초기화

        String email = CmmUtil.nvl(request.getParameter("adm_email"));
        log.info("email : " + email);

        AdminInfoDTO tDTO = new AdminInfoDTO();
        String msg;
        String url;

        tDTO.setAdm_email(email);

        AdminInfoDTO rDTO = AdminService.getFindAdminInfo(tDTO);
        String emailForDB = rDTO.getAdm_email();

        log.info("email : " + email);
        log.info("rDTO.getAdm_email : " + emailForDB);

        if (emailForDB == null) {
            log.info("null : " + emailForDB);
            msg = "가입된 회원이 없습니다.";
            url = "/adminLoginPage";
        } else {

            String title = "메일로 아이디를 보내드렸습니다";
            MailDTO pDTO = new MailDTO();

            pDTO.setTitle(title);
            pDTO.setToMail(email);

            rDTO = AdminService.getFindAdminID(tDTO);
            String idForDB = rDTO.getAdm_id();

            String contents = idForDB;
            pDTO.setContents(contents);

            session = request.getSession();
            session.setAttribute("random", contents);
            session.setAttribute("sessionEmail", emailForDB);

            log.info("rDTO.getAdm_id : " + idForDB);

            mailService.doSendMail(pDTO);

            msg = "메일 발송을 완료 하였습니다.";
            url = "/adminLoginPage";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass() + "SendMailForFindID Page end!!");

        return "/redirect";
    }

    // 비밀번호 찾기 보여주는 페이지
    @RequestMapping(value = "admin/sodoi")
    public String sodoi(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass() + "admin/sodoi start!!");

        log.info(this.getClass() + "admin/sodoi end!!");

        return "/admin/sodoi";
    }

    // 이메일 입력 후 인증 메일 발송하는 컨트롤러
    @RequestMapping(value = "admin/sendMailForFindAdmin")
    public String SendMailForFindAdmin(HttpServletRequest request, ModelMap model, HttpSession session)
            throws Exception {

        log.info(this.getClass() + "SendMailForFindAdmin Page start!!");

        session.invalidate(); // 세션 정보 초기화

        String email = CmmUtil.nvl(request.getParameter("adm_email"));
        log.info("email : " + email);
        AdminInfoDTO tDTO = new AdminInfoDTO();
        String msg;
        String url;

        tDTO.setAdm_email(email);

        AdminInfoDTO rDTO = new AdminInfoDTO();
        rDTO = AdminService.getFindAdminInfo(tDTO);
        String emailForDB = rDTO.getAdm_email();

        log.info("email : " + email);
        log.info("rDTO.getAdm_email : " + emailForDB);

        if (emailForDB == null) {
            log.info("null : " + emailForDB);
            msg = "가입된 회원이 없습니다.";
            url = "/adminLoginPage";
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
            url = "/admin/changeAdminPwdPage";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass() + "SendMailForFindAdmin Page end!!");

        return "/redirect";
    }

    // 인증메일 비교를 위한 페이지 띄우는 로직
    @RequestMapping(value = "admin/changeAdminPwdPage")
    public String changeAdminPwdPage() throws Exception {
        log.info(this.getClass().getName() + "changeAdminPwdPage Start!!");
        log.info(this.getClass().getName() + "changeAdminPwdPage End!!");
        return "/admin/changeAdminPwd";
    }

    // 인증번호 성공 후 비번 변경 페이지 띄우는 컨트롤러
    @RequestMapping(value = "admin/changeAdminPwdProcPage")
    public String changeAdminPwdProcPage() throws Exception {
        log.info(this.getClass().getName() + "changeAdminPwdProcPage Start!!");
        log.info(this.getClass().getName() + "changeAdminPwdProcPage End!!");
        return "/admin/changeAdminPwdProc";
    }

    // 비밀번호 변경처리 로직
    @RequestMapping(value = "admin/changeAdminPwdProc")
    public String changeAdminPwdProc(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
        log.info(this.getClass() + "changeAdminPwdProc Page end!!");

//        String email = CmmUtil.nvl(session.getAttribute("sessionEmail").toString());
//
//        session.invalidate(); // 세션 정보 초기화
        String adm_id = CmmUtil.nvl(request.getParameter("adm_id"));
        String adm_pw = CmmUtil.nvl(EncryptUtil.encHashSHA256(request.getParameter("adm_pw")));

        log.info("adm_id : " + adm_id);
        log.info("adm_pw : " + adm_pw);

        AdminInfoDTO pDTO = new AdminInfoDTO();

        pDTO.setAdm_id(adm_id);
        pDTO.setAdm_pw(adm_pw);

        int success = AdminService.updateAdminPwdInfo(pDTO);

        String msg = "";
        String url = "";

        if (success == 1) {
            msg = "정보 변경이 완료되었습니다. 로그인 해주세요.";
            url = "/adminLoginPage";
        } else {
            msg = "오류로 인해 비밀번호 변경을 실패하였습니다";
            url = "/admin/changeAdminPwdProcPage";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass() + "changeAdminPwdProc Page end!!");

        return "/redirect";
    }
}
