package com.example.RestApplication.controllers;

import com.example.RestApplication.domain.Status;
import com.example.RestApplication.domain.Task;
import com.example.RestApplication.services.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskControllerTest {

    @Mock
    TaskService taskService;

    private MockMvc mock;

    @InjectMocks
    TaskController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new TaskController(taskService);
        mock = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void addUser() throws Exception {
        Task task = new Task();
        task.setId(1L);
        mock.perform(post("/task")).andExpect(status().isAccepted());
        verify(taskService, times(1)).addTask(any());
    }

    @Test
    public void findUser() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setStatus(Status.RUNNING);
        task.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));

        when(taskService.findTask(anyLong())).thenReturn(task);
        mock.perform(get("/task/1")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        verify(taskService, times(1)).findTask(anyLong());
    }
}