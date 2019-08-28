package com.example.demo.service.impl;

import com.example.demo.service.AccountEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
/**
 * 账户邮件服务
 *
 * @author yuwb@corp.21cn.com
 * @date 2019/8/20 上午11:39
 */
@Service
public class AccountEmailServiceImpl implements AccountEmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${system.email}")
    private String systemEmail;

    /**
     * 发送邮件
     */
    @Override
    public void sendMail(String to, String subject, String htmlText) {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper msgHelper = new MimeMessageHelper(msg);

        try {
            msgHelper.setFrom(systemEmail);
            msgHelper.setTo(to);
            msgHelper.setSubject(subject);
            msgHelper.setText(htmlText, true);

            javaMailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
