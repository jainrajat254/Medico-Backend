package com.example.Medico.user.service;

import com.example.Medico.user.model.Records;
import com.example.Medico.user.responses.RecordsResponse;
import com.example.Medico.user.model.Users;
import com.example.Medico.user.repository.RecordsRepository;
import com.example.Medico.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecordsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecordsRepository recordsRepository;

    public Records addRecord(RecordsResponse recordsResponse, UUID id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Records record = new Records(
                user,
                recordsResponse.getRecordName(),
                recordsResponse.getReviewedBy(),
                recordsResponse.getAttentionLevel()
        );
        return recordsRepository.save(record);
    }

    public List<Records> getRecord(UUID id) {
        return recordsRepository.findByUsers_Id(id);
    }
}
