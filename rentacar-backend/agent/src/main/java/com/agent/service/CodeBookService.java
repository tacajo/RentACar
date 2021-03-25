package com.agent.service;

import com.agent.dto.CodesDTO;
import com.agent.model.*;
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
