package cn.com.sinosoft.payrollemail.domain;

/**
 * Created by fangzy on 2018/8/11 9:33
 */
public class Payroll {
    //姓名
    private String name;
    //基本工资
    private String basicWage;
    //奖金
    private String bonus;
    //补助
    private String subsidy;
    //扣除 请假、旷工等
    private String deduction;
    //社保扣除
    private String socialSecurityDeduction;
    //公积金扣除
    private String providentFundDeduction;
    //个人所得税扣除
    private String taxDeduction;
    //个人总收入
    private String personalIncome;
    //接收邮件地址
    private String email;
    //日期
    private String wageMonth;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBasicWage() {
        return basicWage;
    }

    public void setBasicWage(String basicWage) {
        this.basicWage = basicWage;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(String subsidy) {
        this.subsidy = subsidy;
    }

    public String getDeduction() {
        return deduction;
    }

    public void setDeduction(String deduction) {
        this.deduction = deduction;
    }

    public String getSocialSecurityDeduction() {
        return socialSecurityDeduction;
    }

    public void setSocialSecurityDeduction(String socialSecurityDeduction) {
        this.socialSecurityDeduction = socialSecurityDeduction;
    }

    public String getProvidentFundDeduction() {
        return providentFundDeduction;
    }

    public void setProvidentFundDeduction(String providentFundDeduction) {
        this.providentFundDeduction = providentFundDeduction;
    }

    public String getTaxDeduction() {
        return taxDeduction;
    }

    public void setTaxDeduction(String taxDeduction) {
        this.taxDeduction = taxDeduction;
    }

    public String getPersonalIncome() {
        return personalIncome;
    }

    public void setPersonalIncome(String personalIncome) {
        this.personalIncome = personalIncome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWageMonth() {
        return wageMonth;
    }

    public void setWageMonth(String wageMonth) {
        this.wageMonth = wageMonth;
    }


    @Override
    public String toString() {
        return "payroll{" +
                ", name='" + name + '\'' +
                ", basicWage=" + basicWage +
                ", bonus=" + bonus +
                ", Subsidy=" + subsidy +
                ", deduction=" + deduction +
                ", socialSecurityDeduction=" + socialSecurityDeduction +
                ", ProvidentFundDeduction=" + providentFundDeduction +
                ", taxDeduction=" + taxDeduction +
                ", personalIncome=" + personalIncome +
                ", email='" + email + '\'' +
                ", wageMonth=" + wageMonth +
                '}';
    }
}
