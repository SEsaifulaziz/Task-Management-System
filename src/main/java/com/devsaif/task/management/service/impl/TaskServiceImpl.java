package com.devsaif.task.management.service.impl;


import com.devsaif.task.management.dto.TaskRequest;
import com.devsaif.task.management.dto.TaskResponse;
import com.devsaif.task.management.entity.TaskEntity;
import com.devsaif.task.management.entity.TaskPriority;
import com.devsaif.task.management.entity.TaskStatus;
import com.devsaif.task.management.mapper.TaskMapper;
import com.devsaif.task.management.repository.TaskRepository;
import com.devsaif.task.management.service.TaskService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        TaskEntity task = taskMapper.toEntity(taskRequest);
        TaskEntity savedTask = taskRepository .save(task);

        return taskMapper.toDto(savedTask);

    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return List.of();
    }

    @Override
    public TaskResponse getTaskById() {
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        return null;
    }

    @Override
    public List<TaskResponse> getTaskByStatus(TaskStatus taskStatus) {
        return List.of();
    }

    @Override
    public List<TaskPriority> getTaskByPriority(TaskPriority taskPriority) {
        return List.of();
    }
}
