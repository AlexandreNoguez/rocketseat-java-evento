package com.todolistrocket.todolistrestAPI.task;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        var idUser = (UUID) request.getAttribute("idUser");
        TaskModel createdTask = taskService.createTask(taskModel, idUser);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping
    public List<TaskModel> list(HttpServletRequest request) {
        var idUser = (UUID) request.getAttribute("idUser");
        List<TaskModel> tasks = taskService.getTasksByUserId(idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskModel> update(@RequestBody TaskModel taskModel, @PathVariable("id") UUID id,
            HttpServletRequest request) throws Exception {
        var idUser = (UUID) request.getAttribute("idUser");
        TaskModel updatedTask = taskService.updateTask(id, taskModel, idUser);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.badRequest().body(null); // Tratar erro apropriadamente
        }
    }

}
