package cn.com.sinosoft.payrollemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class PayrollEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayrollEmailApplication.class, args);
	}
}
