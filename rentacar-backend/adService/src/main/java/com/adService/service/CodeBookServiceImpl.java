package com.adService.service;

import com.adService.dto.CodesDTO;
import com.adService.model.*;
import com.adService.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CodeBookServiceImpl implements CodeBookService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    public FuelRepository fuelRepository;

    @Autowired
    public ModelRepository modelRepository;

    @Autowired
    public CarClassRepository carClassRepository;

    @Autowired
    public GearShiftRepository gearShiftRepository;


    @Override
    public CodesDTO formCodeBookDTO() {
       List<Model> models= modelRepository.findAll();
       List<Brand> brands= brandRepository.findAll();
       List<GearShift> gearShifts= gearShiftRepository.findAll();
       List<Fuel> fuels= fuelRepository.findAll();
       List<CarClass> classes= carClassRepository.findAll();

       CodesDTO codesDTO= new CodesDTO();

        for ( Model model : models
             ) {
            codesDTO.getModels().add(model.getName());
        }

        for ( Brand brand : brands
        ) {
            codesDTO.getBrands().add(brand.getName());
        }

        for ( Fuel fuel : fuels
        ) {
            codesDTO.getFuels().add(fuel.getName());
        }

        for ( CarClass carClass : classes
        ) {
            codesDTO.getCarClasses().add(carClass.getName());
        }

        for ( GearShift gearShift : gearShifts
        ) {
            codesDTO.getGearShifts().add(gearShift.getName());
        }

        return codesDTO;
    }

    public List<Model> getModels() {
        return modelRepository.findAll();
    }

    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    public List<Fuel> getFuels() {
        return fuelRepository.findAll();
    }

    public List<GearShift> getGearShifts() {
        return gearShiftRepository.findAll();
    }

    public List<CarClass> getCarClasses() {
        return carClassRepository.findAll();
    }
}
