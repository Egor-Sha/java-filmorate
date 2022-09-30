package ru.yandex.practicum.filmorate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sound.sampled.Port;

@SpringBootApplication
public class FilmorateApplication {

	public static void main(String[] args) {
		System.out.println("App starts");
		SpringApplication.run(FilmorateApplication.class, args);
	}

}
