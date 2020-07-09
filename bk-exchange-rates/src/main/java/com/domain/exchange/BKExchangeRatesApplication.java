package com.domain.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hithasree.mandapaka
 *
 */
@SpringBootApplication
@EnableSwagger2
public class BKExchangeRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BKExchangeRatesApplication.class, args);
	}
	
	/**
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
