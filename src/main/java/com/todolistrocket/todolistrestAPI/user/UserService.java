package com.todolistrocket.todolistrestAPI.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolistrocket.todolistrestAPI.utils.Utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public UserModel createUser(UserModel userModel) {
        UserModel existingUser = userRepository.findByUsername(userModel.getUsername());
        if (existingUser != null) {
            // Lidar com a lógica de erro
            return null;
        }

        String passwordHash = BCrypt.withDefaults()
                .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHash);

        return userRepository.save(userModel);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel updateUser(UUID userId, UserModel newUser) {
        UserModel existingUser = userRepository.findById(userId).orElse(null);

        if (existingUser == null) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        Utils.copyNonNullProperties(newUser, existingUser); // Copie as propriedades não nulas

        return userRepository.save(existingUser);
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

}
