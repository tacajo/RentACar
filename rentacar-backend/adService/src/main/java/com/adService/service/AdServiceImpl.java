package com.adService.service;

import com.adService.dto.AdDTO;
import com.adService.dto.CarDTO;
import com.adService.dto.PriceListDTO;
import com.adService.model.*;
import com.adService.repository.*;
import com.adService.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

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
    public GearShiftRepository gearShiftRepository;

    @Autowired
    public AdRatingRepository adRatingRepository;

    @Autowired
    public LocationRepository locationRepository;

    @Autowired
    public PriceListRepository priceListRepository;

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
        long endTime = System.nanoTime();
        double time = (double) ((endTime - startTime) / 1000000);
    }



    public int rate(AdRating adRating) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());
        Ad ad = adRepository.findById(adRating.getAd()).get();

        List<AdRating> ads = adRatingRepository.findByAd(ad.getId());
        for (AdRating rating : ads) {
            if (rating.getUser() == userDB.getId())
                return 0;
        }
        adRating.setUser(userDB.getId());
        adRatingRepository.save(adRating);
        ad.setRating(averageRate(ad));
        System.out.println(ad.getRating());
        adRepository.save(ad);
        return 1;
    }

    public double averageRate(Ad ad) {
        List<AdRating> rated = adRatingRepository.findByAd(ad.getId());
        return rated.stream()
                .mapToDouble(AdRating::getRating)
                .average()
                .getAsDouble();
    }

    public Ad save(Ad ad) {
        return adRepository.save(ad);
    }

    public int getLimitKm(Long id) {
        return adRepository.findById(id).get().getCar().getLimitKms();
    }


    public double getPricePerKm(Long id) {
        return adRepository.findById(id).get().getPriceList().getPricePerKm();
    }

    public List<Location> getLocations(Long id) {
        if(adRepository.findById(id).isPresent())
            return locationRepository.findByAd(id);
        else
            return null;
    }
}
