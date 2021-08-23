package br.com.lserra.aluraflixapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AluraflixApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluraflixApiApplication.class, args);
	}

}
