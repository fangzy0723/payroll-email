package cn.com.sinosoft.payrollemail.service.impl;

import cn.com.sinosoft.payrollemail.service.SendEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by fangzy on 2018/8/10 16:53
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DefaultSendEmailServiceTest {

    @Autowired
    SendEmailService sendEmailService;

    @Test
    public void sendEmail() throws Exception {
        sendEmailService.sendEmail(null);
    }
}