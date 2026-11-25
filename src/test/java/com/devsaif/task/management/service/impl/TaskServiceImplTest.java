package com.devsaif.task.management.service.impl;

import com.devsaif.task.management.dto.TaskRequest;
import com.devsaif.task.management.dto.TaskResponse;
import com.devsaif.task.management.entity.TaskEntity;
import com.devsaif.task.management.entity.TaskPriority;
import com.devsaif.task.management.entity.TaskStatus;
import com.devsaif.task.management.mapper.TaskMapper;
import com.devsaif.task.management.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {


    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void shouldCreateTaskSuccessfully(){

        //Arrange
        TaskRequest request = new TaskRequest(
                "Complete Java assignment",
                "Finish Spring Boot project",
                TaskStatus.TODO,
                TaskPriority.HIGH,
                LocalDate.of(2026, 12, 1)
        );

        TaskEntity taskEntity = mock(TaskEntity.class);
        TaskEntity savedTask = mock(TaskEntity.class);

        TaskResponse expectedResponse = new TaskResponse(
                1L,
                "Complete Java assignment",
                "Finish Spring Boot project",
                TaskStatus.TODO,
                TaskPriority.HIGH,
                LocalDate.of(202, 12, 1),
                LocalDateTime.of(202, 11, 25, 10, 0)
        );

        when(taskMapper.toEntity(request)).thenReturn(taskEntity);
        when(taskRepository.save(taskEntity)).thenReturn(savedTask);
        when(taskMapper.toDto(savedTask)).thenReturn(expectedResponse);

        //Act
        TaskResponse actualResponse = taskService.createTask(request);

        //Assert
        assertEquals(expectedResponse, actualResponse);

        verify(taskMapper).toEntity(request);
        verify(taskRepository).save(taskEntity);
        verify(taskMapper).toDto(savedTask);
    }
}
