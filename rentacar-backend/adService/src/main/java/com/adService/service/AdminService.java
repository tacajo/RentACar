package com.adService.service;

import com.adService.model.*;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    Model addModel(Model model);

    Brand addBrand(Brand brand);

    CarClass addClass(CarClass carClass);

    Fuel addFuel(Fuel fuel);

    GearShift addGearShift(GearShift gearShift);

    void deleteModel(Long id);

    void deleteBrand(Long id);

    void deleteClass(Long id);

    void deleteFuel(Long id);

    void deleteGearShift(Long id);

}
