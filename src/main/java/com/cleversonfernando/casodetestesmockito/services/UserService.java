package com.cleversonfernando.casodetestesmockito.services;

import com.cleversonfernando.casodetestesmockito.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
