package com.devsaif.task.management.mapper;


import com.devsaif.task.management.dto.TaskRequest;
import com.devsaif.task.management.dto.TaskResponse;
import com.devsaif.task.management.entity.TaskEntity;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")

public interface TaskMapper {

    TaskEntity toEntity(TaskRequest taskRequest);

    TaskResponse toDto(TaskEntity task);



}
