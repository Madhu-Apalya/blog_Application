package org.blog.repository;

import org.blog.model.Role;
import org.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(@Param("email") String email);

    Optional<User> findByUsername(@Param("username") String username);
    
    Optional<User> findById(Long id);
    
    List<User> findAllByRoles(Role role);
}
