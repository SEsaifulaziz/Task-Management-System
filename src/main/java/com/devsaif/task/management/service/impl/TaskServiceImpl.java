package com.devsaif.task.management.service.impl;


import com.devsaif.task.management.dto.TaskRequest;
import com.devsaif.task.management.dto.TaskResponse;
import com.devsaif.task.management.entity.TaskEntity;
import com.devsaif.task.management.entity.TaskPriority;
import com.devsaif.task.management.entity.TaskStatus;
import com.devsaif.task.management.exception.ResourceNotFoundException;
import com.devsaif.task.management.mapper.TaskMapper;
import com.devsaif.task.management.repository.TaskRepository;
import com.devsaif.task.management.service.TaskService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        TaskEntity task = taskMapper.toEntity(taskRequest);
        TaskEntity savedTask = taskRepository.save(task);

        return taskMapper.toDto(savedTask);

    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream().map(taskMapper::toDto).toList();
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDto).orElseThrow(() ->
                        new ResourceNotFoundException("Task with id " +id+ " not found"));
    }

    @Override
    public void deleteTask(Long id) {

    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {

        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Task with id " +id+ " not found"));

        if(taskEntity == null && taskEntity.equals("")){
            throw new ResourceNotFoundException("Task with id " +id+ " not found");
        }

        taskEntity.setCreatedAt(LocalDateTime.now());
        taskEntity.setDescription(taskRequest.description());
        taskEntity.setDueDate(taskRequest.dueDate());
        taskEntity.setPriority(taskRequest.priority());
        taskEntity.setTitle(taskRequest.title());
        taskEntity.setStatus(taskRequest.status());

        TaskEntity newTask = taskRepository.save(taskEntity);
        return taskMapper.toDto(newTask);
    }

    @Override
    public List<TaskResponse> getTaskByStatus(TaskStatus taskStatus) {

        List<TaskEntity> task = taskRepository.findByStatus(taskStatus);

        if (!task.isEmpty() && !task.equals("")){
            return taskRepository.findByStatus(taskStatus)
                    .stream().map(taskMapper::toDto).toList();
        }
        throw new ResourceNotFoundException("task with status " +taskStatus+ " not found");
    }

    @Override
    public List<TaskPriority> getTaskByPriority(TaskPriority taskPriority) {
        return List.of();
    }
}
