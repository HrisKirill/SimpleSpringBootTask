package com.example.proxybandtechnicaltask.services.implementation;

import com.example.proxybandtechnicaltask.entities.User;
import com.example.proxybandtechnicaltask.exception.CustomEmptyDataException;
import com.example.proxybandtechnicaltask.repositories.UserRepository;
import com.example.proxybandtechnicaltask.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        Optional<User> userForCreatingIdOptional = userRepository.findById(user.getId());

        if (userForCreatingIdOptional.isEmpty()) {
            return userRepository.save(user);
        } else {
            throw new CustomEmptyDataException("User is already create");
        }
    }

    @Override
    public User updateUser(User user) {
        Optional<User> userForUpdatingIdOptional = userRepository.findById(user.getId());
        if (userForUpdatingIdOptional.isPresent()) {
            User target = userForUpdatingIdOptional.get();
            target.setName(user.getName());
            target.setEmail(user.getEmail());
            return userRepository.save(target);
        } else {
            throw new CustomEmptyDataException("User does not exist");
        }
    }

    @Override
    public User findById(Long id) throws CustomEmptyDataException {
        Optional<User> userGettingByIdOptional = userRepository.findById(id);
        if (userGettingByIdOptional.isPresent()) {
            return userGettingByIdOptional.get();
        } else {
            throw new CustomEmptyDataException("Unable to get user");
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> userForDeletingIdOptional = userRepository.findById(id);
        if (userForDeletingIdOptional.isPresent()) {
            userRepository.delete(userForDeletingIdOptional.get());
        } else {
            throw new CustomEmptyDataException("Unable to delete user");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
