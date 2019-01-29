package cn.com.sinosoft.payrollemail.service.impl;

import cn.com.sinosoft.payrollemail.domain.Payroll;
import cn.com.sinosoft.payrollemail.service.GetExcelDataService;
import cn.com.sinosoft.payrollemail.service.SendEmailService;
import cn.com.sinosoft.payrollemail.util.DateFormatUtil;
import cn.com.sinosoft.payrollemail.util.ReadExcelData;
import cn.com.sinosoft.payrollemail.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fangzy on 2018/8/11 11:00
 */
@Service
public class DefaultGetExcelDataService implements GetExcelDataService {

    @Autowired
    private SendEmailService sendEmailService;

    @Override
    public void getExcelDataToList(String fileName, MultipartFile file) {
        List<Payroll> payrolls = null;
        try {
            payrolls = getData(fileName, file);
            //正常获取到数据，循环发送邮件
            payrolls.stream().forEach(e -> {
                System.out.println(e);
                sendEmailService.sendEmail(e);
            });
        } catch (Exception e) {
            //发送失败的邮件，提示发送失败
            sendEmailService.sendTextEmail("fangzy0723@outlook.com","请检查文件内容或格式，仅支持“.xls和.xlsx的文件。”");
            e.printStackTrace();
        }
    }

    private List<Payroll> getData(String fileName, MultipartFile file) throws Exception {
        String[][] result = ReadExcelData.getData(fileName, file);

        List<Payroll> list = new ArrayList<>();

        int rowLength = result.length;
        for(int i=0;i<rowLength;i++) {


            Payroll payroll = new Payroll();

            for(int j=0;j<result[i].length;j++) {

                System.out.print(result[i][j]+"\t\t");
                if (j==0) {//姓名
                    payroll.setName(result[i][j]);
                }else if (j==1) { //基本工资
                    payroll.setBasicWage(result[i][j]);
                }else if(j==2){//奖金
                    payroll.setBonus(result[i][j]);
                }else if(j==3){//补助
                    payroll.setSubsidy(result[i][j]);
                }else if(j==4){//扣除 请假、旷工等
                    payroll.setDeduction(result[i][j]);
                }else if(j==5){//社保扣除
                    payroll.setSocialSecurityDeduction(result[i][j]);
                }else if(j==6){//公积金扣除
                    payroll.setProvidentFundDeduction(result[i][j]);
                }else if(j==7){//个人所得税扣除
                    payroll.setTaxDeduction(result[i][j]);
                }else if(j==8){//个人总收入
                    payroll.setPersonalIncome(result[i][j]);
                }else if(j==9){//接收邮件地址
                    payroll.setEmail(result[i][j]);
                }else if(j==10){//日期
                    String workDate = result[i][j].replaceAll("-","/");
                    if(StringUtils.isNotBlank(workDate)){
                        try {
                            Date date = DateFormatUtil.parse("yyyy/MM/dd",workDate);
                            payroll.setWageMonth(result[i][j]);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
            list.add(payroll);
            System.out.println();
        }
        System.out.println(list);
        return list;
    }
}
