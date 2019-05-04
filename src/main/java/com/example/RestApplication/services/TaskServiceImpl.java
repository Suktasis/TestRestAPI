package com.example.RestApplication.services;

import com.example.RestApplication.domain.Status;
import com.example.RestApplication.domain.Task;
import com.example.RestApplication.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void addTask(Task task) {
        log.debug("add new task");
        task.setStatus(Status.RUNNING);
        task.setTimestamp(LocalDateTime.now());
        taskRepository.save(task);
    }

    @Override
    public Task findTask(Long id) {
        log.debug("find task by id");
        Optional<Task> user = taskRepository.findById(id);
        return user.orElse(null);
    }

    @Async
    public void updateTask(Task task){
        log.debug("update task");
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.setStatus(Status.FINISHED);
        task.setTimestamp(LocalDateTime.now());
        taskRepository.save(task);
    }
}
