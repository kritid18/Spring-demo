package org.example.demo;


import org.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class DemoApplication {

//	@Autowired
//	UserDAO userDAO;

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

//		User user1 = new User(1, "Ankit", "ankit@gmail.com", "ankit123", 1234L);

//		UserDAO userDAO = new UserDAO();
//		userDAO.addUser(user1);

	}

}
