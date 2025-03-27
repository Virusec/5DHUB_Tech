package org.example.repository;

import org.example.model.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Anatoliy Shikin
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByLastNameIgnoreCase(Pageable pageable, String lastName);
}