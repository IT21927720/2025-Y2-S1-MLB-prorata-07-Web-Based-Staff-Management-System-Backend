package com.staffmanagement.work_schedule;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    // ----- Create -----
    public Task create(Task t) {
        return repo.save(t);
    }

    // ----- Read (All Tasks) -----
    public List<Task> getAll() {
        return repo.findAll();
    }

    // ----- Read by ID -----
    public Task getById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    // ----- Update -----
    public Task update(String id, Task updatedTask) {
        Task existing = getById(id);

        existing.setTname(updatedTask.getTname());
        existing.setDescription(updatedTask.getDescription());
        existing.setAssignTo(updatedTask.getAssignTo());
        existing.setDeadlineDate(updatedTask.getDeadlineDate());
        existing.setStatus(updatedTask.getStatus());

        return repo.save(existing);
    }

    // ----- Delete -----
    public void deleteById(String id) {
        repo.deleteById(id);
    }
}
