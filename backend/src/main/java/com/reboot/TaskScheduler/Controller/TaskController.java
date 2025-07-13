package com.reboot.TaskScheduler.Controller;

import com.reboot.TaskScheduler.DTO.TaskRequest;
import com.reboot.TaskScheduler.DTO.TaskResponse;
import com.reboot.TaskScheduler.Entity.TaskStatus;
import com.reboot.TaskScheduler.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest request){
        TaskResponse created = taskService.createTask(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public List<TaskResponse> fetchAllTasks(){
        return taskService.fetchAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> fetchTaskById(@PathVariable Long id){
        Optional<TaskResponse> task = taskService.fetchById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    public List<TaskResponse> getTasksByStatus(@RequestParam TaskStatus status){
        return taskService.fetchAllTasksByStatus(status);
    }

    @GetMapping("/overdue")
    public List<TaskResponse> getOverdueTasks(){
        return taskService.fetchAllTasksByStatus(TaskStatus.MISSED);
    }

    @GetMapping("/today")
    public List<TaskResponse> getTodayTasks(){
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime todayEnd = todayStart.plusDays(1);
        return taskService.getTasksByDateRange(todayStart, todayEnd);
    }




}
