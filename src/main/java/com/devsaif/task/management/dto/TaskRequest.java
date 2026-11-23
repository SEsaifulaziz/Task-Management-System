package com.devsaif.task.management.dto;

import com.devsaif.task.management.entity.TaskPriority;
import com.devsaif.task.management.entity.TaskStatus;

import java.time.LocalDate;

public class TaskRequest {
    String title;

    String description;

    TaskStatus status;

    TaskPriority priority;

    LocalDate dueDate;

}
