package com.reboot.TaskScheduler.DTO;

import com.reboot.TaskScheduler.Entity.TaskStatus;

import java.time.LocalDateTime;

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime scheduledTime;
    private LocalDateTime createdAt;
    private TaskStatus status;
    private boolean recurring;

    public TaskResponse() {
    }

    public TaskResponse(Long id, String title, String description, LocalDateTime scheduledTime, LocalDateTime createdAt, TaskStatus status, boolean recurring) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.scheduledTime = scheduledTime;
        this.createdAt = createdAt;
        this.status = status;
        this.recurring = recurring;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }
}
