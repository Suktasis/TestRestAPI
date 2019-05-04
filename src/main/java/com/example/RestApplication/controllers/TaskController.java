package com.example.RestApplication.controllers;

import com.example.RestApplication.domain.Status;
import com.example.RestApplication.domain.Task;
import com.example.RestApplication.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@Slf4j
public class TaskController {

    private TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task")
    public ResponseEntity addTask(){
        log.debug("add User POST");

        Task task = new Task();
        task.setStatus(Status.created);
        taskService.addTask(task);
        taskService.updateTask(task);
        return new ResponseEntity(task.getId(),HttpStatus.ACCEPTED);
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/task/{id}")
    public ResponseEntity<JSONObject> findTask(@PathVariable @Min(value = 0, message = "not GUID") Long id){
        log.debug("get USER GET");

        Task task = taskService.findTask(id);

        if(task == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        JSONObject entity = new JSONObject();
        entity.put("status",task.getStatus());
        entity.put("timestamp",task.getTimestamp());
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
