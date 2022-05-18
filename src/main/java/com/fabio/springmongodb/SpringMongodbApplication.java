package com.fabio.springmongodb;

import com.fabio.springmongodb.domain.Post;
import com.fabio.springmongodb.domain.User;
import com.fabio.springmongodb.dtos.AuthorDto;
import com.fabio.springmongodb.repository.PostRepository;
import com.fabio.springmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
public class SpringMongodbApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringMongodbApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User user = new User(null,"Fábio","fabio@gmail.com");
		User user1 = new User(null, "Paula", "paula@gmail.com");
		User user2 = new User(null, "Carol", "carol@gmail.com");

		userRepository.saveAll(List.of(user,user1,user2));

		Post post1 = new Post(null,sdf.parse("21/03/2018"), "Partiu viagem","Vou viajar para São Paulo.Abraços.", new AuthorDto(user));
		Post post2 = new Post(null,sdf.parse("23/03/2018"), "Bom dia","Hoje eu acordei feliz", new AuthorDto(user));

		postRepository.saveAll(List.of(post1,post2));

		user.getPosts().addAll(List.of(post1,post2));

		userRepository.save(user);

	}
}
