package com.agent.controller;

import com.agent.dto.AdDTO;
import com.agent.dto.CarDTO;
import com.agent.dto.CartDTO;
import com.agent.dto.CommentStatisticsDTO;
import com.agent.dto.PriceListDTO;
import com.agent.model.Ad;
import com.agent.model.Car;
import com.agent.model.RentACar;
import com.agent.model.PriceList;
import com.agent.service.AdService;
import com.agent.service.RentACarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "https://localhost:4201")
@RestController
@RequestMapping(value = "/ad", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdService adService;

    @Autowired
    private RentACarService rentACarService;

    @PostMapping(value="/new-ad")
  //  @PreAuthorize("hasAuthority('newAd')")
    public void addAd(@RequestBody AdDTO adDTO) throws IOException {
        adService.createAd(adDTO);
        logger.info(String.format("New add is created."));
    }

    @PostMapping(value="/new-priceList")
    //  @PreAuthorize("hasAuthority('newAd')")
    public void addPriceList(@RequestBody PriceListDTO priceListDTO) throws IOException {
        adService.createPriceList(priceListDTO);
        logger.info(String.format("New price list is created."));
    }

    @GetMapping(value = "/priceLists")
    public List<PriceList> getAllPriceLists(){
        return adService.getPriceLists();
    }

    @RequestMapping(value="/getAd/{id}", method= RequestMethod.GET)
    public Ad getAd(@PathVariable Long id) throws IOException {
        return adService.getAd(id);
    }

    @GetMapping(value = "/dto")
    public List<AdDTO> getAdDto(){
        return adService.getAdDTOS();
    }

    @GetMapping(value = "/cars")
    public List<Car> getAllCars(){
        return adService.getCars();
    }

    @GetMapping(value = "/commentStats")
    public List<CommentStatisticsDTO> getStatistics(){
        List<CommentStatisticsDTO> comments= new ArrayList<>();
        List<Ad> ads= new ArrayList<>();
        ads=adService.getAds();

        for ( Ad ad : ads
             ) {
            CommentStatisticsDTO commentStatisticsDTO= new CommentStatisticsDTO();
            commentStatisticsDTO.setAdID(ad.getId());
            commentStatisticsDTO.setNumOfComments(ad.getComments().size());
            comments.add(commentStatisticsDTO);
        }

        return comments;
    }

    @GetMapping(value = "/{id}")
    public AdDTO ad(@PathVariable Long id) {
        Ad ad = adService.getAd(id);
        CarDTO carDto = new CarDTO(ad.getCar().getModel().getName(), ad.getCar().getBrand().getName(), ad.getCar().getFuel().getName(), ad.getCar().getGearShift().getName(), ad.getCar().getCarClass().getName(), ad.getCar().getTraveledKms(), ad.getCar().getChildSeats(), ad.getCar().getLimitKms(), ad.getCar().getImageNames());
        return new AdDTO(ad.getId(), carDto, ad.getCity(), ad.isCollisionDamageWaiver(), ad.getUserID(), null,null, ad.getRating());
    }

    @PostMapping(value = "/cart")
    public void cart(@RequestBody CartDTO cartDTO) {
        RentACar rentACar = new RentACar().builder()
                .startDate(cartDTO.getStartDate())
                .endDate(cartDTO.getEndDate())
                .ad(cartDTO.getAdId())
                .build();
        adService.addToCart(rentACar);
    }

    @GetMapping(value = "/cart")
    public List<CartDTO> getCartAds() {
        List<CartDTO> cart = new ArrayList<>();
        List<RentACar> rentACars = rentACarService.getUserCart();
        System.out.println(rentACars);

        for (RentACar rentACar : rentACars) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setId(rentACar.getId());
            cartDTO.setStartDate(rentACar.getStartDate());
            cartDTO.setEndDate(rentACar.getEndDate());
            cartDTO.setAdId(rentACar.getAd());
            Ad ad = adService.getAd(rentACar.getAd());
            CarDTO carDto = new CarDTO(ad.getCar().getModel().getName(), ad.getCar().getBrand().getName(), ad.getCar().getFuel().getName(), ad.getCar().getGearShift().getName(), ad.getCar().getCarClass().getName(), ad.getCar().getTraveledKms(), ad.getCar().getChildSeats(), ad.getCar().getLimitKms(), null);
            AdDTO adDto = new AdDTO(ad.getId(), carDto, ad.getCity(), ad.isCollisionDamageWaiver(), ad.getUserID(), null,null, ad.getRating());
            cartDTO.setAd(adDto);
            System.out.println(cartDTO.getAd());
            cart.add(cartDTO);
        }
        System.out.println(cart);
        return cart;
    }



}
