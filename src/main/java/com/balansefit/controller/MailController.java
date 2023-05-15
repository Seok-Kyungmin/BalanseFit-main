package com.balansefit.controller;

import com.balansefit.dto.MailDTO;
import com.balansefit.service.IMailService;
import com.balansefit.util.CmmUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class MailController {

    // 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
    private Logger log = Logger.getLogger(this.getClass().getName());

    @Resource(name="MailService")
    IMailService mailService;

    // 메일 발송 페이지
    @RequestMapping(value = "/mail/mailForm")
    public String mailForm(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass() + "/mail/mailForm start!!");

        log.info(this.getClass() + "/mail/mailForm end!!");

        return "/mail/mailForm";
    }

//    // 메일 작성 페이지
//    @RequestMapping(value="mail/mailPage")
//    public String mailPage() throws Exception{
//        log.info(this.getClass().getName() + "mailPage Start!!");
//        log.info(this.getClass().getName() + "mailPage end!!");
//        return "/mail/mailPage";
//    }

    // 메일 전송 프로세스
    @RequestMapping(value="mail/sendMailProc")
    public String sendMailProc(HttpServletRequest request, ModelMap model) throws Exception{

        log.info(this.getClass().getName() + "send Mail Proc Start!!");

        String toMail = CmmUtil.nvl(request.getParameter("toMail")); //받는 사람
        String title = CmmUtil.nvl(request.getParameter("title")); // 제목
        String contents = CmmUtil.nvl(request.getParameter("contents"));
        String msg = "";
        String url = "";

        log.info("toMail : " + toMail);
        log.info("title : " + title);
        log.info("contents : " + contents);

        MailDTO pDTO = new MailDTO();
        pDTO.setToMail(toMail); // 받는 사람을 DTO에 저장
        pDTO.setTitle(title);   // 제목을 DTO에 저장
        pDTO.setContents(contents); // 내용을 DTO에 저장

        int res = mailService.doSendMail(pDTO);
        pDTO = null;

        if(res == 1) {
            log.info(this.getClass().getName() + "mail.sendMailProc succes!!");
            msg = "메일 발송 성공";
            url = "/userIndex";
        } else {
            log.info(this.getClass().getName() + "mail.sendMailProc fall!!");
            msg = "메일 발송 실패";
            url = "/mail/mailForm";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass().getName() + "send Mail Proc end!!");
        return "/redirect";
    }
}
