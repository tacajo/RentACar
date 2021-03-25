package com.agent.controller;

import com.agent.dto.ReportDTO;
import com.agent.service.RentACarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://localhost:4201")
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    private RentACarService rentACarService;

    @PostMapping(value= "/create")
    private ResponseEntity createReport(@RequestBody ReportDTO reportDTO) {
        rentACarService.createReport(reportDTO);
        return ResponseEntity.ok("ok");
    }
}
