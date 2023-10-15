package com.todolistrocket.todolistrestAPI.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserModel userModel) {
        UserModel createdUser = userService.createUser(userModel);
        if (createdUser == null) {
            String errorMessage = "O usuário já existe"; // Mensagem de erro personalizada
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        return ResponseEntity.status(HttpStatus.OK).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserModel> updateUser(@PathVariable UUID userId, @RequestBody UserModel newUser) {
        UserModel updatedUser = userService.updateUser(userId, newUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
