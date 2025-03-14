package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByLastNameIgnoreCase(String lastName);
}