package com.example.Medico.user.service;

import com.example.Medico.user.model.Reports;
import com.example.Medico.user.responses.ReportsResponse;
import com.example.Medico.user.model.Users;
import com.example.Medico.user.repository.ReportsRepository;
import com.example.Medico.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ReportsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReportsRepository reportsRepository;

    @Transactional
    public Reports addReport(ReportsResponse reportsResponse, UUID id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Reports report = new Reports();
        report.setReportName(reportsResponse.getReportName());
        report.setReviewedBy(reportsResponse.getReviewedBy());
        report.setAttentionLevel(reportsResponse.getAttentionLevel());
        report.setUsers(user);

        byte[] reportData = reportsResponse.getReport();
        report.setReport(reportData);
        reportsRepository.save(report);
        return report;
    }

    public List<Reports> getReport(UUID id) {
        return reportsRepository.findByUsers_Id(id);
    }

    public Reports getReportFile(UUID reportId) {
        return reportsRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));
    }
}
