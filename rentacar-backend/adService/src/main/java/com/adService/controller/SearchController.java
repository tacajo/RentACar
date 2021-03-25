package com.adService.controller;

import com.adService.dto.AdDTO;
import com.adService.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping(value="/lightSearch")
    public List<AdDTO> lightSearch(@RequestParam("city") String city, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        System.out.println("usao u search controller");

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
