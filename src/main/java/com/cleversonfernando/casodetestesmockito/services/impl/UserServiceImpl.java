package com.cleversonfernando.casodetestesmockito.services.impl;

import com.cleversonfernando.casodetestesmockito.DTO.UserDTO;
import com.cleversonfernando.casodetestesmockito.domain.User;
import com.cleversonfernando.casodetestesmockito.repositories.UserRepository;
import com.cleversonfernando.casodetestesmockito.services.UserService;
import com.cleversonfernando.casodetestesmockito.services.exceptions.DataIntegratyViolationException;
import com.cleversonfernando.casodetestesmockito.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {

        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class));
    }

    @Override
    public User update(UserDTO obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);

    }

    private void findByEmail(UserDTO obj) {
        Optional<User> user = userRepository.findByEmail(obj.getEmail());
        if (user.isPresent() && !user.get().getId().equals(obj.getId())) {
            throw new DataIntegratyViolationException("E-mail já cadastrado!");
        }
    }
}
