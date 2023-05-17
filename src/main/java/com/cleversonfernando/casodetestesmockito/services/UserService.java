package com.cleversonfernando.casodetestesmockito.services;

import com.cleversonfernando.casodetestesmockito.DTO.UserDTO;
import com.cleversonfernando.casodetestesmockito.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserDTO obj);

    User update(UserDTO obj);

    void delete(Integer id);
}
