package com.example.proxybandtechnicaltask.services.interfaces;

import com.example.proxybandtechnicaltask.entities.User;
import com.example.proxybandtechnicaltask.exception.CustomEmptyDataException;

import java.util.List;

public interface IUserService {
    User createUser(User user) throws CustomEmptyDataException;

    User updateUser(User user) throws CustomEmptyDataException;

    User findById(Long id) throws CustomEmptyDataException;

    String deleteUser(Long id);

    List<User> getAllUsers();
}
