package br.com.booktalks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class BooktalksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooktalksApplication.class, args);
	}

}
