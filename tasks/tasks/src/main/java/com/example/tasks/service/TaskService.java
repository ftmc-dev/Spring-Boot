package com.example.tasks.service;

import com.example.tasks.model.Task;
import com.example.tasks.model.TaskPriority;
import com.example.tasks.model.TaskStatus;
import com.example.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.tasks.model.TaskStatus.DONE;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getAllTask(Task task, TaskStatus taskStatus, TaskPriority taskPriority) {
        if (taskStatus == null && taskPriority == null) {
            List<Task> tasks = taskRepository.findAll();
        }
        if(taskStatus != null && taskPriority == null) {
            List<Task> tasks = taskRepository.findByStatus(taskStatus);
        }
        if (taskStatus == null && taskPriority != null) {
            List<Task> tasks = taskRepository.findByPriority(taskPriority);
        }
        return taskRepository.;
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTask(Long id, Task Task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
      if(optionalTask.isEmpty()) {
          throw new RuntimeException("Task Not Found");
      }
      Task taskToUpdate = optionalTask.get();
      if(taskToUpdate.getStatus() == DONE) {

          
      }
      taskToUpdate.setTitle(Task.getTitle());
      taskToUpdate.setPriority(Task.getPriority());
      taskToUpdate.setStatus(Task.getStatus());
      return taskRepository.save(taskToUpdate);
    }

    public void deleteTaskById(Long id) {
        if(taskRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Task Not Found");
        }
        taskRepository.deleteById(id);
    }

    public List<Task> getTaskByStatus(TaskStatus status) {
      if(taskRepository.findByStatus(status).isEmpty()) {
          throw new RuntimeException("Task Not Found");
      }
      return taskRepository.findByStatus(status);
    }

    public List<Task> getTaskByPriority(TaskPriority priority) {
        if(taskRepository.findByPriority(priority).isEmpty()) {
            throw new RuntimeException("Task Not Found");
        }
        return taskRepository.findByPriority(priority);
    }

    public List<Task> getTaskByPriorityStatus(TaskPriority priority, TaskStatus status) {
        return taskRepository.findByPriorityAndStatus(priority, status);
    }


}
}
