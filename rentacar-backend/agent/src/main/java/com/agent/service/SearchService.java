package com.agent.service;

import com.agent.dto.AdDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SearchService {

    List<AdDTO> lightSearch(String city, String startDate, String endDate);

    List<AdDTO> advancedSearch(String brand, String model, String fuel, String gearShift, String carClass, String traveledKms, String childSeats, String planKms, String fromPrice, String toPrice, String startDate, String endDate, String city);


}
