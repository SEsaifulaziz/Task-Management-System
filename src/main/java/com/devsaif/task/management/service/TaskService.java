package com.devsaif.task.management.service;

import com.devsaif.task.management.dto.TaskRequest;
import com.devsaif.task.management.dto.TaskResponse;
import com.devsaif.task.management.entity.TaskPriority;
import com.devsaif.task.management.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest taskRequest);

    Page<TaskResponse> getTasks(
            TaskStatus status,
            TaskPriority priority,
            Pageable pageable
    );

    TaskResponse getTaskById(Long id);

    void deleteTask(Long id);

    TaskResponse updateTask(Long id, TaskRequest taskRequest);
}
