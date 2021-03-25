package com.rentACarService.clients;

import com.rentACarService.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth")
public interface AuthClient {

    @GetMapping(value = "/user/find/{id}")
    UserDTO getUser(@PathVariable Long id);
}
