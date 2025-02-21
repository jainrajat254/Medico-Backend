package com.example.Medico.user.controller;

import com.example.Medico.user.model.Records;
import com.example.Medico.user.responses.RecordsResponse;
import com.example.Medico.user.service.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/records")
@RestController
public class RecordsController {

    @Autowired
    RecordsService recordsService;

    @PutMapping("/addRecord/{id}")
    public Records addRecord(@RequestBody RecordsResponse recordsResponse, @PathVariable UUID id) {
        return recordsService.addRecord(recordsResponse, id);
    }

    @GetMapping("/getRecord/{id}")
    public List<Records> getRecord(@PathVariable UUID id) {
        return recordsService.getRecord(id);
    }
}
