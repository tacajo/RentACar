package com.locationService.service;

import com.locationService.controller.QueueProducer;
import com.locationService.dto.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LocationService {

    @Autowired
    private QueueProducer queueProducer;

    @Scheduled(cron  = "*/10 * * * * *")
    public void addCords() throws Exception {
        System.out.println("salje koordinate..");
        Random random = new Random();
        LocationDTO locationDTO = new LocationDTO();
        //random.nextInt(max + 1 - min) + min;
        locationDTO.setLongitude(((Math.random()*(200000-190000))+190000)/10000);
        locationDTO.setLatitude(((Math.random()*(451000-450000))+450000)/10000);
        locationDTO.setAd(Long.valueOf (random.nextInt(5) + 1));
        System.out.println(locationDTO);
        queueProducer.produce(locationDTO);
    }
}
