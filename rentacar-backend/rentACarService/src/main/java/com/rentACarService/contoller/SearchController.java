package com.rentACarService.contoller;

import com.rentACarService.dto.LightSearchDTO;
import com.rentACarService.service.RequestService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    private RequestService requestService;

    public SearchController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping(value="/light-search", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Long> getAdsLightSearch(@RequestBody LightSearchDTO lightSearchDTO){
        System.out.println("Usao u search controller: "+lightSearchDTO.getEndDate() + " " + lightSearchDTO.getStartDate() + " " + lightSearchDTO.getAds());
        return requestService.getAdsLightSearch(lightSearchDTO);
    }


}
