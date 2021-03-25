package com.agent.service;

import com.agent.dto.LightSearchDTO;
import com.agent.model.RentACar;
import com.agent.dto.RentACarDTO;
import com.agent.model.RentACar;
import com.agent.model.Request;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequestService{
  
    void createRequestRESERVED(RentACarDTO rentACar);

    List<Long> getAdsLightSearch(LightSearchDTO lightSearchDTO);

    void createRequests(List<RentACar> rentACarList, boolean bundle);

    List<Request> getOwnersRequest();

    void cancelRequest(Long id);

    void acceptRequest(Long id);

    void payRequest(Long id);

    List<Request> getUserRequest();

}
