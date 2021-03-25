package com.rentACarService.service;


import com.rentACarService.dto.RentACarDTO;
import com.rentACarService.dto.ReportDTO;
import com.rentACarService.model.RentACar;

import java.util.List;

public interface RentACarService {


    RentACar findById(Long id);

    void createRequest();

    void acceptRequest();

    void rejectRequest();

    void cancelRequest();

    void getByUserID();

    void createPaidRequest();

    void createReport();

    List<RentACarDTO> getRentACarsUser();

    List<RentACarDTO> getRentACarsAgent();

    void createReport(ReportDTO reportDTO);

}
