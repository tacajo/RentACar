package com.adService.clients;

import com.adService.dto.LightSearchDTO;
import com.adService.model.RentACar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "rentacar")
public interface RentACarClient {

    @PostMapping(value = "/search/light-search")
    List<Long> getAdsLightSearch(@RequestBody LightSearchDTO lightSearchDTO);

    @GetMapping(value = "/cart/{id}")
    List<RentACar> getCart(@PathVariable Long id);


}
