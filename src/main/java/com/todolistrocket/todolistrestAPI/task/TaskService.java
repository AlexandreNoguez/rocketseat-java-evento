package com.todolistrocket.todolistrestAPI.task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final ITaskRepository taskRepository;

    public TaskModel createTask(TaskModel taskModel, UUID idUser) {
        return taskRepository.save(taskModel);
    }

    public List<TaskModel> getTasksByUserId(UUID idUser) {
        return taskRepository.findByIdUser(idUser);
    }

    public TaskModel updateTask(UUID id, TaskModel taskModel, UUID idUser) throws Exception {
        Optional<TaskModel> existingTaskOptional = taskRepository.findById(id);

        if (existingTaskOptional.isPresent()) {
            TaskModel existingTask = existingTaskOptional.get();

            if (existingTask.getIdUser().equals(idUser)) {
                existingTask.setTitle(taskModel.getTitle());
                existingTask.setStartAt(taskModel.getStartAt());
                existingTask.setEndAt(taskModel.getEndAt());
                TaskModel updatedTask = taskRepository.save(existingTask);

                return updatedTask;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
