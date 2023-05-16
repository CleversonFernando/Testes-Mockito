package com.cleversonfernando.casodetestesmockito.services;

import com.cleversonfernando.casodetestesmockito.domain.User;

public interface UserService {

    User findById(Integer id);
}
