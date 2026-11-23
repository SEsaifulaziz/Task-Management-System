package com.devsaif.task.management.mapper;


import com.devsaif.task.management.dto.TaskRequest;
import com.devsaif.task.management.dto.TaskResponse;
import org.mapstruct.Mapper;
import org.springframework.scheduling.config.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskRequest taskRequest);

    TaskResponse toDto(Task task);



}
