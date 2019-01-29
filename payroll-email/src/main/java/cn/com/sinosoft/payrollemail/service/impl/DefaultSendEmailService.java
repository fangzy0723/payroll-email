package cn.com.sinosoft.payrollemail.service.impl;

import cn.com.sinosoft.payrollemail.domain.Payroll;
import cn.com.sinosoft.payrollemail.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * Created by fangzy on 2018/8/10 16:40
 */
@Service
public class DefaultSendEmailService implements SendEmailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Override
    public void sendEmail(Payroll payroll){
        try{
            sendHtmlEmail(payroll);
        }catch (Exception e){
            sendTextEmail(payroll.getEmail(),payroll.getName()+"的工资条发送失败！");
        }
    }

    @Override
    public void sendHtmlEmail(Payroll payroll) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(new String[] {payroll.getEmail()});
        mimeMessageHelper.setFrom("fangzhiyong@sinosoft.com.cn");
        mimeMessageHelper.setSubject(payroll.getName()+"的工资条");
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>"+payroll.getName()+":"+payroll.getBasicWage()+":"
                +payroll.getBonus()+":"+payroll.getDeduction()+":"
                +payroll.getPersonalIncome()+":"+payroll.getProvidentFundDeduction()+":"
                +payroll.getSocialSecurityDeduction()+":"+payroll.getSubsidy()+":"
                +payroll.getTaxDeduction()+":"+payroll.getWageMonth()+":"
                +"</h1></body>");
        sb.append("</html>");
        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 发送邮件
        System.out.println(payroll.getName()+"的邮件发生成功！");
        mailSender.send(mimeMessage);

    }

    @Override
    public void sendTextEmail(String to,String content){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人
        simpleMailMessage.setTo(new String[] {to});
        simpleMailMessage.setFrom("fangzhiyong@sinosoft.com.cn");
        simpleMailMessage.setSubject("邮件发送失败提示！");
        simpleMailMessage.setText(content);
        // 发送邮件
        mailSender.send(simpleMailMessage);
    }
}
