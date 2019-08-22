package com.example.demo.service;

/**
 * 账户邮件服务
 *
 * @author yuwb@corp.21cn.com
 * @date 2019/8/20 上午11:37
 */
public interface AccountEmailService {
    /**
     * 发送邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param htmlText 内容
     */
    void sendMail(String to, String subject, String htmlText);
}
