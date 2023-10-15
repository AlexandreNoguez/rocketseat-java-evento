package com.todolistrocket.todolistrestAPI.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    public void updateAllFields(UserModel newUser) {
        this.username = newUser.getUsername();
        this.name = newUser.getName();
        this.password = newUser.getPassword();
    }
}
