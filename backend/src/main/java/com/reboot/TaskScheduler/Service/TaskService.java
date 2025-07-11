package com.reboot.TaskScheduler.Service;

import com.reboot.TaskScheduler.DTO.TaskRequest;
import com.reboot.TaskScheduler.DTO.TaskResponse;
import com.reboot.TaskScheduler.Entity.Task;
import com.reboot.TaskScheduler.Entity.TaskStatus;
import com.reboot.TaskScheduler.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskResponse createTask(TaskRequest request){
        LocalDateTime now = LocalDateTime.now();

        TaskStatus intialStatus = TaskStatus.PENDING;
        if(request.getScheduledTime() != null && request.getScheduledTime().isBefore(now)){
            intialStatus=TaskStatus.DUE;
        }

        Task currentTask = new Task();
        currentTask.setTitle(request.getTitle());
        currentTask.setDescripion(request.getDescription());
        currentTask.setStatus(intialStatus);
        currentTask.setScheduledTime(request.getScheduledTime());
        currentTask.setRecurring(request.isRecurring());
        currentTask.setCreatedAt(now);

        Task saved = taskRepository.save(currentTask);

        return new TaskResponse(saved.getId(),
                saved.getTitle(),
                saved.getDescripion(),
                saved.getScheduledTime(),
                saved.getCreatedAt(),
                saved.getStatus(),
                saved.isRecurring());
    }

}
