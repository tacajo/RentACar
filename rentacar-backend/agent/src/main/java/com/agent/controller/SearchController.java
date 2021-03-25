package com.agent.controller;

import com.agent.dto.AdDTO;
import com.agent.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:4201")
@RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SearchService searchService;

    @GetMapping(value = "/lightSearch")
    public List<AdDTO> lightSearch(@RequestParam("city") String city, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {

        List<AdDTO> ret = searchService.lightSearch(city, startDate, endDate);

        return ret;
    }

    @GetMapping(value="/advancedSearch")
    public List<AdDTO> advancedSearch(@RequestParam("brand") String brand, @RequestParam("model") String model, @RequestParam("fuel") String fuel, @RequestParam("gearShift") String gearShift, @RequestParam("carClass") String carClass, @RequestParam("traveledKms") String traveledKms, @RequestParam("childSeats") String childSeats, @RequestParam("planKms") String planKms, @RequestParam("fromPrice") String fromPrice, @RequestParam("toPrice") String toPrice, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("city") String city){
        System.out.println("usao u advancedSearch");

        List<AdDTO> ret = searchService.advancedSearch(brand, model, fuel, gearShift, carClass, traveledKms, childSeats, planKms, fromPrice, toPrice, startDate, endDate, city);

        return ret;
    }

}
