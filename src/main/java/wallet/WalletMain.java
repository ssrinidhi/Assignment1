package wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class WalletMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(WalletMain.class,args);
	}

}
