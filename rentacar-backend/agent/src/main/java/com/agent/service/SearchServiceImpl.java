package com.agent.service;

import com.agent.dto.AdDTO;
import com.agent.dto.CarDTO;
import com.agent.dto.LightSearchDTO;
import com.agent.model.Ad;
import com.agent.repository.AdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AdRepository adRepository;

    @Autowired
    public RequestService requestService;

    @Override
    public List<AdDTO> lightSearch(String city, String startDate, String endDate) {
        System.out.println("light search: "+ city + startDate + endDate);

        List<Ad> ads = new ArrayList<>();

        if(city.equals("")){
            ads = adRepository.findAll();
        }else{
            ads = adRepository.findByCity(city);
        }

        if(ads.isEmpty()){
            return new ArrayList<>();
        }

        LightSearchDTO lightSearchDTO = new LightSearchDTO();
        lightSearchDTO.setStartDate(startDate);
        lightSearchDTO.setEndDate(endDate);

        for (Ad ad:ads) {
            lightSearchDTO.getAds().add(ad.getId());
        }

        List<Long> retAds = requestService.getAdsLightSearch(lightSearchDTO);

        List<AdDTO> adsDTO = new ArrayList<>();

        for(Ad ad : ads){
            for(Long id : retAds){
                if(ad.getId().equals(id)){
                    System.out.println("Ad car id" +ad.getCar().getId() +" images " + ad.getCar().getImageNames());
                    CarDTO carDto = new CarDTO(ad.getCar().getModel().getName(), ad.getCar().getBrand().getName(), ad.getCar().getFuel().getName(), ad.getCar().getGearShift().getName(), ad.getCar().getCarClass().getName(), ad.getCar().getTraveledKms(), ad.getCar().getChildSeats(), ad.getCar().getLimitKms(),ad.getCar().getImageNames());
                    carDto.setImageNames(ad.getCar().getImageNames());
                    AdDTO adDto = new AdDTO(ad.getId(), carDto, ad.getCity(), ad.isCollisionDamageWaiver(), ad.getUserID(),null, null,0,ad.getPriceList().getId(),ad.getDiscount());
                    adsDTO.add(adDto);
                }
            }
        }
        logger.info(String.format("User search for ads. Search came back with " + adsDTO.size() + " results"));
        return adsDTO;
    }

    @Override
    public List<AdDTO> advancedSearch(String brand, String model, String fuel, String gearShift, String carClass, String traveledKms, String childSeats, String planKms, String fromPrice, String toPrice, String startDate, String endDate, String city) {

        List<AdDTO> lightSearch = lightSearch(city, startDate, endDate);
        System.out.println(brand + model + fuel + endDate + fromPrice + toPrice + traveledKms);

        List<AdDTO> ret  =  lightSearch
                .stream()
                .filter(ad -> {
                    if(!brand.equals("") && brand != null) {
                        return ad.getCar().getBrand().toUpperCase().equals(brand.toUpperCase());
                    } else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(!model.equals("") && model != null) {
                        return ad.getCar().getModel().toUpperCase().equals(model.toUpperCase());
                    } else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(!fuel.equals("") && fuel != null) {
                        return ad.getCar().getFuel().toUpperCase().equals(fuel.toUpperCase());
                    } else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(!gearShift.equals("") && gearShift != null) {
                        return ad.getCar().getGearShift().toUpperCase().equals(gearShift.toUpperCase());
                    } else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(!carClass.equals("") && carClass != null) {
                        return ad.getCar().getCarClass().toUpperCase().equals(carClass.toUpperCase());
                    } else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(!traveledKms.equals("undefined") && !traveledKms.equals("null")) {
                        return ad.getCar().getTraveledKms() == Integer.parseInt(traveledKms);
                    } else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(!childSeats.equals("undefined") && !childSeats.equals("null")) {
                        return ad.getCar().getChildSeats() == Integer.parseInt(childSeats);
                    } else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(!fromPrice.equals("undefined") && !fromPrice.equals("null")) {
                        Optional<Ad> ad1 = adRepository.findById(ad.getId());
                        return ad1.get().getPriceList().getPrice() >= Double.parseDouble(fromPrice);
                    } else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(!toPrice.equals("undefined") && !toPrice.equals("null")) {
                        Optional<Ad> ad1 = adRepository.findById(ad.getId());
                        return ad1.get().getPriceList().getPrice() <= Double.parseDouble(toPrice);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());

        System.out.println(ret);

        return ret;
    }
}
