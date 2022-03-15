package org.example.demo.service;

import org.example.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    ResponseEntity<User> getUser(Integer id);
    void deleteUser(Integer id);
    User saveUser(User user);
    //ModelAndView logedIn(@ModelAttribute User user)
    User updateUser(Integer id, User user);
    User checkUser(@ModelAttribute User user);
}
