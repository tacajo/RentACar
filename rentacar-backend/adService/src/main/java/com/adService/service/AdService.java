package com.adService.service;

import com.adService.dto.AdDTO;
import com.adService.dto.PriceListDTO;
import com.adService.model.Ad;
import com.adService.model.AdRating;
import com.adService.model.Location;
import com.adService.model.PriceList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface AdService {

    Ad getAd(Long id);

    List<AdDTO> getAdDTOS();

    void search();

    void createAd(AdDTO adDTO) throws IOException;

    int rate(AdRating adRating);

    Ad save(Ad ad);

    int getLimitKm(Long id);

    double getPricePerKm(Long id);

    void createPriceList(PriceListDTO priceListDTO);

    List<PriceList> getPriceLists();

    List<Location> getLocations(Long id);
}
