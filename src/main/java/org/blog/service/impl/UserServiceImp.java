package org.blog.service.impl;

import org.blog.model.Role;
import org.blog.model.User;
import org.blog.repository.RoleRepository;
import org.blog.repository.UserRepository;
import org.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String USER_ROLE = "ROLE_USER";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findForId(Long id) {
        return userRepository.findById(id);
    }
    
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        // Encode plaintext password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        // Set Role to ROLE_USER
        List<Role> l = new ArrayList<Role>();
        l.add(roleRepository.findByRole(USER_ROLE));
        //l.add(roleRepository.findByRole(ADMIN_ROLE));
        user.setRoles(l);
        //user.setRoles(Collections.singletonList(roleRepository.findByRole(USER_ROLE)));
        return userRepository.saveAndFlush(user);
    }
    
    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
    
    @Override
    public List<User> findAllUser() {
    	Role role = roleRepository.findByRole(USER_ROLE);
    	return userRepository.findAllByRoles(role);
    }

}
