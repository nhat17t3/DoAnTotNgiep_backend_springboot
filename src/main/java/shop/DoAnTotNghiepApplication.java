package shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@ComponentScan({ "shop.controller","shop.service", "shop.repository", "shop.service.impl","shop.config" })
//@EntityScan("shop.entity")
//@EnableJpaRepositories("shop.repository")
//@SpringBootApplication


@SpringBootApplication   
@EnableAutoConfiguration
@ComponentScan(basePackages={"shop"})
@EnableJpaRepositories(basePackages="shop.repository")
@EnableTransactionManagement
@EntityScan(basePackages="shop.entity")
public class DoAnTotNghiepApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoAnTotNghiepApplication.class, args);
	}

}
