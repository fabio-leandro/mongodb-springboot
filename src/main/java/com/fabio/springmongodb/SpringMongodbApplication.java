package com.fabio.springmongodb;

import com.fabio.springmongodb.domain.User;
import com.fabio.springmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringMongodbApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringMongodbApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		User user = new User(null,"Fábio","fabio@gmail.com");
		User user1 = new User(null, "Paula", "paula@gmail.com");
		User user2 = new User(null, "Carol", "carol@gmail.com");

		userRepository.saveAll(List.of(user,user1,user2));

	}
}
