package com.adService.service;

import com.adService.dto.CartDTO;
import com.adService.model.Ad;
import com.adService.model.RentACar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    void addToCart(RentACar rentACar);

    List<RentACar> getCartAds();
}
