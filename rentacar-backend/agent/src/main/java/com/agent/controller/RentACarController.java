package com.agent.controller;

import com.agent.dto.AdDTO;
import com.agent.dto.CarDTO;
import com.agent.dto.CartDTO;
import com.agent.dto.RentACarDTO;
import com.agent.model.Ad;
import com.agent.model.RentACar;
import com.agent.service.RentACarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:4201")
@RequestMapping(value = "/rentacar")
public class RentACarController {

    @Autowired
    private RentACarService rentACarService;


    @GetMapping(value="/get-rent-a-cars-user")
    @PreAuthorize("hasAuthority('orderedRequests')")
    public List<RentACarDTO> getRentACarsUser(){
        return rentACarService.getRentACarsUser();
    }

    @GetMapping(value="/get-rent-a-cars-agent")
    @PreAuthorize("hasAuthority('publishedRequests')")
    public List<RentACarDTO> getRentACarsAgent(){
        return rentACarService.getRentACarsAgent();

    }



}
