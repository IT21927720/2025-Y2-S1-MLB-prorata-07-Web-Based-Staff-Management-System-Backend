package com.example.demo.controller;

import com.example.demo.model.LeaveRequest;
import com.example.demo.service.LeaveRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"}) // allow React dev server
@RestController
@RequestMapping("/api/leave")
public class LeaveRequestController {
    private final LeaveRequestService service;

    public LeaveRequestController(LeaveRequestService service) {
        this.service = service;
    }

    // --- CREATE (you already have; keep it) ---
    @PostMapping({"/create", "/apply"})
    public LeaveRequest apply(@RequestBody LeaveRequest body) {
        if (body.getStatus() == null || body.getStatus().isBlank()) body.setStatus("Pending");
        return service.create(body);
    }

    // --- READ ---
    @GetMapping("/all")
    public List<LeaveRequest> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public LeaveRequest getOne(@PathVariable String id) { return service.getById(id); }

    // --- UPDATE ---
    @PutMapping("/update/{id}")
    public LeaveRequest update(@PathVariable String id, @RequestBody LeaveRequest body) {
        return service.update(id, body);
    }

    // --- DELETE ---
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) { service.delete(id); }
}
