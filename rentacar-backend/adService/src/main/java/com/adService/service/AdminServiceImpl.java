package com.adService.service;

import com.adService.model.*;
import com.adService.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private FuelRepository fuelRepository;

    @Autowired
    private GearShiftRepository gearShiftRepository;

    @Autowired
    private CarClassRepository carClassRepository;

    @Autowired
    private CodeBookRepository codeBookRepository;

    @Autowired
    private CarRepository carRepository;

    public Model addModel(Model model) {
        CodeBook codeBook = codeBookRepository.findById(1L).get();
        model.setCodeBook(codeBook);
        codeBook.getModels().add(model);
        return modelRepository.save(model);
    }

    public Brand addBrand(Brand brand) {
        CodeBook codeBook = codeBookRepository.findById(1L).get();
        brand.setCodeBook(codeBook);
        codeBook.getBrands().add(brand);
        return brandRepository.save(brand);
    }

    public CarClass addClass(CarClass carClass) {
        CodeBook codeBook = codeBookRepository.findById(1L).get();
        carClass.setCodeBook(codeBook);
        codeBook.getClasses().add(carClass);
        return carClassRepository.save(carClass);
    }

    public Fuel addFuel(Fuel fuel) {
        CodeBook codeBook = codeBookRepository.findById(1L).get();
        fuel.setCodeBook(codeBook);
        codeBook.getFuels().add(fuel);
        return fuelRepository.save(fuel);
    }

    public GearShift addGearShift(GearShift gearShift) {
        if (gearShiftRepository.findByName(gearShift.getName()) == null) {
            CodeBook codeBook = codeBookRepository.findById(1L).get();
            gearShift.setCodeBook(codeBook);
            codeBook.getGearShift().add(gearShift);
            return gearShiftRepository.save(gearShift);
        }
        return null;
    }

    public void deleteModel(Long id) {
        System.out.println("usao u delete model");
        Model model = modelRepository.findById(id).get();
        CodeBook codeBook = codeBookRepository.findById(1L).get();
        List<Car> cars = carRepository.findByModel(model);
        if(cars.isEmpty()) {
            System.out.println("nema nijednog auta s tim modelom");
            codeBook.getModels().remove(model);
            codeBookRepository.save(codeBook);
            modelRepository.delete(modelRepository.findById(id).get());
        }

    }

    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id).get();
        CodeBook codeBook = codeBookRepository.findById(1L).get();
        List<Car> cars = carRepository.findByBrand(brand);
        if(cars.isEmpty()) {
            codeBook.getBrands().remove(brand);
            codeBookRepository.save(codeBook);
            brandRepository.delete(brandRepository.findById(id).get());
        }
    }

    public void deleteClass(Long id) {
        CarClass carClass = carClassRepository.findById(id).get();
        CodeBook codeBook = codeBookRepository.findById(1L).get();
        List<Car> cars = carRepository.findByCarClass(carClass);
        if(cars.isEmpty()) {
            codeBook.getClasses().remove(carClass);
            codeBookRepository.save(codeBook);
            carClassRepository.delete(carClassRepository.findById(id).get());
        }
    }

    public void deleteFuel(Long id) {
        Fuel fuel = fuelRepository.findById(id).get();
        CodeBook codeBook = codeBookRepository.findById(1L).get();
        List<Car> cars = carRepository.findByFuel(fuel);
        if(cars.isEmpty()) {
            codeBook.getFuels().remove(fuel);
            codeBookRepository.save(codeBook);
            fuelRepository.delete(fuelRepository.findById(id).get());
        }
    }

    public void deleteGearShift(Long id) {
        GearShift gearShift = gearShiftRepository.findById(id).get();
        CodeBook codeBook = codeBookRepository.findById(1L).get();
        List<Car> cars = carRepository.findByGearShift(gearShift);
        if(cars.isEmpty()) {
            codeBook.getGearShift().remove(gearShift);
            codeBookRepository.save(codeBook);
            gearShiftRepository.delete(gearShiftRepository.findById(gearShift.getId()).get());
        }
    }
}
