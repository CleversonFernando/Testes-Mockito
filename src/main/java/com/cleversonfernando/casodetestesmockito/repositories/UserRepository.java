package com.cleversonfernando.casodetestesmockito.repositories;

import com.cleversonfernando.casodetestesmockito.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
