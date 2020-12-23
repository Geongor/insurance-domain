package com.geongo.insurancedomain.repos;

import com.geongo.insurancedomain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

