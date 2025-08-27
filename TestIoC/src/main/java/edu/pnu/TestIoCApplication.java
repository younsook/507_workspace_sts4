package edu.pnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ImportResource("classpath:beans.xml")
public class TestIoCApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestIoCApplication.class, args);
	}

}
