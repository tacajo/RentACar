package com.agent.service;

import com.agent.dto.AdDTO;
import com.agent.dto.PriceListDTO;
import com.agent.model.Ad;
import com.agent.model.RentACar;
import com.agent.model.PriceList;
import org.springframework.stereotype.Service;
import com.agent.model.Car;

import java.io.IOException;
import java.util.List;

public interface AdService {

    Ad getAd(Long id);

    List<AdDTO> getAdDTOS();

    List<Ad> getAds();

    List<Car> getCars();

    void createPriceList(PriceListDTO priceListDTO);

    List<PriceList> getPriceLists();

    void search();

    void createAd(AdDTO adDTO) throws IOException;

    void addToCart(RentACar rentACar);

    List<RentACar> getUserCart(Long id);

}
