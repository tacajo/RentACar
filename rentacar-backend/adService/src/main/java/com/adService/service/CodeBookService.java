package com.adService.service;

import com.adService.dto.CodesDTO;
import com.adService.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CodeBookService {

    CodesDTO formCodeBookDTO();

    List<Model> getModels();

    List<Brand> getBrands();

    List<Fuel> getFuels();

    List<GearShift> getGearShifts();

    List<CarClass> getCarClasses();

}
