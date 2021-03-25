package com.adService.service;

import com.adService.clients.RentACarClient;
import com.adService.dto.AdDTO;
import com.adService.dto.CarDTO;
import com.adService.dto.LightSearchDTO;
import com.adService.model.Ad;
import com.adService.repository.AdRepository;
import com.adService.utils.BASE64DecodedMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    public AdRepository adRepository;

    @Autowired
    public RentACarClient rentACarClient;

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

        System.out.println(lightSearchDTO);

        List<Long> retAds = rentACarClient.getAdsLightSearch(lightSearchDTO);

        System.out.println("Rent a car servis mi je vratio: " + retAds);
        List<AdDTO> adsDTO = new ArrayList<>();

        for(Ad ad : ads){
            for(Long id : retAds){
                if(ad.getId().equals(id)){
                    System.out.println(ad.getCar().getImage());
                    CarDTO carDto = new CarDTO(ad.getCar().getModel().getName(), ad.getCar().getBrand().getName(), ad.getCar().getFuel().getName(), ad.getCar().getGearShift().getName(), ad.getCar().getCarClass().getName(), ad.getCar().getTraveledKms(), ad.getCar().getChildSeats(), ad.getCar().getLimitKms(),ad.getCar().getImageNames());
                    AdDTO adDto = new AdDTO(ad.getId(), carDto, ad.getCity(), ad.isCollisionDamageWaiver(), ad.getUserID(), null,null, ad.getRating());
                    adsDTO.add(adDto);
                }
            }
        }

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
