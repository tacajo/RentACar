package com.agent.service;

import com.agent.dto.RentACarDTO;
import com.agent.model.RentACar;
import org.springframework.stereotype.Service;

import java.util.List;
import com.agent.dto.ReportDTO;

public interface RentACarService {

    void createRequest();

    void acceptRequest();

    void rejectRequest();

    void cancelRequest();

    void getByUserID();

    void createPaidRequest();

    List<RentACarDTO> getRentACarsUser();

    List<RentACarDTO> getRentACarsAgent();

    void createReport(ReportDTO reportDTO);

    RentACar findById(Long id);

    List<RentACar> getUserCart();

}
