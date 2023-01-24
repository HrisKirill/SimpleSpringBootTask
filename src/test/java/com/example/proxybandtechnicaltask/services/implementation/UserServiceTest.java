package com.example.proxybandtechnicaltask.services.implementation;

import com.example.proxybandtechnicaltask.entities.User;
import com.example.proxybandtechnicaltask.exception.CustomEmptyDataException;
import com.example.proxybandtechnicaltask.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    UserRepository userRepository = mock(UserRepository.class);
    UserService userService = new UserService(userRepository);


    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    private User getTestUser() {
        return User.builder()
                .id(1L)
                .email("test@gmail.com")
                .name("testName")
                .build();
    }

    @Test
    void createUserTest() {
        when(userRepository.findById(getTestUser().getId())).thenReturn(Optional.empty());
        when(userRepository.save(getTestUser())).thenReturn(getTestUser());
        assertEquals(getTestUser(), userService.createUser(getTestUser()));
    }

    @Test
    void createUserBadTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(getTestUser()));
        assertThrows(CustomEmptyDataException.class, () -> userService.createUser(getTestUser()));
    }

    @Test
    void updateUserTest() {
        when(userRepository.findById(getTestUser().getId())).thenReturn(Optional.of(getTestUser()));
        when(userRepository.save(getTestUser())).thenReturn(getTestUser());
        assertEquals(getTestUser(), userService.updateUser(getTestUser()));
    }

    @Test
    void updateUserBadTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CustomEmptyDataException.class, () -> userService.updateUser(getTestUser()));
    }


    @Test
    void findByIdTest() {
        when(userRepository.findById(getTestUser().getId())).thenReturn(Optional.of(getTestUser()));
        assertEquals(getTestUser(), userService.findById(getTestUser().getId()));
    }

    @Test
    void findByIdBadTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CustomEmptyDataException.class, () -> userService.findById(getTestUser().getId()));
    }

    @Test
    void deleteUser() {
        when(userRepository.findById(getTestUser().getId())).thenReturn(Optional.of(getTestUser()));
        assertDoesNotThrow(() -> userService.deleteUser(getTestUser().getId()));
    }

    @Test
    void deleteUserBadTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CustomEmptyDataException.class, () -> userService.deleteUser(getTestUser().getId()));
    }

    @Test
    void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(List.of(getTestUser()));
        assertEquals(getTestUser(),userService.getAllUsers().get(0));
    }


}