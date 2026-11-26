package com.devsaif.task.management.service.impl;


import com.devsaif.task.management.dto.TaskDashboardResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
    public Page<TaskResponse> getTasks(
            TaskStatus status,
            TaskPriority priority,
            Pageable pageable
    ) {
        return taskRepository.findByFilters(status, priority, pageable)
                .map(taskMapper::toDto);
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDto).orElseThrow(() ->
                        new ResourceNotFoundException("Task with id " +id+ " not found"));
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("task with id " +id+ " not found"));
        taskRepository.deleteById(id);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {

        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Task with id " +id+ " not found"));

        taskEntity.setDescription(taskRequest.description());
        taskEntity.setDueDate(taskRequest.dueDate());
        taskEntity.setTitle(taskRequest.title());

        if(taskRequest.status() !=null){
            taskEntity.setStatus(taskRequest.status());
        }
        if(taskRequest.priority() !=null){
            taskEntity.setPriority(taskRequest.priority());
        }

        TaskEntity newTask = taskRepository.save(taskEntity);
        return taskMapper.toDto(newTask);
    }

    @Override
    public TaskDashboardResponse getDashBoardStatistics() {

        long totalTasks = taskRepository.count();

        long completedTasks =
                taskRepository.countByStatus(TaskStatus.COMPLETED);

        long inProgressTasks =
                taskRepository.countByStatus(TaskStatus.IN_PROGRESS);

        long pendingTasks =
                taskRepository.countByStatus(TaskStatus.TODO);

        long highPriorityTasks =
                taskRepository.countByPriority(TaskPriority.HIGH);

        long overdueTasks =
                taskRepository.countByDueDateBeforeAndStatusNot(
                        LocalDate.now(),
                        TaskStatus.COMPLETED
                );
        return new TaskDashboardResponse(
                totalTasks,
                completedTasks,
                inProgressTasks,
                pendingTasks,
                highPriorityTasks,
                overdueTasks
        );
    }

}
