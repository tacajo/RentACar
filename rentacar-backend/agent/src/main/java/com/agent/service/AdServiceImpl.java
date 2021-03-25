package com.agent.service;

import com.agent.dto.AdDTO;
import com.agent.dto.CarDTO;
import com.agent.dto.PriceListDTO;
import com.agent.model.*;
import com.agent.repository.*;
import com.agent.security.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CarRepository carRepository;

    @Autowired
    public AdRepository adRepository;

    @Autowired
    public BrandRepository brandRepository;

    @Autowired
    public FuelRepository fuelRepository;

    @Autowired
    public ModelRepository modelRepository;

    @Autowired
    public CarClassRepository carClassRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PriceListRepository priceListRepository;

    @Autowired
    public GearShiftRepository gearShiftRepository;

    @Override
    public Ad getAd(Long id) {
        return adRepository.findById(id).get();
    }

    @Override
    public List<AdDTO> getAdDTOS() {

        List<Ad> ads = adRepository.findAll();
        List<AdDTO> adDTOS = new ArrayList<>();
        for(Ad ad : ads){
            CarDTO carDTO = new CarDTO(ad.getCar().getModel().getName(), ad.getCar().getBrand().getName(), ad.getCar().getFuel().getName(), ad.getCar().getGearShift().getName(), ad.getCar().getCarClass().getName(), ad.getCar().getTraveledKms(), ad.getCar().getChildSeats(), ad.getCar().getLimitKms(),  ad.getCar().getImageNames());
            AdDTO adDTO = new AdDTO(ad.getId(), carDTO, ad.getCity(), ad.isCollisionDamageWaiver(), ad.getUserID(),null, null,0,ad.getPriceList().getId(),ad.getDiscount());
            adDTOS.add(adDTO);
        }

        return adDTOS;
    }

    @Override
    public List<Ad> getAds() {
        return adRepository.findAll();
    }

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public void createPriceList(PriceListDTO priceListDTO) {
        PriceList priceList=new PriceList(priceListDTO.getStartDate(), priceListDTO.getEndDate(), priceListDTO.getPrice(), priceListDTO.getCollisionDamageWaiver(), priceListDTO.getCollisionDamageWaiver());
        priceListRepository.save(priceList);
    }

    @Override
    public List<PriceList> getPriceLists() {
        return priceListRepository.findAll();
    }

    @Override
    public void search() {

    }

    @Override
    public void createAd(AdDTO adDTO) throws IOException {

        long startTime = System.nanoTime();

        Ad ad= new Ad();

//        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userDB = userRepository.findByUsername(user.getUsername());
//
//        if(userDB.getNumCreatedAds()<3) {
        ad.setCity(adDTO.getCity());
        ad.setUserID(2L);
        ad.setCollisionDamageWaiver(adDTO.isCollisionDamageWaiver());


        Car car = new Car();

        Model model = modelRepository.findByName(adDTO.getCar().getModel());

        Brand brand = brandRepository.findByName(adDTO.getCar().getBrand());

        GearShift gearShift = gearShiftRepository.findByName(adDTO.getCar().getGearShift());

        Fuel fuel = fuelRepository.findByName(adDTO.getCar().getFuel());

        CarClass carClass = carClassRepository.findByName(adDTO.getCar().getCarClass());

        car.setTraveledKms(0);
        car.setModel(model);
        car.setBrand(brand);
        car.setCarClass(carClass);
        car.setGearShift(gearShift);
        car.setFuel(fuel);
        car.setChildSeats(adDTO.getCar().getChildSeats());
        car.setLimitKms(adDTO.getCar().getLimitKms());
        System.out.println(adDTO.getCar().getImageNames().size());
        for(int i=0;i<adDTO.getCar().getImageNames().size();i++) {
            System.out.println(adDTO.getCar().getImageNames().get(i));
            car.getImageNames().add(adDTO.getCar().getImageNames().get(i));
        }


        carRepository.save(car);
        logger.info(String.format("Successfully added new car: " + car.getBrand() + car.getModel()));

        brand.getCars().add(car);
        model.getCars().add(car);
        fuel.getCars().add(car);
        gearShift.getCars().add(car);
        carClass.getCars().add(car);

        brandRepository.save(brand);
        modelRepository.save(model);
        fuelRepository.save(fuel);
        carClassRepository.save(carClass);
        gearShiftRepository.save(gearShift);

        ad.setCar(car);

        ad.setStartDate(adDTO.getStartDate());
        ad.setEndDate(adDTO.getEndDate());
        ad.setDiscount(adDTO.getDiscount());
        ad = adRepository.save(ad);
//            userDB.setNumCreatedAds(userDB.getNumCreatedAds()+1) ;
        car.setAd(ad);
        carRepository.save(car);
//        } else {
//            return;
//       }

        PriceList priceList= priceListRepository.findById(adDTO.getPriceListID()).get();
        if(priceList!=null) {
            ad.setPriceList(priceList);
            priceList.getAds().add(ad);
            adRepository.save(ad);
            priceListRepository.save(priceList);
        }

        logger.info(String.format("Successfully added new ad with id:" + ad.getId() + " - for car: " + ad.getCar()));

        long endTime = System.nanoTime();
        double time = (double) ((endTime - startTime) / 1000000);
        logger.trace("Time elapsed for adding ad was " + time + "ms");
    }

    public void addToCart(RentACar rentACar) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());
        Ad ad = adRepository.findById(rentACar.getAd()).get();
        rentACar.setAdOwner(ad.getUserID());
        userDB.getRentACars().add(rentACar);
        userRepository.save(userDB);
    }
    public List<RentACar> getUserCart(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());
        List<RentACar> userCart = userDB.getRentACars();
        List<RentACar> ret = new ArrayList<>();
        System.out.println(userCart.size());
        for (RentACar rentACar : userCart) {
            if (rentACar.getRequest() == null)
                ret.add(rentACar);
        }
        System.out.println(ret.size());
        return ret;
    }
}
