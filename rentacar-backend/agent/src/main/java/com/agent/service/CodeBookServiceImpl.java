package com.agent.service;

import com.agent.dto.CodesDTO;
import com.agent.model.*;
import com.agent.repository.*;
import com.agent.soap.ModelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private CodeBookRepository codeBookRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelClient modelClient;

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

    public Model addModel(Model model) {
        CodeBook codeBook = codeBookRepository.findById(1L).get();
        model.setCodeBook(codeBook);
        codeBook.getModels().add(model);

        Long prosao = modelClient.addModel(model);

        if(prosao.equals(1L)){
            System.out.println("komentar je sacuvan u glavnom beku");
            return modelRepository.save(model);
        } else {
            System.out.println("komentar nije sacuvan u glavnom beku");
        }

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
