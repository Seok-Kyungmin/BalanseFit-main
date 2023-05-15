package com.balansefit.service.impl;

import com.balansefit.dto.MailDTO;
import com.balansefit.service.IMailService;
import com.balansefit.util.CmmUtil;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;

@Service("MailService")
public class MailService implements IMailService {

    // 로그 파일 생성 및 출력을 위한 log4j 프레임워크의 자바 객체
    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    final String host = "smtp.gmail.com"; //네이버에서 제공하는 SMTP서버
    final String user = "sukm38621@gmail.com";
    final String password = "yfehcqjfaodweltn";
    final int port = 465;

    @Override
    public int doSendMail(MailDTO pDTO) {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근하는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".doSendMail start!");
        log.info(this.getClass().getName()+ " 보내는 사람 email : " + user);

        // 메일 발송 성공여부(발송성공 : 1 / 발송실패 : 0)
        int res = 1;

        // 전달 받은 DTO로부터 데이터 가져오기(DTO객체가 메모리에 올라가지 않아 Null이 발생할 수 있기 때문에 에러방지차원으로 if문 시용함
        if (pDTO == null) {
            pDTO = new MailDTO();
        }

        String toMail = CmmUtil.nvl(pDTO.getToMail()); //받는사람
        log.info(this.getClass().getName()+ "받는 사람 이메일 : " + toMail);

        Properties props = new Properties();
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.host", host); // javax 외부 라이브러리에 메일 보내는 사라므이 정보 설정
        props.put("mail.smtp.auth", "true"); // javax 외부 라이브러리에 메일 보내는 사람 인증 여부설정
        props.put("mail.smtp.user", user);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.transport.protocal","smtps");

        // 네이버 SMTP 서버 인증 처리 로직
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

            //메일 제목
            message.setSubject(CmmUtil.nvl(pDTO.getTitle()));
            // 메일 내용
            message.setText(CmmUtil.nvl(pDTO.getContents()));
            // 메일 발송
            Transport.send(message);

        } catch (MessagingException e) { // 메일 전송 관련 예러 다 잡기
            res = 0; // 메일발송이 실패하기 때문에 0으로 변경
            log.info("[EROOR]" + this.getClass().getName() + ".doSendMail : " + e);

        } catch (Exception e) { // 모든 에러 다 잡기
            res = 0; // 메일발송이 실패하기 때문에 0으로 변경
            log.info("[EROOR]" + this.getClass().getName() + ".doSendMail : " + e);
        }
        // 함수 종료 로그
        log.info(this.getClass().getName() + "doSendMail end!!");
        return res;
    }

}
