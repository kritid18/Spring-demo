package org.example.demo.controller;

import org.example.demo.model.User;
import org.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")  //Post Request
    public User saveUserr(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping   //Get Request
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")  //Request Mapping also applicable
    public ResponseEntity<User> getUser(@PathVariable Integer id)
    {
        return userService.getUser(id);
    }

    @DeleteMapping(value = "{Id}")
    public void deleteUser(@PathVariable Integer Id)
    {
        userService.deleteUser(Id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
//    @RequestMapping(value = "/addUsers", method = RequestMethod.POST)
//    public void saveUser(@RequestBody User user){
//        userService.saveUser(user);
//        //return "addUsers";
//    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ModelAndView logedIn(@ModelAttribute User user) {
//
//        ModelAndView modelAndView = new ModelAndView();
//        List<User> users = userService.getAllUsers();
//        User user1=userService.checkUser(user);
//                if (user1!=null) {
//                    user.setName(user1.getName());
//                    modelAndView.setViewName("home");
//                    modelAndView.addObject("user",user1);
//
//                    return modelAndView;
//                }
//
//
//
//        return new ModelAndView();
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String logedIn(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        System.out.println(email+ " "+ password);

        List<User> users = userService.getAllUsers();
        for (User user1 : users) {
            if (user1.getEmail().equals(user.getEmail())) {
                if (user1.getPassword().equals(user.getPassword())) {
                    return "home";
                }
            }
            else
            {
                return "error";
            }
        }

        return "error";

    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/addUserPage")
    public String addUserPage() {
        return "addUsers";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
}
