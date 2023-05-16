package com.cleversonfernando.casodetestesmockito.repositories;

import com.cleversonfernando.casodetestesmockito.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
