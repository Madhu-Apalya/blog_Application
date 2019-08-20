package org.blog.service;

import java.util.List;
import java.util.Optional;

import org.blog.model.User;

public interface UserService {
	Optional<User> findForId(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
    
    List<User> findAllUser();

    User save(User user);
    
    void delete(User user);
}
