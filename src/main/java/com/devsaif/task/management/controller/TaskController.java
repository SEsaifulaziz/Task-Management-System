package com.devsaif.task.management.controller;

import com.devsaif.task.management.dto.TaskRequest;
import com.devsaif.task.management.dto.TaskResponse;
import com.devsaif.task.management.entity.TaskPriority;
import com.devsaif.task.management.entity.TaskStatus;
import com.devsaif.task.management.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "Task Management",
        description = "APIs for creating, updating, retrieving and deleting tasks"
)
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        TaskResponse task = taskService.createTask(taskRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(task);
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getAll(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority,
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            )
            Pageable pageable
    ) {
        Page<TaskResponse> tasks =
                taskService.getTasks(status, priority, pageable);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable Long id){
        TaskResponse task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.updateTask(id, taskRequest);

        return ResponseEntity.ok(taskResponse);
    }

//    @GetMapping("/status/{status}")
//    public ResponseEntity<List<TaskResponse>> getByStatus(@PathVariable TaskStatus status){
//        List<TaskResponse> task = taskService.getTaskByStatus(status);
//        return ResponseEntity.ok(task);
//    }
//
//    @GetMapping("/priority/{priority}")
//    public ResponseEntity<List<TaskResponse>> getByPriority(@PathVariable TaskPriority priority){
//        List<TaskResponse> task = taskService.getTaskByPriority(priority);
//        return ResponseEntity.ok(task);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteById(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}

