package com.example.RestApplication.services;

import com.example.RestApplication.domain.Task;
import com.example.RestApplication.repositories.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void findUser() {
        Task task = new Task();
        task.setId(1L);

        Optional<Task> user1 = Optional.of(task);

        when(taskRepository.findById(anyLong())).thenReturn(user1);

        Optional<Task> returnedUser = taskRepository.findById(1L);

        assertEquals(task.getId(),user1.get().getId());

        verify(taskRepository,times(1)).findById(anyLong());

    }
}