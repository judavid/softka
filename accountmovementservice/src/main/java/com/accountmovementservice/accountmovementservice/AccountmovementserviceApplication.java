package com.accountmovementservice.accountmovementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class AccountmovementserviceApplication {

	public static void main(String[] args) {
		Map<Integer, Integer> map = new HashMap<>();
		ArrayList<Integer> arrayList;
		SpringApplication.run(AccountmovementserviceApplication.class, args);
	}

}
