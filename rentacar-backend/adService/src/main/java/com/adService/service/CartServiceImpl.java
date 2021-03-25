package com.adService.service;

import com.adService.clients.RentACarClient;
import com.adService.model.Ad;
import com.adService.model.RentACar;
import com.adService.model.User;
import com.adService.repository.AdRepository;
import com.adService.repository.RentACarRepository;
import com.adService.repository.UserRepository;
import com.adService.security.CustomUserDetails;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private RentACarRepository rentACarRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public RentACarClient rentACarClient;


    public void addToCart(RentACar rentACar) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());
        Ad ad = adRepository.findById(rentACar.getAd()).get();
        rentACar.setAdOwner(ad.getUserID());
        userDB.getRentACars().add(rentACar);
        userRepository.save(userDB);

    }

    public List<RentACar> getCartAds() {
        CustomUserDetails loginUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(loginUser.getUsername());
        List<RentACar> ret = rentACarClient.getCart(user.getId());
        return ret;
    }


}
