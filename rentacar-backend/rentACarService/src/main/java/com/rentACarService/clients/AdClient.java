package com.rentACarService.clients;

import com.rentACarService.dto.AdDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ad")
public interface AdClient {

    @GetMapping(value = "/ad/dto")
    public List<AdDTO> getAdDTOS();

}
