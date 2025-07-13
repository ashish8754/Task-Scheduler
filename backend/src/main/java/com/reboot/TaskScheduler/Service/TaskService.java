package com.reboot.TaskScheduler.Service;

import com.reboot.TaskScheduler.DTO.TaskRequest;
import com.reboot.TaskScheduler.DTO.TaskResponse;
import com.reboot.TaskScheduler.Entity.Task;
import com.reboot.TaskScheduler.Entity.TaskStatus;
import com.reboot.TaskScheduler.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskResponse createTask(TaskRequest request){
        LocalDateTime now = LocalDateTime.now();

        //Initially all tasks are in pending state
        TaskStatus intialStatus = TaskStatus.PENDING;
        //Now we check for due logic which means , if the current time is not reached the scheduled time for the task then the task is due for completion
        if(request.getScheduledTime() != null && request.getScheduledTime().isBefore(now)){
            intialStatus=TaskStatus.DUE;
        }

        Task currentTask = new Task();
        currentTask.setTitle(request.getTitle());
        currentTask.setDescription(request.getDescription());
        currentTask.setStatus(intialStatus);
        currentTask.setScheduledTime(request.getScheduledTime());
        currentTask.setRecurring(request.isRecurring());
        currentTask.setCreatedAt(now);

        Task saved = taskRepository.save(currentTask);

        return mapTaskToResponse(saved);
    }

    //Adding a scheduled task to run every given interval and check the taska and update the Status based on provided logic.
    @Scheduled(fixedRate = 60000)
    public void updateStatus(){
        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks=taskRepository.findAll();

        for(Task task : tasks){
            if(task.getScheduledTime() != null && task.getScheduledTime().isBefore(now)){
                if(task.getStatus() == TaskStatus.PENDING){
                    task.setStatus(TaskStatus.DUE);
                }
                else if(task.getStatus() == TaskStatus.DUE && task.getScheduledTime().plusHours(1).isBefore(now)){
                    task.setStatus(TaskStatus.MISSED);
                }
                System.out.println("Updated Task status to : " + task.getStatus());
                taskRepository.save(task);
            }
        }
    }

    public Optional<TaskResponse> fetchById(Long id){
        return taskRepository.findById(id).map(this::mapTaskToResponse);
    }

    public List<TaskResponse> fetchAllTasks() {
        return taskRepository.findAll().stream().map(this::mapTaskToResponse).collect(Collectors.toList());
    }

    public List<TaskResponse> fetchAllTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status).stream().map(this::mapTaskToResponse).collect(Collectors.toList());
    }

    public List<TaskResponse> getTasksByDateRange(LocalDateTime todayStart, LocalDateTime todayEnd) {
        return taskRepository.findByScheduledTimeBetween(todayStart, todayEnd).stream().map(this::mapTaskToResponse).collect(Collectors.toList());
    }

    private TaskResponse mapTaskToResponse(Task task){
        TaskResponse response = new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getScheduledTime(),
                task.getCreatedAt(),
                task.getStatus(),
                task.isRecurring()
        );
        return response;
    }


}
