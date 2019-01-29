package cn.com.sinosoft.payrollemail.service;

import cn.com.sinosoft.payrollemail.domain.Payroll;

/**
 * Created by fangzy on 2018/8/10 16:40
 */
public interface SendEmailService {
    void sendEmail(Payroll payroll);
    void sendHtmlEmail(Payroll payroll) throws Exception;
    void sendTextEmail(String to,String content);
}
