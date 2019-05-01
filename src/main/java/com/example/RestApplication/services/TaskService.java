package com.example.RestApplication.services;

import com.example.RestApplication.domain.Task;

public interface TaskService {

    void addTask(Task task);

    Task findTask(Long id);

    void updateTask(Task task);
}
