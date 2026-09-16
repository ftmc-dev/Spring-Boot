package com.example.tasks.repository;

import com.example.tasks.model.Task;
import com.example.tasks.model.TaskPriority;
import com.example.tasks.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(TaskPriority priority);

    List<Task> findByPriorityAndStatus(TaskPriority priority, TaskStatus status);;

}
