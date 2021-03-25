package com.rentACarService.contoller;

import com.rentACarService.dto.ReportDTO;
import com.rentACarService.service.RentACarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    private RentACarService rentACarService;

    @PostMapping(value= "/create")
    private void createReport(@RequestBody ReportDTO reportDTO) {
        rentACarService.createReport(reportDTO);
    }
}
