package com.cashify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CashifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashifyApplication.class, args);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<< CASHIFY RUNNING >>>>>>>>>>>>>>>>>>>>>>>>");
	}

}
