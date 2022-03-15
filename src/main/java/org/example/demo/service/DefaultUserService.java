package org.example.demo.service;

import org.example.demo.model.User;
import org.example.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository repository;

    public void setUserRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<User> getUser(Integer id) {
        Optional<User> optionalUser = repository.findById(id);
        if(optionalUser.isPresent())
        {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }
        //return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
    }

    @Override
    public void deleteUser(Integer id) {

        Optional<User> optionalUser = repository.findById(id);
        if(optionalUser.isPresent())
        {
            repository.deleteById(id);
        }
        //return new ResponseEntity<>(optionalCustomer.get(), HttpStatus.OK);
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

    }

    @Override
    public User saveUser(User user) {

        return repository.saveAndFlush(user);
    }

    @Override
    public User updateUser(Integer id, User user) {

        ResponseEntity<User> responseEntity=getUser(id);
        if(responseEntity.getStatusCode().is4xxClientError())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "INVALID USER ID");
        }

        User existingUser = responseEntity.getBody();
        BeanUtils.copyProperties(user, existingUser, "id");
        return repository.saveAndFlush(existingUser);

    }

    @Override
    public User checkUser(User user) {
        for (User user1 : getAllUsers()) {
            if (user1.getEmail().equals(user.getEmail())) {
                if (user1.getPassword().equals(user.getPassword()))
                {
//                    User user2=user1;
                    return user1;
                }
            }
        }
        return new User();
    }
}
