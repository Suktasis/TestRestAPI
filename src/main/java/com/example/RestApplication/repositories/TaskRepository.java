package com.example.RestApplication.repositories;

import com.example.RestApplication.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {

    Optional<Task> findTaskById(Long id);

}