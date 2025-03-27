package org.example.repository;

import org.example.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByLastNameIgnoreCase(String lastName);
}