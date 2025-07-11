package com.reboot.TaskScheduler.DTO;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class TaskRequest {

    @NotBlank(message = "Title must not be blank.")
    private String title;
    private String description;
    private LocalDateTime scheduledTime;
    private boolean recurring;

    public TaskRequest() {
    }

    public TaskRequest(String title, String description, LocalDateTime scheduledTime, boolean recurring) {
        this.title = title;
        this.description = description;
        this.scheduledTime = scheduledTime;
        this.recurring = recurring;
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

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }
}
