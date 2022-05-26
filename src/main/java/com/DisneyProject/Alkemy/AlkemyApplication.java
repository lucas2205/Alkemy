package com.DisneyProject.Alkemy;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlkemyApplication {
    
     @Bean
        public ModelMapper modelMapper(){
            return new ModelMapper();
        }

	public static void main(String[] args) {
		SpringApplication.run(AlkemyApplication.class, args);
	}

}
