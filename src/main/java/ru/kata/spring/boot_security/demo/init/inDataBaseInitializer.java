package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;


@Configuration
public class inDataBaseInitializer {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Autowired
    public inDataBaseInitializer(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole);
        Role userRole = new Role("ROLE_USER");
        roleRepository.save(userRole);

        User admin = new User("admin", "admin", "admin@admin.ru", "admin", passwordEncoder.encode("admin"));
        admin.setRoles(Set.of(adminRole, userRole));
        userRepository.save(admin);
        User user = new User("user", "user", "user@user.ru", "user", passwordEncoder.encode("user"));
        user.setRoles(Set.of(userRole));
        userRepository.save(user);
    }
}


