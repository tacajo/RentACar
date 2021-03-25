package com.agent.service;



import com.agent.dto.LightSearchDTO;
import com.agent.enums.RequestStatus;
import com.agent.model.RentACar;
import com.agent.model.Request;
import com.agent.repository.AdRepository;
import com.agent.repository.RequestRepository;
import com.agent.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.agent.dto.LightSearchDTO;
import com.agent.dto.RentACarDTO;
import com.agent.enums.RequestStatus;
import com.agent.model.Ad;
import com.agent.model.RentACar;
import com.agent.model.Request;
import com.agent.model.User;
import com.agent.repository.AdRepository;
import com.agent.repository.RentACarRepository;
import com.agent.repository.RequestRepository;
import com.agent.repository.UserRepository;
import com.agent.security.CustomUserDetails;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private RentACarRepository rentACarRepository;

    public void createRequestRESERVED(RentACarDTO rentACarDTO) {
//        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       // User userDB = userRepository.findByUsername(user.getUsername());

        RentACar rentACar =  new RentACar().builder()
                            .startDate(rentACarDTO.getStartDate())
                            .endDate(rentACarDTO.getEndDate())
                            .ad(rentACarDTO.getAdId())
                            .build();
        Set<RentACar> rentACarSet = new HashSet<RentACar>();
        rentACarSet.add(rentACar);

           Request request = new Request().builder()
                    .startDate(new Date())
                    .endDate(DateUtils.addHours(new Date(), 24))
                    .status(RequestStatus.PAID)
                    .userID(1L)
                    .rentACars(rentACarSet)
                    .build();

            requestRepository.save(request);
             rentACar.setRequest(request);
            rentACarRepository.save(rentACar);
    }
  
    @Override
    public List<Long> getAdsLightSearch(LightSearchDTO lightSearchDTO) {

        System.out.println("usao u rentacar servis u getadslightsearch");

        String paramTimeFrom = lightSearchDTO.getStartDate().split("T")[1];
        String paramDateFrom = lightSearchDTO.getStartDate().split("T")[0];
        String paramTimeTo = lightSearchDTO.getEndDate().split("T")[1];
        String paramDateTo = lightSearchDTO.getEndDate().split("T")[0];
        LocalTime timeFrom = LocalTime.parse(paramTimeFrom);
        LocalDate dateFrom = LocalDate.parse(paramDateFrom);
        LocalTime timeTo = LocalTime.parse(paramTimeTo);
        LocalDate dateTo = LocalDate.parse(paramDateTo);

        List<Long> unavailableAds = new ArrayList<>();

        List<Request> req = requestRepository.findAll();
        if(req == null){
            return lightSearchDTO.getAds();
        }
        for (Request request:req) {
            if(request.getStatus().equals(RequestStatus.PAID) || request.getStatus().equals(RequestStatus.RESERVED)){
                for(RentACar rc : request.getRentACars()){
                    for(Long adId : lightSearchDTO.getAds()){
                        if(adId.equals(rc.getAd())){
                            System.out.println(rc.getEndDate().toString());

                            String paramTimeRentACarFrom = rc.getStartDate().toString().split(" ")[1].split(":")[0] +  ":" + rc.getStartDate().toString().split(" ")[1].split(":")[1];
                            String paramDateRentACarFrom = rc.getStartDate().toString().split(" ")[0];
                            String paramTimeRentACarTo = rc.getEndDate().toString().split(" ")[1].split(":")[0] + ":" + rc.getEndDate().toString().split(" ")[1].split(":")[1];
                            String paramDateRentACarTo = rc.getEndDate().toString().split(" ")[0];
                            LocalTime timeRentACarFrom = LocalTime.parse(paramTimeRentACarFrom);
                            LocalDate dateRentACarFrom = LocalDate.parse(paramDateRentACarFrom);
                            LocalTime timeRentACarTo = LocalTime.parse(paramTimeRentACarTo);
                            LocalDate dateRentACarTo = LocalDate.parse(paramDateRentACarTo);

                            if(dateFrom.isAfter(dateRentACarFrom) && dateFrom.isBefore(dateRentACarTo)){
                                if(!unavailableAds.contains(adId)){
                                    unavailableAds.add(adId);
                                }
                            }else if(dateTo.isAfter(dateRentACarFrom) && dateTo.isBefore(dateRentACarTo)){
                                if(!unavailableAds.contains(adId)){
                                    unavailableAds.add(adId);
                                }
                            }else if(dateFrom.isBefore(dateRentACarFrom) && dateTo.isAfter(dateRentACarTo)){
                                if(!unavailableAds.contains(adId)){
                                    unavailableAds.add(adId);
                                }
                            }else if(dateFrom.isEqual(dateRentACarFrom) && timeFrom.isAfter(timeRentACarFrom)){
                                if(!unavailableAds.contains(adId)){
                                    unavailableAds.add(adId);
                                }
                            }else if(dateFrom.isEqual(dateRentACarFrom) && timeFrom.isBefore(timeRentACarFrom) && dateTo.isAfter(dateRentACarFrom)){
                                if(!unavailableAds.contains(adId)){
                                    unavailableAds.add(adId);
                                }
                            }else if(dateFrom.isEqual(dateRentACarFrom) && timeFrom.isBefore(timeRentACarFrom) && dateTo.isEqual(dateRentACarFrom) && timeTo.isAfter(timeRentACarFrom)){
                                if(!unavailableAds.contains(adId)){
                                    unavailableAds.add(adId);
                                }
                            }else if(dateFrom.isBefore(dateRentACarFrom) && dateTo.isEqual(dateRentACarFrom) && timeTo.isAfter(timeRentACarFrom)){
                                if(!unavailableAds.contains(adId)){
                                    unavailableAds.add(adId);
                                }
                            }else if(dateFrom.isEqual(dateRentACarTo) && timeFrom.isBefore(timeRentACarFrom)){
                                if(!unavailableAds.contains(adId)){
                                    unavailableAds.add(adId);
                                }
                            }else if(dateTo.isEqual(dateRentACarTo) && timeTo.isBefore(timeRentACarTo)){
                                if(!unavailableAds.contains(adId)){
                                    unavailableAds.add(adId);
                                }
                            }else if(dateTo.isEqual(dateRentACarTo) && timeTo.isAfter(timeRentACarTo) && dateFrom.isBefore(dateRentACarTo) ){
                                if(!unavailableAds.contains(adId)){
                                    unavailableAds.add(adId);
                                }
                            }else if(dateTo.isEqual(dateRentACarTo) && timeTo.isAfter(timeRentACarTo) && dateFrom.isEqual(dateRentACarTo) && timeFrom.isBefore(timeRentACarTo)){
                                if(!unavailableAds.contains(adId)){
                                    unavailableAds.add(adId);
                                }
                            }
                        }
                    }
                }
            }
        }
        List<Long> reqID = new ArrayList<>();
        for(Long id : lightSearchDTO.getAds()){
            reqID.add(id);
        }

        System.out.println(unavailableAds);
        for(Long id : lightSearchDTO.getAds()){
            for(Long idUnavailable : unavailableAds){
                if(id.equals(idUnavailable)){
                    reqID.remove(id);
                    break;
                }
            }
        }


        return reqID;
    }



    @Override
    public void cancelRequest(Long id) {

    }

    @Override
    public void acceptRequest(Long id) {

    }

    @Override
    public void payRequest(Long id) {

    }


    public void createRequests(List<RentACar> rentACarList, boolean bundle) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());

        Set<RentACar> rentACarSet = new HashSet<RentACar>();
        Request request = new Request().builder()
                .startDate(new Date())
                .endDate(DateUtils.addHours(new Date(), 24))
                .status(RequestStatus.PENDING)
                .rentACars(rentACarSet)
                .userID(userDB.getId())
                .build();

        if (bundle) {
            List<RentACar> sortedRentACars = rentACarList.stream()
                    .sorted(Comparator.comparing(RentACar::getAdOwner))
                    .collect(Collectors.toList());
            Ad ad = adRepository.findById(sortedRentACars.get(0).getAd()).get();
            Long id = ad.getUserID();
            for (RentACar rentACar : sortedRentACars) {
                if (id != rentACar.getAdOwner()) {
                    id = rentACar.getAdOwner();
                    requestRepository.save(request);
                    rentACarSet = new HashSet<>();
                    request = new Request().builder()
                            .startDate(new Date())
                            .endDate(DateUtils.addHours(new Date(), 24))
                            .status(RequestStatus.PENDING)
                            .rentACars(rentACarSet)
                            .userID(userDB.getId())
                            .build();
                }

                request.getRentACars().add(rentACar);
                rentACar.setRequest(request);
            }
            requestRepository.save(request);
            Timer timer = new Timer("Timer");
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    endDate24H();
                }
            };
            timer.schedule(timerTask, DateUtils.addHours(request.getStartDate(), 24));

        } else {
            for (RentACar rentACar : rentACarList) {
                rentACarSet = new HashSet<RentACar>();
                rentACarSet.add(rentACar);
                request = new Request().builder()
                        .startDate(new Date())
                        .endDate(DateUtils.addHours(new Date(), 24))
                        .status(RequestStatus.PENDING)
                        .userID(userDB.getId())
                        .rentACars(rentACarSet)
                        .build();
                rentACar.setRequest(request);
                requestRepository.save(request);
                Timer timer = new Timer("Timer");
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        endDate24H();
                    }
                };
                timer.schedule(timerTask, DateUtils.addHours(request.getStartDate(), 24));
            }
        }
    }

    public void reservation12H() {
        Date reservedDate = new Date();
        List<Request> requests = requestRepository.findAll();
        for (Request request : requests) {
            if (request.getEndPaid().equals(reservedDate) && request.getStatus().equals(RequestStatus.RESERVED)) {
                request.setStatus(RequestStatus.CANCELED);
                requestRepository.save(request);
                break;
            }
        }
    }

    public void endDate24H() {
        Date reservedDate = new Date();
        List<Request> requests = requestRepository.findAll();
        for (Request request : requests) {
            if (request.getEndDate().equals(reservedDate) && request.getStatus().equals(RequestStatus.PENDING)) {
                request.setStatus(RequestStatus.CANCELED);
                requestRepository.save(request);
                break;
            }
        }
    }



    public List<Request> getOwnersRequest() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());

        List<Request> ret = new ArrayList<>();
        List<Request> requests = requestRepository.findAll();
        for (Request request : requests) {
            System.out.println(request.getId() + " ima ovoliko rentacarova " + request.getRentACars().size());
            System.out.println(userDB.getId());
            if (request.getRentACars().iterator().next().getAdOwner() == userDB.getId()) {
                ret.add(request);
            }
        }
        return ret;
    }

    public List<Request> getUserRequest() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());

        List<Request> ret = new ArrayList<>();
        List<Request> requests = requestRepository.findAll();
        for (Request request : requests) {
            if (request.getUserID() == userDB.getId()) {
                ret.add(request);
            }
        }
        return ret;
    }




}

