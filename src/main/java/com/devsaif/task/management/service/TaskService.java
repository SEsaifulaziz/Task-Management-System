package com.devsaif.task.management.service;

import com.devsaif.task.management.dto.TaskRequest;
import com.devsaif.task.management.dto.TaskResponse;
import com.devsaif.task.management.entity.TaskPriority;
import com.devsaif.task.management.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest taskRequest);

    List<TaskResponse> getAllTasks();

    TaskResponse getTaskById(Long id);

    void deleteTask(Long id);

    TaskResponse updateTask(Long id, TaskRequest taskRequest);

    List<TaskResponse> getTaskByStatus (TaskStatus taskStatus);

    List<TaskPriority> getTaskByPriority(TaskPriority taskPriority);


}
