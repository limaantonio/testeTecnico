package com.example.teste.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.teste.domain.Info;
import com.example.teste.infoRepository.InfoRepository;

public class Instantiation {
	@Autowired
	private InfoRepository infoRepository;

	public void run(String... args) throws Exception {
		infoRepository.deleteAll();
		
		Info inf1 = new Info(null, 44332211, "123", "PWWIN", 0 , "F04A2E4088B", 4, "8.00b3", 0 ,
				16777216, "PWWIN");
		
		infoRepository.saveAll(Arrays.asList(inf1));	
	}
}
