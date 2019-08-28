package com.example.demo.service.impl;

import com.example.demo.service.AccountEmailService;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.Message;
import javax.mail.MessagingException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountEmailServiceImplTest {
    private GreenMail greenMail;
    @Autowired
    AccountEmailService accountEmailService;

    @Before
    public void startMailServer() {
        greenMail = new GreenMail(ServerSetup.SMTP);
        greenMail.setUser("test@juvenx.com", "1234");
        greenMail.start();
    }

    @Test
    public void testSendMail() throws MessagingException {
        String subject = "Test Subject";
        String htmlText = "<h3>Test</h3>";

        accountEmailService.sendMail("test@jxx.com", subject, htmlText);
        greenMail.waitForIncomingEmail(2000, 1);

        Message[] msgs = greenMail.getReceivedMessages();
        assertEquals(1, msgs.length);
        assertEquals(subject, msgs[0].getSubject());
        assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());
    }

    @After
    public void stopMailServer() {
        greenMail.stop();
    }
}
