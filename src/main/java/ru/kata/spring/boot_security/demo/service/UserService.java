package ru.kata.spring.boot_security.demo.service;


import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User findUserById(Long id);

    void update(Long id, User updateUser);

    User saveUser(User user);

    void deleteById(Long id);
}