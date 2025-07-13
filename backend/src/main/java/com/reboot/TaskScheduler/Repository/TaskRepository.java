package com.reboot.TaskScheduler.Repository;

import com.reboot.TaskScheduler.Entity.Task;
import com.reboot.TaskScheduler.Entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    // For scheduler: find PENDING tasks whose scheduled time has passed
    List<Task> findByStatusAndScheduledTimeBefore(TaskStatus status, LocalDateTime time);

    // Optional filters for listing tasks later
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByStatusNot(TaskStatus status);

    List<Task> findByScheduledTimeBetween(LocalDateTime start, LocalDateTime end);

}
