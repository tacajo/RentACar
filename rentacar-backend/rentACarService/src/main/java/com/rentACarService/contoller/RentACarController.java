package com.rentACarService.contoller;

import com.rentACarService.dto.RentACarDTO;
import com.rentACarService.service.RentACarService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/rentacar")
public class RentACarController {

    private RentACarService rentACarService;

    public RentACarController(RentACarService rentACarService) {
        this.rentACarService = rentACarService;
    }

    @GetMapping(value="/get-rent-a-cars-user")
    @PreAuthorize("hasRole('USER')")
    public List<RentACarDTO> getRentACarsUser(){
        return rentACarService.getRentACarsUser();
    }

    @GetMapping(value="/get-rent-a-cars-agent")
    @PreAuthorize("hasRole('AGENT')")
    public List<RentACarDTO> getRentACarsAgent(){
        return rentACarService.getRentACarsAgent();
    }
}
