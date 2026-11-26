package com.devsaif.task.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDashboardResponse {
    private long totalTasks;
    private long completedTasks;
    private long inProgressTasks;
    private long pendingTasks;
    private long highPriorityTasks;
    private long overdueTasks;
}
