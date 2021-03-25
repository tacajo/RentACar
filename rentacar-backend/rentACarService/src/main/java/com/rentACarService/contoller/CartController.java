package com.rentACarService.contoller;

import com.rentACarService.model.RentACar;
import com.rentACarService.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private RequestService requestService;

    @GetMapping(value = "{id}")
    private List<RentACar> getRentACars(@PathVariable Long id) {
        System.out.println("usao iz ad u rentacar service da preuzme oglase koji nemaju request");
        return requestService.getUserCart(id);
    }
}
