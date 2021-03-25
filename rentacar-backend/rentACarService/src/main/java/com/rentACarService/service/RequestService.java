package com.rentACarService.service;

import com.rentACarService.dto.LightSearchDTO;
import com.rentACarService.model.RentACar;
import com.rentACarService.model.Request;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RequestService{

    List<Long> getAdsLightSearch(LightSearchDTO lightSearchDTO);

    void createRequests(List<RentACar> rentACarList, boolean bundle);

    List<RentACar> getUserCart(Long id);

    List<Request> getOwnersRequest();

    void cancelRequest(Long id);

    void acceptRequest(Long id);

    void payRequest(Long id);

    List<Request> getUserRequest();
}
