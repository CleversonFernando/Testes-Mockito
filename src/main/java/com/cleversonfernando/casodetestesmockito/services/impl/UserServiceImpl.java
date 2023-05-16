package com.cleversonfernando.casodetestesmockito.services.impl;

import com.cleversonfernando.casodetestesmockito.domain.User;
import com.cleversonfernando.casodetestesmockito.repositories.UserRepository;
import com.cleversonfernando.casodetestesmockito.services.UserService;
import com.cleversonfernando.casodetestesmockito.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {

        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }
}
