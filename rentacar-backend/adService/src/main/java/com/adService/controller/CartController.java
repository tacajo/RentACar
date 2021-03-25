package com.adService.controller;

import com.adService.dto.AdDTO;
import com.adService.dto.CarDTO;
import com.adService.dto.CartDTO;
import com.adService.model.Ad;
import com.adService.model.RentACar;
import com.adService.service.AdService;
import com.adService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cart", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AdService adService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public void cart(@RequestBody CartDTO cartDTO) {
        RentACar rentACar = new RentACar().builder()
                .startDate(cartDTO.getStartDate())
                .endDate(cartDTO.getEndDate())
                .ad(cartDTO.getAdId())
                .build();
        cartService.addToCart(rentACar);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<CartDTO> getCartAds() {
        List<CartDTO> cart = new ArrayList<>();
        List<RentACar> rentACars = cartService.getCartAds();
        System.out.println(rentACars);

        for (RentACar rentACar : rentACars) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setId(rentACar.getId());
            cartDTO.setStartDate(rentACar.getStartDate());
            cartDTO.setEndDate(rentACar.getEndDate());
            cartDTO.setAdId(rentACar.getAd());
            Ad ad = adService.getAd(rentACar.getAd());
            CarDTO carDto = new CarDTO(ad.getCar().getModel().getName(), ad.getCar().getBrand().getName(), ad.getCar().getFuel().getName(), ad.getCar().getGearShift().getName(), ad.getCar().getCarClass().getName(), ad.getCar().getTraveledKms(), ad.getCar().getChildSeats(), ad.getCar().getLimitKms(),ad.getCar().getImageNames());
            AdDTO adDto = new AdDTO(ad.getId(), carDto, ad.getCity(), ad.isCollisionDamageWaiver(), ad.getUserID(), null,null, ad.getRating());
            cartDTO.setAd(adDto);
            System.out.println(cartDTO.getAd());
            cart.add(cartDTO);
        }
        System.out.println(cart);
        return cart;
    }
}
