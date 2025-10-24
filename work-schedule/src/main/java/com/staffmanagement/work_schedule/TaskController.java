package com.staffmanagement.work_schedule;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // ----- Create -----
    @PostMapping
    public Task create(@RequestBody Task task) {
        return service.create(task);
    }

    // ----- Read All -----
    @GetMapping
    public List<Task> getAll() {
        return service.getAll();
    }

    // ----- Read One -----
    @GetMapping("/{id}")
    public Task getById(@PathVariable String id) {
        return service.getById(id);
    }

    // ----- Update -----
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        return service.update(id, updatedTask);
    }

    // ----- Delete -----
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
