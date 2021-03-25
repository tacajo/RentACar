package com.adService.controller;

import com.adService.dto.*;
import com.adService.model.Ad;
import com.adService.model.AdRating;
import com.adService.model.Location;
import com.adService.model.PriceList;
import com.adService.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/ad", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdController {

    @Autowired
    private AdService adService;

    @PostMapping(value = "/new-ad")
    public void addAd(@RequestBody AdDTO adDTO) throws IOException {
        adService.createAd(adDTO);
    }

    @RequestMapping(value = "/getAd/{id}", method = RequestMethod.GET)
    public Ad getAd(@PathVariable Long id) throws IOException {
        return adService.getAd(id);
    }

    @GetMapping(value = "/{id}")
    public AdDTO ad(@PathVariable Long id) {
        Ad ad = adService.getAd(id);
        CarDTO carDto = new CarDTO(ad.getCar().getModel().getName(), ad.getCar().getBrand().getName(), ad.getCar().getFuel().getName(), ad.getCar().getGearShift().getName(), ad.getCar().getCarClass().getName(), ad.getCar().getTraveledKms(), ad.getCar().getChildSeats(), ad.getCar().getLimitKms(),ad.getCar().getImageNames());
        return new AdDTO(ad.getId(), carDto, ad.getCity(), ad.isCollisionDamageWaiver(), ad.getUserID(), null, null, ad.getRating());
    }

    @PostMapping(value="/new-priceList")
    //  @PreAuthorize("hasAuthority('newAd')")
    public void addPriceList(@RequestBody PriceListDTO priceListDTO) throws IOException {
       adService.createPriceList(priceListDTO);
    }

    @GetMapping(value = "/priceLists")
    public List<PriceList> getAllPriceLists(){
        return adService.getPriceLists();
    }


    @GetMapping(value = "/dto")
    public List<AdDTO> getAdDto() {
        return adService.getAdDTOS();
    }

    @PostMapping(value = "/rate")
    public int setRate(@RequestBody AdRatingDTO rate) {
        AdRating adRating = new AdRating().builder()
                .ad(rate.getAdId())
                .rating(rate.getRating())
                .build();
        return adService.rate(adRating);
    }

    @GetMapping(value = "/rt/get-limit-km")
    public Integer getLimitKm(@RequestParam("id") Long id) {
        return (Integer) adService.getLimitKm(id);
    }

    @GetMapping(value = "/rt/get-price-per-km")
    public Double getPricePerKm(@RequestParam("id") Long id) {
        return (Double) adService.getPricePerKm(id);
    }

    @GetMapping(value = "/locations/{id}")
    public List<Location> getLocation(@PathVariable Long id) {
        return adService.getLocations(id);
    }

}
