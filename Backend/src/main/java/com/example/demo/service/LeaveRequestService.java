package com.example.demo.service;

import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository repo;

    public LeaveRequestService(LeaveRequestRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public LeaveRequest create(LeaveRequest r) {
        normalize(r);
        if (!StringUtils.hasText(r.getStatus())) r.setStatus("Pending");
        return repo.save(r);
    }

    // READ
    public List<LeaveRequest> getAll() { return repo.findAll(); }
    public LeaveRequest getById(String id) { return repo.findById(id).orElse(null); }

    // UPDATE
    public LeaveRequest update(String id, LeaveRequest incoming) {
        LeaveRequest existing = repo.findById(id).orElse(null);
        if (existing == null) {
            incoming.setId(id);
            normalize(incoming);
            if (!StringUtils.hasText(incoming.getStatus())) incoming.setStatus("Pending");
            return repo.save(incoming);
        }

        // Always allow updating the employee message in history
        if (incoming.getMessage() != null) existing.setMessage(incoming.getMessage().trim());

        boolean wasPending = "Pending".equalsIgnoreCase(existing.getStatus());

        if (wasPending) {
            if (StringUtils.hasText(incoming.getStatus())) {
                existing.setStatus(incoming.getStatus().trim());
            }
            if (incoming.getDecisionComment() != null) {
                existing.setDecisionComment(incoming.getDecisionComment().trim());
            }
            // (optional) allow core fields update while pending
            if (incoming.getEmployeeId() != null) existing.setEmployeeId(incoming.getEmployeeId().trim());
            if (incoming.getLeaveType() != null) existing.setLeaveType(incoming.getLeaveType().trim());
            if (incoming.getStartDate() != null) existing.setStartDate(incoming.getStartDate().trim());
            if (incoming.getEndDate() != null) existing.setEndDate(incoming.getEndDate().trim());
        } // else: lock status/decisionComment after decision

        return repo.save(existing);
    }

    public void delete(String id) { repo.deleteById(id); }

    private void normalize(LeaveRequest r) {
        if (r == null) return;
        if (r.getEmployeeId() != null) r.setEmployeeId(r.getEmployeeId().trim());
        if (r.getLeaveType() != null) r.setLeaveType(r.getLeaveType().trim());
        if (r.getStartDate() != null) r.setStartDate(r.getStartDate().trim());
        if (r.getEndDate() != null) r.setEndDate(r.getEndDate().trim());
        if (r.getStatus() != null) r.setStatus(r.getStatus().trim());
        if (r.getMessage() != null) r.setMessage(r.getMessage().trim());
        if (r.getDecisionComment() != null) r.setDecisionComment(r.getDecisionComment().trim());
    }
}
