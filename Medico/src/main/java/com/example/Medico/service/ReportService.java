package com.example.Medico.service;

import com.example.Medico.model.Reports;
import com.example.Medico.responses.ReportsResponse;
import com.example.Medico.model.Users;
import com.example.Medico.repository.ReportRepository;
import com.example.Medico.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ReportService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReportRepository reportRepository;

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
        reportRepository.save(report);
        return report;
    }

    public List<Reports> getReport(UUID id) {
        return reportRepository.findByUsers_Id(id);
    }

    public Reports getReportFile(UUID reportId) {
        return reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));
    }
}
