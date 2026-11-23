package com.devsaif.task.management.dto;

import com.devsaif.task.management.entity.TaskPriority;
import com.devsaif.task.management.entity.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponse (

     Long id,

     String title,

     String description,

     TaskStatus status,

     TaskPriority priority,

     LocalDate dueDate,

     LocalDateTime createdAt

) {}
