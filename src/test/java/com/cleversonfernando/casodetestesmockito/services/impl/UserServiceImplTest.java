package com.cleversonfernando.casodetestesmockito.services.impl;

import com.cleversonfernando.casodetestesmockito.DTO.UserDTO;
import com.cleversonfernando.casodetestesmockito.domain.User;
import com.cleversonfernando.casodetestesmockito.repositories.UserRepository;
import com.cleversonfernando.casodetestesmockito.services.exceptions.DataIntegratyViolationException;
import com.cleversonfernando.casodetestesmockito.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Valdir";
    public static final String EMAIL = "valdir@gmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";
    public static final int INDEX = 0;
    public static final String E_MAIL_JA_CADASTRADO = "E-mail já cadastrado!";

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;
    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnUserInstance() {
        Mockito.when(userRepository.findById(anyInt())).thenReturn(optionalUser);
        User response = userService.findById(ID);
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnObjectNotFoundException() {
        Mockito.when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try {
            userService.findById(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAListOfUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> response = userService.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        Mockito.when(userRepository.save(any())).thenReturn(user);

        User response = userService.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            userService.create(userDTO);
        } catch (Exception e) {
            assertEquals(DataIntegratyViolationException.class, e.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO, e.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        Mockito.when(userRepository.save(any())).thenReturn(user);

        User response = userService.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnDataIntegrityViolationException() {
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);
        try {
            optionalUser.get().setId(2);
            userService.create(userDTO);
        } catch (Exception e) {
            assertEquals(DataIntegratyViolationException.class, e.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO, e.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        Mockito.when(userRepository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(userRepository).deleteById(anyInt());
        userService.delete(ID);
        verify(userRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void whenDeleteWithObjectNotFoundException() {
        Mockito.when(userRepository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            userService.delete(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
        }
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}